document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;

        // Simulate login validation (for static prototype)
        if (email === '' || password === '') {
            alert('Please enter both email and password.');
            return;
        }

        alert(`Logged in as ${email}`);
        // Optionally redirect
        window.location.href = '/profile.html';
    });
});