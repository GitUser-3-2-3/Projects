document.addEventListener('DOMContentLoaded', () => {
    const ingredientCheckboxes = document.querySelectorAll('.ingredient input[type="checkbox"]');

    ingredientCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => {
            if (checkbox.checked) {
                checkbox.parentElement.classList.add('highlighted');
            } else {
                checkbox.parentElement.classList.remove('highlighted');
            }
        });
    });
});
