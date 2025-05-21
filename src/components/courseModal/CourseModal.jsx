import React from 'react';
import './CourseModal.css';
import { useCart } from '../../context/CartContext';
import { useNavigate } from 'react-router-dom';

const CourseModal = ({ course, onClose }) => {
    const { addToCart, cartItems } = useCart();
    const navigate = useNavigate();

    const storedUser = JSON.parse(localStorage.getItem('user'));
    const userRole = storedUser?.role || null;

    if (!course) return null;

    const isInCart = cartItems.some(item => item.courseId === course.courseId);

    const handleAddToCart = () => {
        if (!storedUser) {
            navigate('/login');
            return;
        }

        addToCart(course);
        onClose();
    };

    return (
        <div className="course-modal-overlay" onClick={onClose}>
            <div className="course-modal-content" onClick={(e) => e.stopPropagation()}>
                <button className="close-button" onClick={onClose}>×</button>
                <img
                    src={`/img/${course.courseId}.svg`}
                    alt={`Course ${course.title}`}
                    onError={(e) => { e.target.src = '/static/img/default.svg'; }}
                />

                <div className="modal-details">
                    <h2>{course.title}</h2>
                    <p>{course.description || 'No description provided yet.'}</p>

                    <p><strong>Price:</strong> {course.price}</p>
                    <p><strong>Difficulty:</strong> {course.difficulty}</p>
                    <p><strong>Topic:</strong> {course.category}</p>
                    <p><strong>Session:</strong> {course.session}</p>

                    {/* Only show ID to admins */}
                    {userRole === 'admin' && (
                        <p><strong>Course ID:</strong> {course.courseId}</p>
                    )}

                    {!isInCart && (
                        <button className="add-to-cart-button" onClick={handleAddToCart}>
                            Add to Cart
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
};

export default CourseModal;