import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from "react-router-dom";
import { useCart } from '../../context/CartContext';
import Login from '../../assets/logos/profilePic.svg';
import Cart from '../../assets/logos/cartLogo.svg';
import Logo from '../../assets/logos/homeLogo.svg';
import SearchIcon from '../../assets/logos/search symbol.png';
import './TopPartStyle.css';


const courseSuggestions = [
    'Web Development',
    'AI & Machine Learning',
    'Cloud & DevOps',
    'Digital Marketing',
    'Business Analytics',
    'Cybersecurity',
    'UI/UX Design',
    'Python Programming',
    'Data Science',
];

function TopPartOfPage() {
    const [searchQuery, setSearchQuery] = useState('');
    const [filteredSuggestions, setFilteredSuggestions] = useState([]);
    const [isSearchFocused, setIsSearchFocused] = useState(false);

    const navigate = useNavigate();
    const { cartItems } = useCart();

    const searchRef = useRef();
    const searchPopupRef = useRef();

    useEffect(() => {
        function handleClickOutside(event) {
            if (
                searchPopupRef.current &&
                !searchPopupRef.current.contains(event.target)
            ) {
                setIsSearchFocused(false);
                setSearchQuery('');
            }
        }

        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, []);

    useEffect(() => {
        if (searchQuery.trim() === '') {
            setFilteredSuggestions(courseSuggestions);
        } else {
            const filtered = courseSuggestions.filter(course =>
                course.toLowerCase().includes(searchQuery.toLowerCase())
            );
            setFilteredSuggestions(filtered);
        }
    }, [searchQuery]);

    const handleSuggestionClick = (text) => {
        setSearchQuery('');
        setIsSearchFocused(false);
        navigate(`/courses?search=${encodeURIComponent(text)}`);
    };

    const handleEnterPress = (e) => {
        if (e.key === 'Enter' && searchQuery.trim() !== '') {
            navigate(`/courses?search=${encodeURIComponent(searchQuery.trim())}`);
            setSearchQuery('');
            setIsSearchFocused(false);
        }
    };

    return (
        <>
            <div className={`topPartBox ${isSearchFocused ? 'blurred' : ''}`}>
                <Link to="/" className="homeLogoButton">
                    <img src={Logo} className="homeLogo" alt="logo" />
                </Link>

                <div id="LoginAndCartButtons">
                    <div className="cartLoginBt">
                        <button
                            className="searchIconButton"
                            onClick={() => setIsSearchFocused(prev => !prev)}
                        >
                            <img src={SearchIcon} alt="search" className="searchIconImage" />
                        </button>
                    </div>

                    <Link to="/cart" className="cartLoginBt cart-icon-wrapper">
                        <button>
                            <img src={Cart} alt="cart" />
                            {cartItems.length > 0 && (
                                <span className="cart-badge">{cartItems.length}</span>
                            )}
                        </button>
                    </Link>

                    <Link to="/login" className="cartLoginBt">
                        <button>
                            <img src={Login} alt="login" />
                        </button>
                    </Link>
                </div>
            </div>

            {isSearchFocused && (
                <div className="overlay-blur" ref={searchRef}>
                    <div className="search-popup" ref={searchPopupRef}>
                        <input
                            type="text"
                            placeholder="Search for courses"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            onKeyDown={handleEnterPress}
                            autoFocus
                            style={{
                                width: '100%',
                                padding: '0.75rem',
                                fontSize: '1rem',
                                borderRadius: '25px',
                                border: '1px solid #ccc',
                                marginBottom: '1rem',
                            }}
                        />
                        {filteredSuggestions.length > 0 && (
                            <div>
                                <p style={{ fontWeight: 'bold' }}>Suggestions:</p>
                                <ul style={{ listStyle: 'none', padding: 0 }}>
                                    {filteredSuggestions.map((suggestion, index) => (
                                        <li
                                            key={index}
                                            onClick={() => handleSuggestionClick(suggestion)}
                                            style={{
                                                padding: '0.5rem',
                                                borderRadius: '6px',
                                                cursor: 'pointer',
                                            }}
                                            onMouseEnter={(e) =>
                                                (e.currentTarget.style.backgroundColor = '#ffe600')
                                            }
                                            onMouseLeave={(e) =>
                                                (e.currentTarget.style.backgroundColor = 'transparent')
                                            }
                                        >
                                            {suggestion}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                </div>
            )}
        </>
    );
}

export default TopPartOfPage;