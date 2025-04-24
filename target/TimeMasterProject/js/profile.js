document.getElementById("userIcon").addEventListener("click", function() {
    const dropdownMenu = document.getElementById("dropdownMenu");
    // Toggle the visibility of the dropdown
    dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
});

// Close the dropdown if clicked anywhere outside of it
window.addEventListener("click", function(event) {
    if (!event.target.matches('.fa-user-o')) {
        const dropdownMenu = document.getElementById("dropdownMenu");
        if (dropdownMenu.style.display === "block") {
            dropdownMenu.style.display = "none";
        }
    }
});
