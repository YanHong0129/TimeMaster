document.addEventListener("DOMContentLoaded", () => {
    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach((button) => {
        button.addEventListener("click", (event) => {
            const notificationItem = event.target.closest(".notification-item");
            
            // animation remove
            notificationItem.style.transition = "opacity 0.3s ease, transform 0.3s ease";
            notificationItem.style.opacity = "0";
            notificationItem.style.transform = "translateX(-100%)";

            // Remove notification after animation ends
            setTimeout(() => {
                notificationItem.remove();
            }, 300);
        });
    });
});


console.log("test");