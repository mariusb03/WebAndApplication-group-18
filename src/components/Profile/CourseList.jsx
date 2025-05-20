import React, { useState } from 'react';
import './CourseList.css';
import CourseModal from '../courseModal/CourseModal';

const CourseList = ({ courses }) => {
    const [selectedCourse, setSelectedCourse] = useState(null);

    return (
        <div className="course-grid">
            {courses.map(course => (
                <div
                    key={course.courseId}
                    className="course-card"
                    onClick={() => setSelectedCourse(course)}
                >
                    <img
                        src={`/img/${course.courseId}.svg`}
                        alt={`Course ${course.title}`}
                        onError={(e) => { e.target.src = '/static/img/default.svg'; }}
                    />
                    <h3 className="course-title">{course.title}</h3>
                    <p><strong>Price:</strong> {course.price} NOK</p>
                    <p><strong>Difficulty:</strong> {course.difficulty}</p>
                    <p><strong>Topic:</strong> {course.category}</p>
                    <p><strong>Session:</strong> {course.session}</p>
                </div>
            ))}

            {selectedCourse && (
                <CourseModal
                    course={selectedCourse}
                    onClose={() => setSelectedCourse(null)}
                />
            )}
        </div>
    );
};

export default CourseList;