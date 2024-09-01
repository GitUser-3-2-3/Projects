// summary.js
document.addEventListener("DOMContentLoaded", function () {
// Add a fade-in effect to the ingredient badges
    const badges = document.querySelectorAll('.ingredient-badge');
    badges.forEach((badge, index) => {
        badge.style.animationDelay = `${index * 0.2}s`;  // Adjust delay for each badge
        badge.classList.add('fade-in');  // Assuming a 'fade-in' animation class in your CSS
    });
});

