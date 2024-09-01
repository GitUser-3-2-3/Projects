// summary.js
document.addEventListener("DOMContentLoaded", function () {
    // Apply staggered animations to ingredient badges
    const badges = document.querySelectorAll('.ingredient-badge');
    badges.forEach((badge, index) => {
        badge.style.animationDelay = `${index * 0.2}s`;
    });
});
