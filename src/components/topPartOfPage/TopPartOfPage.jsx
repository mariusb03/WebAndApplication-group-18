import React, { useState, useEffect, useRef } from 'react';
import Login from '../../assets/logos/profilePic.svg';
import Cart from '../../assets/logos/cartLogo.svg';
import Logo from '../../assets/logos/homeLogo.svg';
import './TopPartStyle.css';
import 'react-refresh/runtime';

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
    const [dropdownOpen, setDropdownOpen] = useState(false);

    const searchRef = useRef();
    const dropdownRef = useRef();

    // Handle clicking outside search OR dropdown
    useEffect(() => {
        function handleClickOutside(event) {
            if (
                searchRef.current && !searchRef.current.contains(event.target) &&
                dropdownRef.current && !dropdownRef.current.contains(event.target)
            ) {
                setIsSearchFocused(false);
                setDropdownOpen(false);
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
        setSearchQuery(text);
        setIsSearchFocused(false);
        console.log(`User selected: ${text}`);
        // Future: trigger search logic or redirect
    };

    return (
        <>
            {isSearchFocused && <div className="overlay-blur" />}

            <div className={`topPartBox ${isSearchFocused ? 'blurred' : ''}`}>
                {/* Logo */}
                <button className="homeLogoButton">
                    <img src={Logo} className="homeLogo" alt="logo" />
                </button>

                {/* Search Field */}
                <div id="SearchField" className="searchFieldCenter" ref={searchRef}>
                    <input
                        type="text"
                        placeholder="Search for courses"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        onFocus={() => setIsSearchFocused(true)}
                    />
                    {isSearchFocused && filteredSuggestions.length > 0 && (
                        <div className="relatedCoursesBox">
                            <p>Suggestions:</p>
                            <ul>
                                {filteredSuggestions.map((suggestion, index) => (
                                    <li key={index} onClick={() => handleSuggestionClick(suggestion)}>
                                        {suggestion}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    )}
                </div>

                {/* Navigation */}
                <div className="navButtons">
                    <div className="dropdown" ref={dropdownRef}>
                        <button className="navButton" onClick={() => setDropdownOpen(prev => !prev)}>
                            Courses ▼
                        </button>
                        {dropdownOpen && (
                            <div className="dropdownMenu">
                                <button>Information Technologies</button>
                                <button>Digital Marketing</button>
                                <button>Business and Entrepreneurship</button>
                                <button>Data Science and Analytics</button>
                            </div>
                        )}
                    </div>
                    <button className="navButton">About Us</button>
                </div>

                {/* Login / Cart */}
                <div id="LoginAndCartButtons">
                    <div className="cartLoginBt">
                        <button><img src={Login} alt="login" /></button>
                    </div>
                    <div className="cartLoginBt">
                        <button><img src={Cart} alt="cart" /></button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default TopPartOfPage;