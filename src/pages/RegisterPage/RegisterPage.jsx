import React, { useState } from 'react';
import './RegisterPage.css';
import { Link, useNavigate } from 'react-router-dom';

const RegisterPage = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.id]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const { name, email, password, confirmPassword } = formData;

        if (!name || !email || !password || !confirmPassword) {
            setError('All fields are required.');
            return;
        }

        if (password !== confirmPassword) {
            setError('Passwords do not match.');
            return;
        }

        setLoading(true);

        try {
            // Step 1: Fetch all users to count
            const userResponse = await fetch('http://http://[2001:700:300:6018:f816:3eff:feb9:e1db]:8082//user/getAll');
            const users = await userResponse.json();

            const newUserId = (Array.isArray(users) ? users.length : 0) + 1;

            // Step 2: Send registration request
            const registerResponse = await fetch('http://http://[2001:700:300:6018:f816:3eff:feb9:e1db]:8082//user/add', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    userId: newUserId,  // manually assign ID
                    name,
                    email,
                    password,
                    role: 'user'
                })
            });

            if (registerResponse.ok) {
                alert('Account created successfully! Please log in.');
                navigate('/login');
            } else {
                const message = await registerResponse.text();
                setError(message || 'Registration failed.');
            }
        } catch (err) {
            console.error(err);
            setError('An error occurred during registration.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="register-container">
            <h1 className="register-heading">Create Your Lerniverse Connect Account</h1>

            <div className="register-box">
                <form className="register-form" onSubmit={handleSubmit}>
                    <div className="register-field">
                        <label htmlFor="name">Full Name :</label>
                        <input type="text" id="name" placeholder="Your full name" onChange={handleChange} />
                    </div>

                    <div className="register-field">
                        <label htmlFor="email">E-mail :</label>
                        <input type="email" id="email" placeholder="Enter your email" onChange={handleChange} />
                    </div>

                    <div className="register-field">
                        <label htmlFor="password">Password :</label>
                        <input type="password" id="password" placeholder="Create a password" onChange={handleChange} />
                    </div>

                    <div className="register-field">
                        <label htmlFor="confirmPassword">Confirm Password :</label>
                        <input type="password" id="confirmPassword" placeholder="Repeat your password" onChange={handleChange} />
                    </div>

                    {error && <p className="error-message">{error}</p>}

                    <div className="register-buttons">
                        <button type="submit" className="btn submit" disabled={loading}>
                            {loading ? 'Registering...' : 'Register'}
                        </button>
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