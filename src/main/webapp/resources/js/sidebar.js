// Sidebar functionality

// Function to toggle sidebar
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const closeBtn = document.getElementById('closeBtn');

    if (sidebar.classList.contains('open')) {
        sidebar.classList.remove('open');
        closeBtn.style.display = 'none';
    } else {
        sidebar.classList.add('open');
        closeBtn.style.display = 'block';
    }
}

// Function to toggle shop submenu
function toggleShopMenu(event) {
    event.preventDefault();
    const shopMenu = event.currentTarget.parentElement;

    // Toggle the active class, which will show/hide the submenu via CSS
    shopMenu.classList.toggle('active');

    // Log for debugging
    console.log('Shop menu clicked, active:', shopMenu.classList.contains('active'));
}

// Initialize sidebar functionality when the document is ready
document.addEventListener('DOMContentLoaded', function() {
    // Add event listeners if needed
    console.log('Sidebar.js loaded');
});