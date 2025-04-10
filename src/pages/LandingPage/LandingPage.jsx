import React from 'react';
import HeroCarousel from '../../components/heroCarousel/HeroCarousel';
import './LandingPage.css'; // We'll add this next

const LandingPage = () => {
    return (
        <div className="landing-container">
            <div className="landing-header">
                <h1>
                    Welcome to <br />
                    <span>Learniverse Connect!</span>
                </h1>
            </div>
            <HeroCarousel />
        </div>
    );
};

export default LandingPage;