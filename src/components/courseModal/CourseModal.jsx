import React from 'react';
import './CourseModal.css';
import { useCart } from '../../context/CartContext';


const CourseModal = ({ course, onClose }) => {
    const { addToCart } = useCart();

    if (!course) return null;

    const handleAddToCart = () => {
        addToCart(course);
        onClose(); // Optionally close modal
    };

    return (
        <div className="course-modal-overlay" onClick={onClose}>
            <div className="course-modal-content" onClick={(e) => e.stopPropagation()}>
                <button className="close-button" onClick={onClose}>×</button>
                <img src={course.image} alt={course.title} className="modal-image" />

                <div className="modal-details">
                    <h2>{course.title}</h2>
                    <p>{course.description || 'No description provided yet.'}</p>

                    <p><strong>Price:</strong> {course.price}</p>
                    <p><strong>Difficulty:</strong> {course.difficulty}</p>
                    <p><strong>Topic:</strong> {course.topic}</p>
                    <p><strong>Session:</strong> {course.sessions}</p>
                    <p><strong>Course ID:</strong> {course.id}</p>

                    <button className="add-to-cart-button" onClick={handleAddToCart}>
                        Add to Cart
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CourseModal;