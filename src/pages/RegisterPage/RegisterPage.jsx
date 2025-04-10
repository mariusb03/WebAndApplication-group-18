import React from 'react';
import './RegisterPage.css';
import { Link } from 'react-router-dom';

const RegisterPage = () => {
    return (
        <div className="register-container">
            <h1 className="register-heading">Create Your Lerniverse Connect Account</h1>

            <div className="register-box">
                <form className="register-form">
                    <div className="register-field">
                        <label htmlFor="name">Full Name :</label>
                        <input type="text" id="name" placeholder="Your full name"/>
                    </div>

                    <div className="register-field">
                        <label htmlFor="email">E-mail :</label>
                        <input type="email" id="email" placeholder="Enter your email"/>
                    </div>

                    <div className="register-field">
                        <label htmlFor="password">Password :</label>
                        <input type="password" id="password" placeholder="Create a password"/>
                    </div>

                    <div className="register-field">
                        <label htmlFor="confirmPassword">Confirm Password :</label>
                        <input type="password" id="confirmPassword" placeholder="Repeat your password"/>
                    </div>

                    <div className="register-buttons">
                        <button type="submit" className="btn submit">Register</button>
                    </div>

                    <p className="login-redirect">
                        Already have an account? <Link to="/login">Log in here</Link>
                    </p>
                </form>
            </div>
        </div>
    );
};

export default RegisterPage;