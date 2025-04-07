import React from 'react';
import Slider from 'react-slick';
import './CarouselStyle.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import personTakingCourse from '../assets/carouselPictures/personTakingCourse.jpg';

const slides = [
    {
        image: personTakingCourse,
        headline: 'Master Data Analytics with Databricks',
        subtext: 'Learn from the best in the field — practical, project-based, and powerful.',
    },
    {
        image: 'https://miro.medium.com/v2/resize:fit:1358/1*cG6U1qstYDijh9bPL42e-Q.jpeg',
        headline: 'AI & Machine Learning with Python',
        subtext: 'Beginner-friendly, real-world projects, and job-ready skills.',
    },
    {
        image: 'https://digitaltransformation.org.au/sites/default/files/2022-01/azure.png',
        headline: 'Azure Certified in Weeks',
        subtext: 'Cloud skills are in demand. Start your journey with Learniverse Connect.',
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