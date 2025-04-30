import React from 'react';
import './LoginPage.css';
import { Link } from 'react-router-dom';
import registerPage from "../RegisterPage/RegisterPage";

const LoginPage = () => {
    return (
        <div className="login-container">
            <h1 className="login-heading">Log In To Your Lerniverse Connect Account!</h1>

            <div className="login-box">
                <form className="login-form">
                    <div className="login-field">
                        <label htmlFor="email">Username/E-mail :</label>
                        <input type="text" id="email" placeholder="Enter your email" />
                    </div>

                    <div className="login-field">
                        <label htmlFor="password">Password :</label>
                        <input type="password" id="password" placeholder="Enter your password" />
                    </div>

                    <div className="login-buttons">

                        <Link to="/register">
                        <button type="button" className="btn register">Register</button>
                        </Link>

                        <button type="submit" className="btn login">Log in</button>

                    </div>
                </form>
            </div>
        </div>
    );
};

export default LoginPage;