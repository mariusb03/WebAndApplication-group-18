import './BottomPartStyle.css';
import { Link } from 'react-router-dom';

function BottomPart() {
    return (
        <footer className="footer">
            <div className="footer-columns">
                <div className="footer-column links">
                    <h3>Links</h3>
                    <ul>
                        <li><a href="https://github.com/mariusb03/WebAndApplication-group-18/tree/develop">GitHub</a></li>
                        <li><a href="https://www.ntnu.no/">NTNU</a></li>
                        <li><Link to="/about">About Us</Link></li>
                    </ul>
                </div>

                <div className="footer-column contact">
                    <h3>Contact</h3>
                    <p><a href="mailto:contact@lerniverse.com">contact@lerniverse.com</a></p>
                    <p>Main office:</p>
                    <div className="footer-map-wrapper">
                        <iframe
                            title="NTNU Ålesund Location"
                            className="footer-map"
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d21631.88153727769!2d6.24252
                            4377172857!3d62.46780808675252!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x461¨
                            6dac1b03a4a8b%3A0x5df22844dd93ce98!2sNTNU%20i%20%C3%85lesund!5e1!3m2!1sen!2sno!4v174787
                            1231022!5m2!1sen!2sno"
                            allowFullScreen=""
                            loading="lazy"
                            referrerPolicy="no-referrer-when-downgrade"
                        ></iframe>
                    </div>
                </div>

                <div className="footer-column creators">
                    <h3>Creators</h3>
                    <ul>
                        <li>Sivert Sulebakk</li>
                        <li>Marius Bringsvor Rusten</li>
                    </ul>
                </div>
            </div>

            <div className="footer-bottom">
                <p>© 2025 Lerniverse Connect</p>
                <small>
                    * This website is a result of a university group project, performed in the course IDATA2301
                    Web Technologies and IDATA2302 App Development, at NTNU Ålesund.
                    All the information provided here is a result of imagination.
                    Any resemblance with real companies or products is a coincidence.
                </small>
            </div>
        </footer>
    );
}

export default BottomPart;