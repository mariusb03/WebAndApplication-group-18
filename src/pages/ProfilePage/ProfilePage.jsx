import React, { useEffect, useState } from 'react';
import './ProfilePage.css';
import UserInfo from '../../components/Profile/UserInfo';
import CourseList from '../../components/Profile/CourseList';
import { useNavigate } from 'react-router-dom';

const ProfilePage = () => {
    const [user, setUser] = useState(null);
    const [enrolledCourses, setEnrolledCourses] = useState([]);
    const [favouriteCourses, setFavouriteCourses] = useState([]);
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('user');
        setUser(null);
        setEnrolledCourses([]);
        setFavouriteCourses([]);
        navigate('/login');
    };

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        if (storedUser) {
            setUser(storedUser);

            // Fetch enrolled courses
            fetch(`http://localhost:8081/api/courses/user/${storedUser.userId}`)
                .then(res => res.json())
                .then(data => setEnrolledCourses(data))
                .catch(err => console.error('Error fetching enrolled courses:', err));

            // Fetch favourited courses
            fetch(`http://localhost:8081/user/${storedUser.userId}/favourites`)
                .then(res => res.json())
                .then(data => setFavouriteCourses(data))
                .catch(err => console.error('Error fetching favourite courses:', err));
        }
    }, []);

    if (!user) return <p>Loading profile...</p>;

    return (
        <div className="profile-page-container">
            <UserInfo
                name={user.name}
                email={user.email}
            />

            <h2 className="section-title">Enrolled Courses</h2>
            <CourseList courses={enrolledCourses} />

            <h2 className="section-title">Favourited Courses</h2>
            <CourseList courses={favouriteCourses} />

            <button className="logout-button" onClick={handleLogout}>
                Log Out
            </button>
        </div>
    );
};

export default ProfilePage;