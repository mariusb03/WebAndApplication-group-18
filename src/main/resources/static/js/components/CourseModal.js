// Dummy example data (replace with dynamic or fetched data)
const exampleCourse = {
    id: 'C001',
    title: 'AWS Fundamentals',
    image: '../../static/img/aws.png',
    description: 'Learn core services and architecture of AWS.',
    price: '$99',
    difficulty: 'Beginner',
    topic: 'Cloud Computing',
    sessions: '4 weeks, self-paced'
};

// Open modal and populate content
function showModal(course) {
    document.getElementById('modalImage').src = course.image;
    document.getElementById('modalTitle').textContent = course.title;
    document.getElementById('modalDescription').textContent = course.description || 'No description provided yet.';
    document.getElementById('modalPrice').textContent = course.price;
    document.getElementById('modalDifficulty').textContent = course.difficulty;
    document.getElementById('modalTopic').textContent = course.topic;
    document.getElementById('modalSession').textContent = course.sessions;
    document.getElementById('modalID').textContent = course.id;

    document.getElementById('modalOverlay').style.display = 'flex';
}

// Close modal
function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

// Simulate add to cart
function addToCart() {
    console.log(`Added course: ${document.getElementById('modalTitle').textContent} to cart`);
    closeModal();
}

// Optional: Show modal on load for testing
document.addEventListener('DOMContentLoaded', () => {
    showModal(exampleCourse);
});