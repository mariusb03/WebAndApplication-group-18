import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import CourseModal from '../../components/courseModal/CourseModal';
import './AllCoursesPage.css';

const AllCoursesPage = () => {
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const searchParam = params.get('search') || '';

    const [courses, setCourses] = useState([]);
    const [searchQuery, setSearchQuery] = useState(searchParam);
    const [selectedCourse, setSelectedCourse] = useState(null);
    const [user, setUser] = useState(null);
    const [favourites, setFavourites] = useState([]);

    const [coursePrices, setCoursePrices] = useState({});

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        if (storedUser) {
            setUser(storedUser);

            fetch(`http://129.241.236.99:8082/user/${storedUser.userId}/favourites`)
                .then(res => res.json())
                .then(data => {
                    const favIds = data.map(course => course.courseId);
                    setFavourites(favIds);
                })
                .catch(err => console.error('Failed to load favourites:', err));
        }
    }, []);

    useEffect(() => {
        setSearchQuery(searchParam);
    }, [searchParam]);

    useEffect(() => {
        fetch('http://129.241.236.99:8082/api/courses/getAll', {
            headers: {
                'userRole': user?.role || 'guest'
            }
        })
            .then(response => response.json())
            .then(data => {
                setCourses(data);

                data.forEach(course => {
                    fetch(`http://129.241.236.99:8082/api/courses/getPrice/${course.courseId}`)
                        .then(res => res.json())
                        .then(priceData => {
                            const prices = priceData.map(p => p.price).filter(p => p != null);
                            if (prices.length > 0) {
                                const min = Math.min(...prices);
                                const max = Math.max(...prices);
                                setCoursePrices(prev => ({
                                    ...prev,
                                    [course.courseId]: min === max ? `${min} NOK` : `${min} - ${max} NOK`
                                }));
                            }
                        });
                });
            })
            .catch(error => console.error('Error fetching courses:', error));
    }, [user]);

    const toggleFavourite = (courseId) => {
        if (!user) return;

        const isFav = favourites.includes(courseId);
        const method = isFav ? 'DELETE' : 'POST';

        fetch(`http://129.241.236.99:8082/user/${user.userId}/favourite/${courseId}`, {
            method,
        })
            .then(res => {
                if (res.ok) {
                    setFavourites(prev =>
                        isFav ? prev.filter(id => id !== courseId) : [...prev, courseId]
                    );
                } else {
                    console.error('Failed to update favourite');
                }
            })
            .catch(err => console.error('Error updating favourite:', err));
    };

    const displayedCourses = courses.filter(course => {
        const matchesSearch = course.title.toLowerCase().includes(searchQuery.toLowerCase());
        const isVisible = user?.role === 'admin' || !course.hidden;
        return matchesSearch && isVisible;
    });

    return (
        <div className="courses-page">
            <h1>All Courses</h1>

            <input
                type="text"
                className="course-search"
                placeholder="Search for a course..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
            />

            <div className="course-grid">
                {displayedCourses.length > 0 ? (
                    displayedCourses.map((course, index) => (
                        <div
                            key={index}
                            className={`course-card ${course.hidden ? 'hidden-course' : ''}`}
                            onClick={() => setSelectedCourse(course)}
                        >
                            <img
                                src={`/img/${course.courseId}.svg`}
                                alt={`Course ${course.title}`}
                                onError={(e) => { e.target.src = '/static/img/default.svg'; }}
                            />
                            <h3 className="course-title">{course.title}</h3>

                            {user?.role === 'admin' && (
                                <button
                                    className="toggle-visibility-btn"
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        fetch(`http://129.241.236.99:8082/api/courses/${course.courseId}/toggleVisibility`, {
                                            method: 'PUT',
                                        })
                                            .then(() => fetch('http://129.241.236.99:8082/api/courses/getAll', {
                                                headers: {
                                                    'userRole': user?.role || 'user'
                                                }
                                            }))
                                            .then(res => res.json())
                                            .then(updated => setCourses(updated));
                                    }}
                                >
                                    {course.hidden ? 'Unhide' : 'Hide'}
                                </button>
                            )}

                            {user && (
                                <span
                                    className={`favourite-star ${favourites.includes(course.courseId) ? 'filled' : ''}`}
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        toggleFavourite(course.courseId);
                                    }}
                                >
                                    ★
                                </span>
                            )}

                            <p><strong>Price:</strong> {coursePrices[course.courseId] || 'Loading...'}</p>
                            <p><strong>Difficulty:</strong> {course.difficulty}</p>
                            <p><strong>Topic:</strong> {course.category}</p>
                            <p><strong>Session:</strong> {course.session}</p>
                            {user?.role === 'admin' && (
                                <p><strong>ID:</strong> {course.courseId}</p>
                            )}
                        </div>
                    ))
                ) : (
                    <p>No courses found.</p>
                )}
            </div>

            <CourseModal
                course={selectedCourse}
                onClose={() => setSelectedCourse(null)}
            />
        </div>
    );
};

export default AllCoursesPage;