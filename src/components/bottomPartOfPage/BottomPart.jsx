import Logo from '../../assets/logos/homeLogo.svg';
import './BottomPartStyle.css';
import { Link } from 'react-router-dom';

function BottomPart() {
    return (
        <div className="bottomBox">
            <div className="bottomContent">
                <Link to="/" className="bottomLogo">
                    <img src={Logo} className="homeLogo" alt="Lerniverse Logo" />
                </Link>

                <div className="map">
                    <iframe
                        title="Lerniverse Location"
                        className="locationMap"
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5525.613780246515!2d6.222416770839368!3d62.46998535838721!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4616dac1b03a4a8b%3A0x5df22844dd93ce98!2sNTNU%20i%20%C3%85lesund!5e0!3m2!1sen!2sno!4v1747837390234!5m2!1sen!2sno"
                        allowFullScreen=""
                        loading="lazy"
                        referrerPolicy="no-referrer-when-downgrade"
                    ></iframe>
                </div>

                <div className="bottomButtons">
                    <Link to="/about" className="bottomNavButton">About Us</Link>
                    <Link to="/contact" className="bottomNavButton">Contact</Link>
                </div>
            </div>
        </div>
    );
}

export default BottomPart;