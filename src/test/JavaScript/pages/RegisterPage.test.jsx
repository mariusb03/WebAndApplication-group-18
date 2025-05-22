import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import RegisterPage from './RegisterPage';
import { MemoryRouter } from 'react-router-dom';

// Mock useNavigate
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => jest.fn()
}));

// Mock fetch
global.fetch = jest.fn(() =>
    Promise.resolve({
        json: () => Promise.resolve([]),
        ok: true,
        text: () => Promise.resolve(''),
    })
);

describe('RegisterPage', () => {
    beforeEach(() => {
        fetch.mockClear();
    });

    it('renders the form fields', () => {
        render(<RegisterPage />, { wrapper: MemoryRouter });

        expect(screen.getByLabelText(/Full Name/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/E-mail/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Password/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Confirm Password/i)).toBeInTheDocument();
        expect(screen.getByText(/Register/i)).toBeInTheDocument();
    });

    it('shows error if fields are empty', async () => {
        render(<RegisterPage />, { wrapper: MemoryRouter });

        fireEvent.click(screen.getByRole('button', { name: /Register/i }));

        expect(await screen.findByText(/All fields are required/i)).toBeInTheDocument();
    });

    it('shows error if passwords do not match', async () => {
        render(<RegisterPage />, { wrapper: MemoryRouter });

        fireEvent.change(screen.getByLabelText(/Full Name/i), { target: { value: 'John Doe' } });
        fireEvent.change(screen.getByLabelText(/E-mail/i), { target: { value: 'john@example.com' } });
        fireEvent.change(screen.getByLabelText(/^Password/i), { target: { value: 'password123' } });
        fireEvent.change(screen.getByLabelText(/Confirm Password/i), { target: { value: 'different123' } });

        fireEvent.click(screen.getByRole('button', { name: /Register/i }));

        expect(await screen.findByText(/Passwords do not match/i)).toBeInTheDocument();
    });

    it('disables the submit button when loading', async () => {
        render(<RegisterPage />, { wrapper: MemoryRouter });

        fireEvent.change(screen.getByLabelText(/Full Name/i), { target: { value: 'John Doe' } });
        fireEvent.change(screen.getByLabelText(/E-mail/i), { target: { value: 'john@example.com' } });
        fireEvent.change(screen.getByLabelText(/^Password/i), { target: { value: 'password123' } });
        fireEvent.change(screen.getByLabelText(/Confirm Password/i), { target: { value: 'password123' } });

        fireEvent.click(screen.getByRole('button', { name: /Register/i }));

        await waitFor(() =>
            expect(screen.getByRole('button', { name: /Registering/i })).toBeDisabled()
        );
    });
});