import React from 'react';
import './UserInfo.css';

const UserInfo = ({ name, email}) => {
    return (
        <div className="userInfo">

            <div className="user-details">
                <h2>{name}</h2>
                <p>{email}</p>
            </div>

        </div>
    );
};

export default UserInfo;