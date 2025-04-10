import React from 'react';
import './CartPage.css';
import exampleImage from '../../assets/coursPictures/SEO.png'; // replace with real path

const cartItems = [
    {
        id: 1,
        title: 'Search Engine Optimization',
        image: exampleImage,
        price: 10000,
        topics: 'SEO, Marketing',
        schedule: 'May - June 2025',
        description: 'Learn how to boost site visibility with SEO best practices.',
    },
    {
        id: 2,
        title: 'Course A',
        price: 10000,
    },
    {
        id: 3,
        title: 'Course B',
        price: 10000,
    },
    {
        id: 4,
        title: 'Course C',
        price: 10000,
    },
];

const CartPage = () => {
    const totalPrice = cartItems.reduce((sum, item) => sum + item.price, 0);

    return (
        <div className="cart-container">
            <div className="cart-header">
                <h2>Your Cart:</h2>
            </div>

            <div className="cart-top-box">
                {cartItems.slice(0, 1).map((course) => (
                    <div className="course-card" key={course.id}>
                        <img src={course.image} alt={course.title} />
                        <div className="course-info">
                            <h3>{course.title}</h3>
                            <div className="tag">Topics: {course.topics}</div>
                            <div className="tag">Price: {course.price} NOK</div>
                            <div className="tag">Schedule: {course.schedule}</div>
                            <div className="tag">Description: {course.description}</div>
                        </div>
                    </div>
                ))}
            </div>

            <div className="price-summary-box">
                {cartItems.map((course, index) => (
                    <p key={index}>
                        {course.title} : {course.price.toLocaleString()} NOK
                    </p>
                ))}
                <div className="total-box">
                    <span className="label">Total price:</span>
                    <span className="price">{totalPrice.toLocaleString()} NOK</span>
                </div>
            </div>
        </div>
    );
};

export default CartPage;