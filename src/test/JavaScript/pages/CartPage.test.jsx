import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import CartPage from '../../../src/pages/CartPage/CartPage';
import { useCart } from '../../../src/context/CartContext';
import { MemoryRouter } from 'react-router-dom';
import * as test from "node:test";

// Mock navigate
const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
}));

// Mock modal components
jest.mock('../../../src/components/courseModal/CourseModal', () => () => <div data-testid="course-modal" />);
jest.mock('../../../src/components/CheckoutModal/CheckoutModal', () => ({ message, onClose }) => (
    <div data-testid="checkout-modal">
        {message}
        <button onClick={onClose}>Close</button>
    </div>
));

// Mock useCart
jest.mock('../../../src/context/CartContext', () => ({
    useCart: jest.fn()
}));

const mockCartItem = {
    courseId: 1,
    title: 'React Basics',
    category: 'Web Dev',
    session: 'June',
    selectedProvider: {
        providerName: 'Code Academy',
        price: 999
    }
};

describe('CartPage', () => {
    beforeEach(() => {
        localStorage.setItem('user', JSON.stringify({ userId: 1, name: 'Test User' }));
        useCart.mockReturnValue({
            cartItems: [mockCartItem],
            clearCart: jest.fn()
        });
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({})
            })
        );
    });

    test('renders cart items and total', () => {
        render(<CartPage />, { wrapper: MemoryRouter });

        expect(screen.getByText(/React Basics/i)).toBeInTheDocument();
        expect(screen.getByText(/Total price:/i)).toBeInTheDocument();
        expect(screen.getByText(/999 NOK/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /Proceed to Checkout/i })).toBeInTheDocument();
    });

    test('handles empty cart case', () => {
        useCart.mockReturnValueOnce({
            cartItems: [],
            clearCart: jest.fn()
        });

        render(<CartPage />, { wrapper: MemoryRouter });
        expect(screen.getByText(/Your cart is currently empty/i)).toBeInTheDocument();
    });

    test('successfully checks out and shows modal', async () => {
        const clearCartMock = jest.fn();
        useCart.mockReturnValueOnce({
            cartItems: [mockCartItem],
            clearCart: clearCartMock
        });

        render(<CartPage />, { wrapper: MemoryRouter });

        fireEvent.click(screen.getByRole('button', { name: /Proceed to Checkout/i }));

        await waitFor(() => {
            expect(clearCartMock).toHaveBeenCalled();
            expect(screen.getByTestId('checkout-modal')).toBeInTheDocument();
        });

        fireEvent.click(screen.getByText('Close'));

        await waitFor(() => {
            expect(mockNavigate).toHaveBeenCalledWith('/profile');
        });
    });

    test('handles fetch error gracefully', async () => {
        global.fetch = jest.fn(() => Promise.reject(new Error('Failed')));

        render(<CartPage />, { wrapper: MemoryRouter });

        window.alert = jest.fn(); // mock alert
        fireEvent.click(screen.getByRole('button', { name: /Proceed to Checkout/i }));

        await waitFor(() => {
            expect(window.alert).toHaveBeenCalledWith('Checkout failed. Please try again.');
        });
    });
});