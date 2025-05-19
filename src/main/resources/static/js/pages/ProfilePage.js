document.addEventListener('DOMContentLoaded', () => {
    const courses = [
        { id: 1, title: 'AI & Machine Learning', progress: '45%' },
        { id: 2, title: 'Cloud Fundamentals', progress: 'Completed' },
    ];

    const courseListContainer = document.getElementById('courseList');

    courses.forEach(course => {
        const card = document.createElement('div');
        card.className = 'course-card';
        card.innerHTML = `
      <h3>${course.title}</h3>
      <p>Status: ${course.progress}</p>
    `;
        courseListContainer.appendChild(card);
    });
});