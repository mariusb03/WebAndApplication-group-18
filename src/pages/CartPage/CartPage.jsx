import React, { useState } from 'react';
import './CartPage.css';
import { useCart } from '../../context/CartContext';
import CourseModal from '../../components/courseModal/CourseModal';

const CartPage = () => {
    const { cartItems } = useCart();
    const [selectedCourse, setSelectedCourse] = useState(null);

    const totalPrice = cartItems.reduce(
        (sum, item) => sum + Number(item.price),
        0
    );

    return (
        <div className="cart-container">
            <div className="cart-header">
                <h2>Your Cart:</h2>
            </div>

            {cartItems.length === 0 ? (
                <div className="empty-cart-message">
                    <p>Your cart is currently empty</p>
                </div>
            ) : (
                <>
                    <div className="cart-top-box">
                        {cartItems.map((course) => (
                            <div
                                className="course-card"
                                key={course.courseId}
                                onClick={() => setSelectedCourse(course)}
                            >
                                <img
                                    src={`/img/${course.courseId}.svg`}
                                    alt={`Course ${course.title}`}
                                    onError={(e) => { e.target.src = '/img/default.svg'; }}
                                />
                                <div className="course-info">
                                    <h3>{course.title}</h3>
                                    <div className="tag">Topics: {course.category}</div>
                                    <div className="tag">Price: {course.price}</div>
                                    <div className="tag">Schedule: {course.session}</div>
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="price-summary-box">
                        {cartItems.map((course, index) => (
                            <p key={index}>
                                {course.title} : {course.price}
                            </p>
                        ))}
                        <div className="total-box">
                            <span className="label">Total price:</span>
                            <span className="price">{totalPrice.toLocaleString()} NOK</span>
                        </div>
                    </div>
                </>
            )}

            <CourseModal
                course={selectedCourse}
                onClose={() => setSelectedCourse(null)}
            />
        </div>
    );
};

export default CartPage;