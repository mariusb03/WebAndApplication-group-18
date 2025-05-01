import React, { useState, useEffect, useRef } from 'react';
import Login from '../../assets/logos/profilePic.svg';
import Cart from '../../assets/logos/cartLogo.svg';
import Logo from '../../assets/logos/homeLogo.svg';
import SearchIcon from '../../assets/logos/search symbol.png';
import './TopPartStyle.css';
import 'react-refresh/runtime';
import {Link} from "react-router-dom";

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

    const searchRef = useRef();
    const searchPopupRef = useRef();

    // Handle clicking outside search OR dropdown
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

    // Filter suggestions as the user types
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
        console.log(`User selected: ${text}`);
        // Future: trigger search logic or redirect
    };

    return (
        <>
            <div className={`topPartBox ${isSearchFocused ? 'blurred' : ''}`}>
                {/* Logo */}
                <Link to="/" className="homeLogoButton">
                    <img src={Logo} className="homeLogo" alt="logo" />
                </Link>

                {/* Icons */}
                <div id="LoginAndCartButtons">
                    {/* Search */}
                    <div className="cartLoginBt">
                        <button
                            className="searchIconButton"
                            onClick={() => setIsSearchFocused(prev => !prev)}
                        >
                            <img src={SearchIcon} alt="search" className="searchIconImage" />
                        </button>
                    </div>

                    {/* Cart */}
                    <Link to="/cart" className="cartLoginBt">
                        <button>
                            <img src={Cart} alt="cart" />
                        </button>
                    </Link>

                    {/* Login */}
                    <Link to="/login" className="cartLoginBt">
                        <button>
                            <img src={Login} alt="login" />
                        </button>
                    </Link>
                </div>
            </div>

            {/* Search Popup */}
            {isSearchFocused && (
                <div className="overlay-blur" ref={searchRef}>
                    <div className="search-popup" ref={searchPopupRef}>
                        <input
                            type="text"
                            placeholder="Search for courses"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    if (filteredSuggestions.length > 0) {
                                        handleSuggestionClick(filteredSuggestions[0]);
                                    }
                                }
                            }}
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