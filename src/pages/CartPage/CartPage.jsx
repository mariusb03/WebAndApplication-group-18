import React, { useState } from 'react';
import './CartPage.css';
import { useCart } from '../../context/CartContext';
import CourseModal from '../../components/courseModal/CourseModal';
import { useNavigate } from 'react-router-dom';
import CheckoutModal from "../../components/CheckoutModal/CheckoutModal";

const CartPage = () => {
    const { cartItems, clearCart } = useCart();
    const [selectedCourse, setSelectedCourse] = useState(null);
    const [showPopup, setShowPopup] = useState(false);
    const navigate = useNavigate();

    const handleCheckout = async () => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        if (!storedUser) return;

        try {
            for (const course of cartItems) {
                await fetch(`http://http://[2001:700:300:6018:f816:3eff:feb9:e1db]:8082//api/courses/${course.courseId}/user/${storedUser.userId}`, {
                    method: 'POST',
                });
            }

            clearCart();
            setShowPopup(true);
        } catch (error) {
            console.error('Checkout failed:', error);
            alert('Checkout failed. Please try again.');
        }
    };

    const totalPrice = cartItems.reduce(
        (sum, item) => sum + Number(item.selectedProvider?.price || 0),
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
                                    <div className="tag">Provider: {course.selectedProvider.providerName}</div>
                                    <div className="tag">Price: {course.selectedProvider.price} NOK</div>
                                    <div className="tag">Schedule: {course.session}</div>
                                </div>
                            </div>
                        ))}
                    </div>

                    <div className="price-summary-box">
                        {cartItems.map((course, index) => (
                            <div key={index}>
                                <p>{course.title} ({course.selectedProvider.providerName}) : {course.selectedProvider.price} NOK</p>
                            </div>
                        ))}

                        <div className="total-box">
                            <span className="label">Total price:</span>
                            <span className="price">{totalPrice.toLocaleString()} NOK</span>
                        </div>

                        <div className="checkout-box">
                            <button
                                className="checkout-button"
                                onClick={handleCheckout}
                                disabled={cartItems.length === 0}
                            >
                                Proceed to Checkout
                            </button>
                        </div>
                    </div>
                </>
            )}

            <CourseModal
                course={selectedCourse}
                onClose={() => setSelectedCourse(null)}
            />

            {showPopup && (
                <CheckoutModal
                    message="Thank you for your purchase! The courses have been added to your enrolled courses list in your profile."
                    onClose={() => {
                        setShowPopup(false);
                        navigate('/profile');
                    }}
                />
            )}
        </div>
    );
};

export default CartPage;