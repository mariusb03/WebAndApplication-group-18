
import React, { createContext, useContext, useState } from 'react';



const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
    const [cartItems, setCartItems] = useState([]);

    const addToCart = (course, provider) => {
        const courseWithProvider = {
            ...course,
            selectedProvider: provider
        };
        setCartItems((prev) => [...prev, courseWithProvider]);
    };

    const removeFromCart = (courseId, providerId) => {
        setCartItems((prev) =>
            prev.filter(item =>
                item.courseId !== courseId || item.selectedProvider.providerId !== providerId
            )
        );
    };

    const clearCart = () => setCartItems([]);

    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
            {children}
        </CartContext.Provider>
    );
};