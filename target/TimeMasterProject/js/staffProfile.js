// Function to open the edit form modal
function openEditForm() {
    document.getElementById("editModal").style.display = "block";
}

// Function to close the edit form modal
function closeEditForm() {
    document.getElementById("editModal").style.display = "none";
}

// Function to save profile changes
function saveProfile() {
    const updatedEmail = document.getElementById("editEmail").value;
    const updatedLocation = document.getElementById("editLocation").value;

    // Update the profile details in the main card
    document.getElementById("email").textContent = updatedEmail;
    document.getElementById("location").textContent = updatedLocation;

    // Close the modal
    closeEditForm();

    // Alert user of success
    alert("Profile updated successfully!");
}

// Function to open the change password modal
function openPasswordForm() {
    document.getElementById("passwordModal").style.display = "block";
}

// Function to close the change password modal
function closePasswordForm() {
    document.getElementById("passwordModal").style.display = "none";
}

// Function to change password
function changePassword() {
    const oldPassword = document.getElementById("oldPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    // Validate passwords
    if (newPassword !== confirmPassword) {
        alert("New passwords do not match. Please try again.");
        return;
    }

    if (oldPassword === "") {
        alert("Please enter your old password.");
        return;
    }

    // (Optional) Add backend integration here for validating and updating the password

    // Close the modal
    closePasswordForm();

    // Alert user of success
    alert("Password changed successfully!");
}

// Close the modal if the user clicks outside the modal content
	window.onclick = function(event) {
    const editModal = document.getElementById("editModal");
    const passwordModal = document.getElementById("passwordModal");
    if (event.target === editModal) {
        closeEditForm();
    }
    if (event.target === passwordModal) {
        closePasswordForm();
    }
};
