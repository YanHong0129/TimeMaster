document.addEventListener("DOMContentLoaded", () => {
    const deleteButtons = document.querySelectorAll(".delete-btn");
    const readButtons = document.querySelectorAll(".read-btn");
    const timestamps = document.querySelectorAll(".timestamp");

    // Format timestamp
    timestamps.forEach((timestamp) => {
        const date = new Date(timestamp.textContent);
        const formattedDate = formatTimestamp(date);
        timestamp.textContent = formattedDate;
    });

    deleteButtons.forEach((button) => {
        button.addEventListener("click", (event) => {
            const notificationItem = event.target.closest(".notification-item");

            // Animation for removal
            notificationItem.style.transition = "opacity 0.3s ease, transform 0.3s ease";
            notificationItem.style.opacity = "0";
            notificationItem.style.transform = "translateX(-100%)";

            // Remove notification after animation ends
            setTimeout(() => {
                notificationItem.remove();
            }, 300);
        });
    });

    // Format timestamp function to 'yyyy || mm-dd time'
    function formatTimestamp(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year} || ${month}-${day} ${hours}:${minutes}`;
    }

    // Ensure the "Mark as Read" button appears on hover like the "Delete" button
    const notificationItems = document.querySelectorAll(".notification-item");

    notificationItems.forEach((notificationItem) => {
        notificationItem.addEventListener("mouseenter", () => {
            const deleteButton = notificationItem.querySelector(".delete-btn");
            const readButton = notificationItem.querySelector(".read-btn");
            deleteButton.style.display = 'block';  // Show delete button
            readButton.style.display = 'block';   // Show mark as read button
        });

        notificationItem.addEventListener("mouseleave", () => {
            const deleteButton = notificationItem.querySelector(".delete-btn");
            const readButton = notificationItem.querySelector(".read-btn");
            deleteButton.style.display = 'none';  // Hide delete button
            readButton.style.display = 'none';   // Hide mark as read button
        });
    });
});
