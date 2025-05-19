const courseSuggestions = [
    'Web Development',
    'AI & Machine Learning',
    'Cloud & DevOps',
    'Digital Marketing',
    'Business Analytics',
    'Cybersecurity',
    'UI/UX Design',
    'Python Programming',
    'Data Science',
];

let isSearchFocused = false;

document.addEventListener('mousedown', (event) => {
    const popup = document.getElementById('searchPopup');
    if (popup && !popup.contains(event.target)) {
        hideSearchPopup();
    }
});

function toggleSearchPopup() {
    isSearchFocused = !isSearchFocused;
    document.getElementById('searchOverlay').style.display = isSearchFocused ? 'flex' : 'none';
    document.getElementById('searchInput').value = '';
    updateSuggestions('');
}

function hideSearchPopup() {
    isSearchFocused = false;
    document.getElementById('searchOverlay').style.display = 'none';
    document.getElementById('searchInput').value = '';
}

function handleSearchEnter(event) {
    if (event.key === 'Enter') {
        const value = event.target.value.trim();
        if (value) {
            window.location.href = `/courses?search=${encodeURIComponent(value)}`;
        }
    } else {
        updateSuggestions(event.target.value);
    }
}

function updateSuggestions(query) {
    const container = document.getElementById('suggestionsContainer');
    container.innerHTML = '';

    if (!query.trim()) {
        courseSuggestions.forEach((s) => container.appendChild(createSuggestionItem(s)));
        return;
    }

    const filtered = courseSuggestions.filter(c =>
        c.toLowerCase().includes(query.toLowerCase())
    );

    filtered.forEach((s) => container.appendChild(createSuggestionItem(s)));
}

function createSuggestionItem(text) {
    const li = document.createElement('li');
    li.textContent = text;
    li.style.padding = '0.5rem';
    li.style.borderRadius = '6px';
    li.style.cursor = 'pointer';

    li.onmouseenter = () => (li.style.backgroundColor = '#ffe600');
    li.onmouseleave = () => (li.style.backgroundColor = 'transparent');
    li.onclick = () => {
        window.location.href = `/courses?search=${encodeURIComponent(text)}`;
    };

    return li;
}

// Optional: mock cart quantity
const cartItems = JSON.parse(localStorage.getItem('cartItems') || '[]');
const cartBadge = document.getElementById('cartBadge');
if (cartItems.length > 0) {
    cartBadge.style.display = 'inline-block';
    cartBadge.textContent = cartItems.length;
}