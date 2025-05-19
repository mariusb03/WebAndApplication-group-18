const CartContext = (() => {
    let cartItems = [];

    const addToCart = (course) => {
        cartItems.push(course);
        saveToStorage();
    };

    const removeFromCart = (id) => {
        cartItems = cartItems.filter(item => item.id !== id);
        saveToStorage();
    };

    const clearCart = () => {
        cartItems = [];
        saveToStorage();
    };

    const getCartItems = () => {
        loadFromStorage();
        return cartItems;
    };

    const saveToStorage = () => {
        localStorage.setItem('cartItems', JSON.stringify(cartItems));
    };

    const loadFromStorage = () => {
        const saved = localStorage.getItem('cartItems');
        cartItems = saved ? JSON.parse(saved) : [];
    };

    // Initialize from storage on load
    loadFromStorage();

    return {
        addToCart,
        removeFromCart,
        clearCart,
        getCartItems
    };
})();