import React, {useEffect, useState} from 'react';
import './LoginPage.css';
import { useNavigate, Link } from 'react-router-dom';

const LoginPage = () => {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        const existingUser = JSON.parse(localStorage.getItem('user'));
        if (existingUser) {
            navigate('/profile');
        }
    }, []);

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://129.241.236.99:8082/api/user/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, password })
            });

            if (!response.ok) {
                throw new Error('Invalid credentials');
            }

            const user = await response.json();
            localStorage.setItem('user', JSON.stringify(user));
            navigate('/profile');
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="login-container">
            <h1 className="login-heading">Log In To Your Lerniverse Connect Account!</h1>
            <div className="login-box">
                <form className="login-form" onSubmit={handleLogin}>
                    <div className="login-field">
                        <label htmlFor="name">Name :</label>
                        <input type="text" id="name" value={name} onChange={e => setName(e.target.value)} />
                    </div>
                    <div className="login-field">
                        <label htmlFor="password">Password :</label>
                        <input type="password" id="password" value={password} onChange={e => setPassword(e.target.value)} />
                    </div>
                    {error && <p className="error">{error}</p>}
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