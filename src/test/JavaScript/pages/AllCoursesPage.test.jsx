import React from 'react';
import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import AllCoursesPage from 'Components/AllCoursesPage/AllCoursesPage';
import * as test from "node:test";

const mockCourses = [
    {
        courseId: '1',
        title: 'React Basics',
        difficulty: 'Beginner',
        category: 'Frontend',
        session: 'Spring',
        hidden: false
    },
    {
        courseId: '2',
        title: 'Advanced Node.js',
        difficulty: 'Advanced',
        category: 'Backend',
        session: 'Fall',
        hidden: true
    }
];

beforeEach(() => {
    global.fetch = jest.fn()
        .mockImplementation((url) => {
            if (url.includes('getAll')) {
                return Promise.resolve({
                    json: () => Promise.resolve(mockCourses)
                });
            }

            if (url.includes('getPrice')) {
                return Promise.resolve({
                    json: () => Promise.resolve([{ price: 100 }, { price: 200 }])
                });
            }

            if (url.includes('favourites')) {
                return Promise.resolve({
                    json: () => Promise.resolve([{ courseId: '1' }])
                });
            }

            return Promise.resolve({ ok: true });
        });

    localStorage.setItem('user', JSON.stringify({ userId: '123', role: 'user' }));
});

afterEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
});

test('renders AllCoursesPage and displays courses', async () => {
    render(
        <Router>
            <AllCoursesPage />
        </Router>
    );

    expect(screen.getByPlaceholderText(/search for a course/i)).toBeInTheDocument();

    await waitFor(() => {
        expect(screen.getByText('React Basics')).toBeInTheDocument();
    });
});

test('filters courses based on search query', async () => {
    render(
        <Router>
            <AllCoursesPage />
        </Router>
    );

    await waitFor(() => screen.getByText('React Basics'));

    fireEvent.change(screen.getByPlaceholderText(/search for a course/i), {
        target: { value: 'Node' }
    });

    expect(screen.queryByText('React Basics')).not.toBeInTheDocument();
    expect(screen.getByText('Advanced Node.js')).toBeInTheDocument();
});

test('shows favourite star for logged in user', async () => {
    render(
        <Router>
            <AllCoursesPage />
        </Router>
    );

    await waitFor(() => {
        expect(screen.getAllByText('★')[0]).toBeInTheDocument();
    });
});

test('admin sees hidden course and toggle visibility button', async () => {
    localStorage.setItem('user', JSON.stringify({ userId: '123', role: 'admin' }));

    render(
        <Router>
            <AllCoursesPage />
        </Router>
    );

    await waitFor(() => {
        expect(screen.getByText('Advanced Node.js')).toBeInTheDocument();
        expect(screen.getByText('Hide')).toBeInTheDocument(); // or 'Unhide'
        expect(screen.getByText(/ID:/)).toBeInTheDocument();
    });
});

test('opens modal when a course card is clicked', async () => {
    render(
        <Router>
            <AllCoursesPage />
        </Router>
    );

    await waitFor(() => {
        fireEvent.click(screen.getByText('React Basics'));
    });

    expect(screen.getByRole('dialog')).toBeInTheDocument(); // if CourseModal has role="dialog"
});