import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import CourseModal from '../../components/courseModal/CourseModal';
import './AllCoursesPage.css';
import 'react-refresh/runtime';

import AWS from '../../assets/coursPictures/AWS.png';
import Azure from '../../assets/coursPictures/Azure.png';
import Business from '../../assets/coursPictures/businessStrategy.png';
import Databricks from '../../assets/coursPictures/Databricks.png';
import ImageRec from '../../assets/coursPictures/ImageRecognition.jpg';
import Java from '../../assets/coursPictures/JavaLogo.png';
import ML from '../../assets/coursPictures/machineLearning.jpg';
import NET from '../../assets/coursPictures/NET.jpg';
import SEO from '../../assets/coursPictures/SEO.png';
import Social from '../../assets/coursPictures/SocialMediaMarketing.jpg';
import SQL from '../../assets/coursPictures/SQL.jpg';

const dummyCourses = [
    {
        id: 'C001',
        title: 'AWS Fundamentals',
        image: AWS,
        description: 'Learn core services and architecture of AWS.',
        price: '$99',
        difficulty: 'Beginner',
        topic: 'Cloud Computing',
        sessions: '4 weeks, self-paced'
    },
    {
        id: 'C002',
        title: 'Java Programming',
        image: Java,
        description: 'Master Java programming from scratch.',
        price: '$89',
        difficulty: 'Intermediate',
        topic: 'Programming',
        sessions: '6 weeks, instructor-led'
    },
];

const AllCoursesPage = () => {
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const searchParam = params.get('search') || '';

    const [searchQuery, setSearchQuery] = useState(searchParam);
    const [selectedCourse, setSelectedCourse] = useState(null);

    const filteredCourses = dummyCourses.filter(course =>
        course.title.toLowerCase().includes(searchQuery.toLowerCase())
    );

    useEffect(() => {
        setSearchQuery(searchParam);
    }, [searchParam]);

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
                            <img src={course.image} alt={course.title} />
                            <h3 className="course-title">{course.title}</h3>
                            <p className="course-description">{course.description}</p>
                            <p><strong>Price:</strong> {course.price}</p>
                            <p><strong>Difficulty:</strong> {course.difficulty}</p>
                            <p><strong>Topic:</strong> {course.topic}</p>
                            <p><strong>Session:</strong> {course.sessions}</p>
                            <p><strong>ID:</strong> {course.id}</p>
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