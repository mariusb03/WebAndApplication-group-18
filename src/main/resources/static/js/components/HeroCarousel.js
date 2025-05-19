document.addEventListener('DOMContentLoaded', function () {
    const slides = [
        {
            image: '../../static/img/personTakingCourse.jpg',
            headline: 'Master Data Analytics with Databricks',
            subtext: 'Learn from the best in the field — practical, project-based, and powerful.',
        },
        {
            image: 'https://miro.medium.com/v2/resize:fit:1358/1*cG6U1qstYDijh9bPL42e-Q.jpeg',
            headline: 'AI & Machine Learning with Python',
            subtext: 'Beginner-friendly, real-world projects, and job-ready skills.',
        },
        {
            image: 'https://digitaltransformation.org.au/sites/default/files/2022-01/azure.png',
            headline: 'Azure Certified in Weeks',
            subtext: 'Cloud skills are in demand. Start your journey with Learniverse Connect.',
        }
    ];

    const carouselRoot = document.getElementById('carousel-root');

    const sliderContainer = document.createElement('div');
    sliderContainer.classList.add('hero-carousel');

    const slideWrapper = document.createElement('div');
    slideWrapper.classList.add('carousel-slider');

    slides.forEach(slide => {
        const slideDiv = document.createElement('div');
        slideDiv.innerHTML = `
      <div class="carousel-slide" style="background-image: url('${slide.image}')">
        <div class="carousel-content">
          <h1>${slide.headline}</h1>
          <p>${slide.subtext}</p>
        </div>
      </div>
    `;
        slideWrapper.appendChild(slideDiv);
    });

    sliderContainer.appendChild(slideWrapper);
    carouselRoot.appendChild(sliderContainer);

    // Initialize Slick
    $('.carousel-slider').slick({
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 5000,
        pauseOnHover: true,
        arrows: true,
        swipe: true,
    });
});