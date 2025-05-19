const courses = [
    {
        id: 'C001',
        title: 'AWS Fundamentals',
        image: '../../static/img/AWS.png',
        description: 'Learn core services and architecture of AWS.',
        price: '$99',
        difficulty: 'Beginner',
        topic: 'Cloud Computing',
        sessions: '4 weeks, self-paced',
    },
    {
        id: 'C002',
        title: 'Java Programming',
        image: '../../static/img/JavaLogo.png',
        description: 'Master Java programming from scratch.',
        price: '$89',
        difficulty: 'Intermediate',
        topic: 'Programming',
        sessions: '6 weeks, instructor-led',
    },
];

let selectedCourse = null;

function renderCourses(filter = '') {
    const container = document.getElementById('courseGrid');
    container.innerHTML = '';

    const filtered = courses.filter(course =>
        course.title.toLowerCase().includes(filter.toLowerCase())
    );

    if (filtered.length === 0) {
        container.innerHTML = '<p>No courses found.</p>';
        return;
    }

    filtered.forEach(course => {
        const card = document.createElement('div');
        card.className = 'course-card';
        card.onclick = () => openModal(course);

        card.innerHTML = `
      <img src="${course.image}" alt="${course.title}" />
      <h3 class="course-title">${course.title}</h3>
      <p class="course-description">${course.description}</p>
      <p><strong>Price:</strong> ${course.price}</p>
      <p><strong>Difficulty:</strong> ${course.difficulty}</p>
      <p><strong>Topic:</strong> ${course.topic}</p>
      <p><strong>Session:</strong> ${course.sessions}</p>
      <p><strong>ID:</strong> ${course.id}</p>
    `;

        container.appendChild(card);
    });
}

function openModal(course) {
    selectedCourse = course;
    document.getElementById('modalImage').src = course.image;
    document.getElementById('modalTitle').textContent = course.title;
    document.getElementById('modalDescription').textContent = course.description;
    document.getElementById('modalPrice').textContent = course.price;
    document.getElementById('modalDifficulty').textContent = course.difficulty;
    document.getElementById('modalTopic').textContent = course.topic;
    document.getElementById('modalSessions').textContent = course.sessions;
    document.getElementById('modalID').textContent = course.id;
    document.getElementById('modalOverlay').style.display = 'flex';
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

function addToCart() {
    if (selectedCourse) {
        console.log(`Added ${selectedCourse.title} to cart`);
        closeModal();
    }
}

// Initialize page
document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('courseSearch');
    renderCourses(searchInput.value);

    searchInput.addEventListener('input', () => {
        renderCourses(searchInput.value);
    });

    // Check for query string search
    const urlParams = new URLSearchParams(window.location.search);
    const searchParam = urlParams.get('search');
    if (searchParam) {
        searchInput.value = searchParam;
        renderCourses(searchParam);
    }
});