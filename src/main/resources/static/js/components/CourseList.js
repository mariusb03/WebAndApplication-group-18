// Dummy course data (replace or fetch dynamically if needed)
const courses = [
    { id: 'c1', title: 'Intro to JavaScript', progress: 'In Progress' },
    { id: 'c2', title: 'Advanced CSS', progress: 'Completed' },
    { id: 'c3', title: 'React Basics', progress: 'Not Started' }
];

// Render function
function renderCourseList(courseArray) {
    const container = document.getElementById('courseListContainer');
    container.innerHTML = ''; // Clear previous

    courseArray.forEach(course => {
        const card = document.createElement('div');
        card.className = 'course-card';

        const title = document.createElement('h3');
        title.textContent = course.title;

        const status = document.createElement('p');
        status.textContent = `Status: ${course.progress}`;

        card.appendChild(title);
        card.appendChild(status);
        container.appendChild(card);
    });
}

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    renderCourseList(courses);
});