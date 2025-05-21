import React from 'react';
import Slider from 'react-slick';
import './CarouselStyle.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import personTakingCourse from '../../assets/carouselPictures/personTakingCourse.jpg';
import { Link } from 'react-router-dom';

const slides = [
    {
        image: personTakingCourse,
        headline: 'Build Tech Skills with IT Courses',
        subtext: 'Master Java, SQL, .NET, AWS & Azure — industry tools for the modern developer.',
    },
    {
        image: 'https://miro.medium.com/v2/resize:fit:1358/1*cG6U1qstYDijh9bPL42e-Q.jpeg',
        headline: 'Excel in Digital Marketing',
        subtext: 'Learn SEO, Social Media, and analytics to grow brands in the digital age.',
    },
    {
        image: 'https://digitaltransformation.org.au/sites/default/files/2022-01/azure.png',
        headline: 'Strategize Like a Pro',
        subtext: 'Business and entrepreneurship skills for future leaders and innovators.',
    },
    {
        image: 'https://cdn.analyticsvidhya.com/wp-content/uploads/2023/04/Databricks.jpg',
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