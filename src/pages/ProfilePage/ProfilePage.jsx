import React from 'react';
import './ProfilePage.css';


import UserInfo from '../../components/Profile/UserInfo';
import CourseList from '../../components/Profile/CourseList';

const ProfilePage = () => {
    // Mock data
    const enrolledCourses = [
        { id: 1, title: 'AI & Machine Learning', progress: '45%' },
        { id: 2, title: 'Cloud Fundamentals', progress: 'Completed' },
    ];

    return (
        <div className="profile-page-container">
            <UserInfo
                name="John Doe"
                email="john@example.com"
            />

            <h2 className="section-title">Enrolled Courses</h2>
            <CourseList courses={enrolledCourses} />
        </div>
    );
};

export default ProfilePage;