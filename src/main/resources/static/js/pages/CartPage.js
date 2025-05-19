// Simulate cart data (replace with localStorage or dynamic data later)
const cartItems = JSON.parse(localStorage.getItem('cart')) || [];

const renderCart = () => {
    const container = document.getElementById('cartContent');

    if (cartItems.length === 0) {
        container.innerHTML = `
      <div class="empty-cart-message">
        <p>Your cart is currently empty</p>
      </div>
    `;
        return;
    }

    const topBox = document.createElement('div');
    topBox.className = 'cart-top-box';

    cartItems.forEach(course => {
        const card = document.createElement('div');
        card.className = 'course-card';
        card.innerHTML = `
      <img src="${course.image}" alt="${course.title}" />
      <div class="course-info">
        <h3>${course.title}</h3>
        <div class="tag">Topics: ${course.topic}</div>
        <div class="tag">Price: ${course.price}</div>
        <div class="tag">Schedule: ${course.sessions}</div>
        <div class="tag">Description: ${course.description}</div>
      </div>
    `;
        topBox.appendChild(card);
    });

    const summaryBox = document.createElement('div');
    summaryBox.className = 'price-summary-box';

    let total = 0;

    cartItems.forEach(course => {
        const price = parseInt(course.price.replace(/\D/g, '')) || 0;
        total += price;

        const p = document.createElement('p');
        p.textContent = `${course.title} : ${course.price}`;
        summaryBox.appendChild(p);
    });

    const totalBox = document.createElement('div');
    totalBox.className = 'total-box';
    totalBox.innerHTML = `
    <span class="label">Total price:</span>
    <span class="price">${total.toLocaleString()} NOK</span>
  `;

    summaryBox.appendChild(totalBox);

    container.innerHTML = '';
    container.appendChild(topBox);
    container.appendChild(summaryBox);
};

document.addEventListener('DOMContentLoaded', renderCart);