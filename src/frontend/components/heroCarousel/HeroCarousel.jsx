import React from 'react';
import Slider from 'react-slick';
import './CarouselStyle.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import personTakingCourse from '../../assets/carouselPictures/personTakingCourse.jpg';
import digitalMarketing from '../../assets/carouselPictures/8.svg';
import business from '../../assets/carouselPictures/business.png';
import ai from '../../assets/carouselPictures/11.svg';
import school from '../../assets/carouselPictures/school.png';
import { Link } from 'react-router-dom';

const slides = [
    {
        image: school ,
        headline: 'Welcome to Learniverse Connect!' ,
        subtext: 'The website for all your learning needs!',
    },
    {
        image: personTakingCourse,
        headline: 'Build Tech Skills with IT Courses',
        subtext: 'Master Java, SQL, .NET, AWS & Azure — industry tools for the modern developer.',
    },
    {
        image: digitalMarketing,
        headline: 'Excel in Digital Marketing',
        subtext: 'Learn SEO, Social Media, and analytics to grow brands in the digital age.',
    },
    {
        image: business,
        headline: 'Strategize Like a Pro',
        subtext: 'Business and entrepreneurship skills for future leaders and innovators.',
    },
    {
        image: ai,
        headline: 'Decode Data Science & AI',
        subtext: 'Use Python, ML & Databricks to analyze, predict, and transform industries.',
    }
];

const HeroCarousel = () => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 5000,
        pauseOnHover: true,
        arrows: true,
        swipe: true,
    };

    return (
        <div className="hero-carousel">
            <Slider {...settings}>
                {slides.map((slide, index) => (
                    <div key={index}>
                        <div
                            className="carousel-slide"
                            style={{ backgroundImage: `url(${slide.image})` }}
                        >
                            <div className="carousel-content">
                                <h1>{slide.headline}</h1>
                                <p>{slide.subtext}</p>
                            </div>
                        </div>
                    </div>
                ))}
            </Slider>
        </div>
    );
};

export default HeroCarousel;