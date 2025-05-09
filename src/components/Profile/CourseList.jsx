import React from 'react';
import './CourseList.css';

const CourseList = ({ courses }) => {
    return (
        <div className="course-list">
            {courses.map(course => (
                <div key={course.id} className="course-card">
                    <h3>{course.title}</h3>
                    <p>Status: {course.progress}</p>
                </div>
            ))}
        </div>
    );
};

export default CourseList;