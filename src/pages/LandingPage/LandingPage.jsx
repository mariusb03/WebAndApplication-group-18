import React from 'react';
import HeroCarousel from '../../components/heroCarousel/HeroCarousel';
import './LandingPage.css';

import { useNavigate, Link } from 'react-router-dom';

const LandingPage = () => {
    return (
        <div className="landing-container">
            <HeroCarousel />

            <div className="landing-browse-section">
                <Link to="/courses" className="browse-button">
                    Browse Courses
                </Link>
            </div>
        </div>
    );
};

export default LandingPage;