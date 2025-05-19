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

    useEffect(() => {
        setSearchQuery(searchParam);
    }, [searchParam]);

    useEffect(() => {
        fetch('http://localhost:8080/api/courses')
            .then(response => response.json())
            .then(data => {
                setCourses(data);
            })
            .catch(error => {
                console.error('Error fetching courses:', error);
            });
    }, []);

    const filteredCourses = courses.filter(course =>
        course.title.toLowerCase().includes(searchQuery.toLowerCase())
    );

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
                {filteredCourses.length > 0 ? (
                    filteredCourses.map((course, index) => (
                        <div
                            key={index}
                            className="course-card"
                            onClick={() => setSelectedCourse(course)}
                        >
                            {/* You may want to set course.image dynamically */}
                            <img src={`/static/img/coursPictures/${course.title.replace(/\s+/g, '')}.jpg`} alt={course.title} />
                            <h3 className="course-title">{course.title}</h3>
                            <p className="course-description">{course.description}</p>
                            <p><strong>Price:</strong> {course.price} NOK</p>
                            <p><strong>Difficulty:</strong> {course.difficulty}</p>
                            <p><strong>Topic:</strong> {course.category}</p>
                            <p><strong>Session:</strong> {course.session}</p>
                            <p><strong>ID:</strong> {course.courseId}</p>
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