import React from 'react';
import { render, screen, waitFor, act, fireEvent } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import ProfilePage from '../../../src/pages/ProfilePage/ProfilePage';
import * as test from "node:test";

// Mock localStorage
const mockUser = {
    userId: 1,
    name: 'Test User',
    email: 'test@example.com'
};

beforeEach(() => {
    localStorage.setItem('user', JSON.stringify(mockUser));

    global.fetch = jest.fn((url) => {
        if (url.includes(`/api/courses/user/${mockUser.userId}`)) {
            return Promise.resolve({
                json: () => Promise.resolve([{ courseId: 1, title: 'Java', price: 999 }])
            });
        }

        if (url.includes(`/user/${mockUser.userId}/favourites`)) {
            return Promise.resolve({
                json: () => Promise.resolve([{ courseId: 2, title: 'React', price: 888 }])
            });
        }

        return Promise.reject(new Error('Unknown endpoint'));
    });
});

afterEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
});

test('renders user info and course lists', async () => {
    await act(async () => {
        render(<ProfilePage />, { wrapper: MemoryRouter });
    });

    expect(screen.getByText(/Test User/i)).toBeInTheDocument();
    expect(screen.getByText(/test@example.com/i)).toBeInTheDocument();

    await waitFor(() => {
        expect(screen.getByText(/Java/i)).toBeInTheDocument();
        expect(screen.getByText(/React/i)).toBeInTheDocument();
    });
});

test('logout button clears localStorage and redirects', async () => {
    const mockNavigate = jest.fn();
    jest.mock('react-router-dom', () => ({
        ...jest.requireActual('react-router-dom'),
        useNavigate: () => mockNavigate
    }));

    await act(async () => {
        render(<ProfilePage />, { wrapper: MemoryRouter });
    });

    const logoutBtn = screen.getByRole('button', { name: /log out/i });
    fireEvent.click(logoutBtn);

    expect(localStorage.getItem('user')).toBeNull();
});