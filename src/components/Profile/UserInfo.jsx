import React from 'react';
import './UserInfo.css';

const UserInfo = ({ name, email, profilePicture }) => {
    return (
        <div className="userInfo">
            <img src={profilePicture} alt="Profile" className="profile-pic" />
            <div className="user-details">
                <h2>{name}</h2>
                <p>{email}</p>
            </div>
            <button className="edit-button">Edit Profile</button>
        </div>
    );
};

export default UserInfo;