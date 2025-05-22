import { Link } from 'react-router-dom';
import './NotFoundPage.css';

const NotFoundPage = () => {
    return (
        <div className="not-found-container">
            <h1>404</h1>
            <h2>Page Not Found</h2>
            <p>Oops! The page you're looking for doesn't exist.</p>
            <Link to="/" className="back-home-button">
                ⬅️ Go back to Home
            </Link>
        </div>
    );
};

export default NotFoundPage;