import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import TopPartOfPage from '../../components/TopPartOfPage/TopPartOfPage';
import { BrowserRouter } from 'react-router-dom';
import { CartProvider } from '../../context/CartContext';
import * as test from "node:test";

// Mock navigate function from react-router-dom
const mockedUsedNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockedUsedNavigate,
}));

const renderWithContext = (component) => {
    return render(
        <CartProvider>
            <BrowserRouter>{component}</BrowserRouter>
        </CartProvider>
    );
};

describe('TopPartOfPage', () => {
    beforeEach(() => {
        localStorage.clear();
        mockedUsedNavigate.mockClear();
    });

    test('renders logo and buttons', () => {
        renderWithContext(<TopPartOfPage />);
        expect(screen.getByAltText(/logo/i)).toBeInTheDocument();
        expect(screen.getByAltText(/cart/i)).toBeInTheDocument();
        expect(screen.getByAltText(/login/i)).toBeInTheDocument();
        expect(screen.getByAltText(/search/i)).toBeInTheDocument();
    });

    test('toggles search input on search icon click', () => {
        renderWithContext(<TopPartOfPage />);
        const searchButton = screen.getByAltText(/search/i);
        fireEvent.click(searchButton);
        expect(screen.getByPlaceholderText(/search for courses/i)).toBeInTheDocument();
    });

    test('displays suggestions and navigates on click', () => {
        renderWithContext(<TopPartOfPage />);
        const searchButton = screen.getByAltText(/search/i);
        fireEvent.click(searchButton);

        const suggestion = screen.getByText(/web development/i);
        fireEvent.click(suggestion);
        expect(mockedUsedNavigate).toHaveBeenCalledWith('/courses?search=Web%20Development');
    });

    test('navigates when Enter is pressed in input', () => {
        renderWithContext(<TopPartOfPage />);
        fireEvent.click(screen.getByAltText(/search/i));

        const input = screen.getByPlaceholderText(/search for courses/i);
        fireEvent.change(input, { target: { value: 'Python' } });
        fireEvent.keyDown(input, { key: 'Enter', code: 'Enter' });

        expect(mockedUsedNavigate).toHaveBeenCalledWith('/courses?search=Python');
    });

    test('shows cart badge when items are present', () => {
        // Pre-fill cartItems in context
        const MockComponent = () => {
            const { addToCart } = require('../../context/CartContext').useCart();
            React.useEffect(() => {
                addToCart({ courseId: 1, title: 'Test Course' });
            }, []);
            return <TopPartOfPage />;
        };

        renderWithContext(<MockComponent />);
        expect(screen.getByText('1')).toBeInTheDocument();
    });
});