import React from 'react';
import './CartPage.css';
import { useCart } from '../../context/CartContext';

import 'react-refresh/runtime';

const CartPage = () => {
    const { cartItems } = useCart();
    const totalPrice = cartItems.reduce(
        (sum, item) => sum + parseInt(item.price.replace(/\D/g, '')),
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
                            <div className="course-card" key={course.id}>
                                <img src={course.image} alt={course.title} />
                                <div className="course-info">
                                    <h3>{course.title}</h3>
                                    <div className="tag">Topics: {course.topic}</div>
                                    <div className="tag">Price: {course.price}</div>
                                    <div className="tag">Schedule: {course.sessions}</div>
                                    <div className="tag">Description: {course.description}</div>
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
        </div>
    );
};

export default CartPage;