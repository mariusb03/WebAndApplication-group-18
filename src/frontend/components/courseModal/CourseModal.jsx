import React, { useEffect, useState } from 'react';
import './CourseModal.css';
import { useCart } from '../../context/CartContext';
import { useNavigate } from 'react-router-dom';

const CourseModal = ({ course, onClose }) => {
    const { addToCart, cartItems } = useCart();
    const navigate = useNavigate();

    const storedUser = JSON.parse(localStorage.getItem('user'));
    const userRole = storedUser?.role || null;

    const [providers, setProviders] = useState([]);
    const [selectedProvider, setSelectedProvider] = useState(null);

    useEffect(() => {
        if (course) {
            fetch(`http://localhost:8082/api/courses/getPrice/${course.courseId}`)
                .then(res => res.json())
                .then(data => {
                    setProviders(data);
                })
                .catch(err => console.error("Failed to fetch providers:", err));
        }
    }, [course]);

    if (!course) return null;

    const isInCart = cartItems.some(
        item => item.courseId === course.courseId && item.providerId === selectedProvider
    );

    const handleAddToCart = () => {
        if (!storedUser) {
            navigate('/login');
            return;
        }

        const providerObj = providers.find(p => p.providerId === selectedProvider);

        if (!providerObj) return;

        addToCart(course, providerObj);

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
                    <p><strong>Difficulty:</strong> {course.difficulty}</p>
                    <p><strong>Topic:</strong> {course.category}</p>
                    <p><strong>Session:</strong> {course.session}</p>

                    {userRole === 'admin' && (
                        <p><strong>Course ID:</strong> {course.courseId}</p>
                    )}

                    <div className="provider-selection">
                        <label><strong>Select Provider:</strong></label>
                        {providers.length > 0 ? (
                            <select
                                value={selectedProvider || ''}
                                onChange={(e) => setSelectedProvider(parseInt(e.target.value))}
                            >
                                <option value="">-- Select --</option>
                                {providers.map(p => (
                                    <option key={p.providerId} value={p.providerId}>
                                        {p.providerName} — {p.price} NOK
                                    </option>
                                ))}
                            </select>
                        ) : (
                            <p>Loading providers...</p>
                        )}
                    </div>

                    <button
                        className="add-to-cart-button"
                        onClick={handleAddToCart}
                        disabled={!selectedProvider || isInCart}
                    >
                        {isInCart ? 'Already in Cart' : 'Add to Cart'}
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CourseModal;