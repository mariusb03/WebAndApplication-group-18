import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import CourseModal from '../../components/courseModal/CourseModal';
import { CartProvider, useCart } from '../../context/CartContext';
import { BrowserRouter } from 'react-router-dom';
import * as test from "node:test";

// Mock navigate
const mockedNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockedNavigate,
}));

// Set up localStorage mock
beforeEach(() => {
    localStorage.setItem('user', JSON.stringify({ userId: 1, role: 'user' }));
    global.fetch = jest.fn(() =>
        Promise.resolve({
            json: () =>
                Promise.resolve([
                    { providerId: 1, providerName: 'Provider A', price: 1000 },
                    { providerId: 2, providerName: 'Provider B', price: 1500 }
                ])
        })
    );
});

afterEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
});

const mockCourse = {
    courseId: 101,
    title: 'Test Course',
    difficulty: 'Intermediate',
    category: 'Development',
    session: 'Jan 1 – Feb 1',
    description: 'A great course.'
};

const renderWithProviders = (component) => {
    return render(
        <CartProvider>
            <BrowserRouter>
                {component}
            </BrowserRouter>
        </CartProvider>
    );
};

describe('CourseModal', () => {
    test('renders modal with course details', async () => {
        renderWithProviders(<CourseModal course={mockCourse} onClose={() => {}} />);

        expect(screen.getByText('Test Course')).toBeInTheDocument();
        expect(screen.getByText(/Intermediate/)).toBeInTheDocument();
        expect(screen.getByText(/Development/)).toBeInTheDocument();
        expect(screen.getByText(/Jan 1 – Feb 1/)).toBeInTheDocument();

        await waitFor(() => {
            expect(screen.getByText(/Provider A — 1000 NOK/)).toBeInTheDocument();
        });
    });

    test('disables Add to Cart button when no provider is selected', async () => {
        renderWithProviders(<CourseModal course={mockCourse} onClose={() => {}} />);
        const button = await screen.findByText('Add to Cart');
        expect(button).toBeDisabled();
    });

    test('enables Add to Cart button when provider is selected', async () => {
        renderWithProviders(<CourseModal course={mockCourse} onClose={() => {}} />);
        const select = await screen.findByRole('combobox');
        fireEvent.change(select, { target: { value: '1' } });

        const button = screen.getByText('Add to Cart');
        expect(button).toBeEnabled();
    });

    test('calls addToCart and closes modal on click', async () => {
        const onClose = jest.fn();
        renderWithProviders(<CourseModal course={mockCourse} onClose={onClose} />);

        const select = await screen.findByRole('combobox');
        fireEvent.change(select, { target: { value: '1' } });

        const button = screen.getByText('Add to Cart');
        fireEvent.click(button);

        await waitFor(() => {
            expect(onClose).toHaveBeenCalled();
        });
    });

    test('redirects to login if user not in localStorage', async () => {
        localStorage.removeItem('user');

        renderWithProviders(<CourseModal course={mockCourse} onClose={() => {}} />);
        const select = await screen.findByRole('combobox');
        fireEvent.change(select, { target: { value: '1' } });

        fireEvent.click(screen.getByText('Add to Cart'));

        await waitFor(() => {
            expect(mockedNavigate).toHaveBeenCalledWith('/login');
        });
    });
});