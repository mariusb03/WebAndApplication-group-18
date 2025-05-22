import React from 'react';
import './CheckoutModal.css';

const CheckoutModal = ({ message, onClose }) => {
    return (
        <div className="modal-overlay">
            <div className="modal-box">
                <p>{message}</p>
                <button onClick={onClose}>OK</button>
            </div>
        </div>
    );
};

export default CheckoutModal;