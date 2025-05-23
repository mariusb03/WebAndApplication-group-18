import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import LoginPage from '../../../src/pages/LoginPage/LoginPage';
import * as test from "node:test";

const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate,
    Link: ({ to, children }) => <a href={to}>{children}</a>
}));

describe('LoginPage', () => {
    beforeEach(() => {
        localStorage.clear();
        jest.clearAllMocks();
    });

    test('renders login form correctly', () => {
        render(<LoginPage />, { wrapper: MemoryRouter });

        expect(screen.getByLabelText(/name/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /log in/i })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /register/i })).toBeInTheDocument();
    });

    test('redirects if user is already logged in', () => {
        localStorage.setItem('user', JSON.stringify({ name: 'testuser' }));

        render(<LoginPage />, { wrapper: MemoryRouter });

        expect(mockNavigate).toHaveBeenCalledWith('/profile');
    });

    test('logs in successfully', async () => {
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({ name: 'testuser', userId: 1 })
            })
        );

        render(<LoginPage />, { wrapper: MemoryRouter });

        fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'testuser' } });
        fireEvent.change(screen.getByLabelText(/password/i), { target: { value: '1234' } });
        fireEvent.click(screen.getByRole('button', { name: /log in/i }));

        await waitFor(() => {
            expect(localStorage.getItem('user')).toContain('testuser');
            expect(mockNavigate).toHaveBeenCalledWith('/profile');
        });
    });

    test('shows error on failed login', async () => {
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: false
            })
        );

        render(<LoginPage />, { wrapper: MemoryRouter });

        fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'wronguser' } });
        fireEvent.change(screen.getByLabelText(/password/i), { target: { value: 'wrongpass' } });
        fireEvent.click(screen.getByRole('button', { name: /log in/i }));

        await waitFor(() => {
            expect(screen.getByText(/invalid credentials/i)).toBeInTheDocument();
        });
    });
});