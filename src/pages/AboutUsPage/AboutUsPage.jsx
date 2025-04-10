import React from 'react';
import './AboutUsPage.css'; // We'll write this next

const AboutUsPage = () => {
    return (
        <div className="about-container">
            <h1 className="about-heading">About Learniverse Connect!</h1>

            <section className="about-section">
                <p>
                    Welcome to <strong>Learniverse Connect</strong>, your premier destination for unlocking a world of knowledge and skills through our dynamic online course marketplace. At Learniverse, we believe that learning knows no bounds. Our platform is built to empower individuals like you to embark on a journey of lifelong learning.
                </p>
                <p>
                    As a marketplace, we unite a diverse range of courses from passionate, expert third-party providers — ensuring you have access to a wide array of subjects and skills for both personal and professional growth.
                </p>
            </section>

            <section className="about-section">
                <p>
                    Our commitment to quality is unwavering. We carefully curate each course to ensure a premium learning experience. Whether you're a budding entrepreneur diving into business strategy, or a creative thinker exploring the arts — <strong>Learniverse Connect</strong> is your trusted learning companion.
                </p>
                <p>
                    Join a vibrant community of learners. Connect with top-notch instructors. Explore a rich tapestry of knowledge — because here, learning is not a destination, it’s a continuous journey.
                </p>
            </section>

            <section className="about-section">
                <p>
                    Learniverse Connect also offers <strong>certifications</strong> to validate your expertise. After completing any course, you can take the corresponding exam to earn a certificate.
                </p>
                <p>
                    And if you don’t pass on the first try — don’t worry. We offer a <strong>money-back guarantee</strong>. Your success is our commitment, and we proudly stand behind the quality of every course we offer.
                </p>
            </section>

            <section className="about-section">
                <p>
                    While our courses are mostly online, we add a unique human touch. Every course is guided by a <strong>real, dedicated instructor</strong>, and includes optional workshop sessions for hands-on learning.
                </p>
                <p>
                    Courses are organized on specific dates to accommodate these workshops — and they’re repeated several times throughout the year for maximum flexibility. You choose when you thrive.
                </p>
            </section>
        </div>
    );
};

export default AboutUsPage;