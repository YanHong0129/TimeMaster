document.addEventListener("DOMContentLoaded", () => {
  // Get references to the modal and buttons
  const modal = document.getElementById("add-course-modal");
  const openModalBtn = document.getElementById("addBtn");
  const closeModalBtn = document.getElementById("closeBtn");
  const closeEditModalBtn = document.getElementById("closeEditBtn");
  const editModal = document.getElementById("edit-course-modal");
  const openEditModalBtn = document.getElementById("EditBtn");

  // Function to show the modal
  openModalBtn.addEventListener("click", () => {
    modal.style.display = "flex"; // Set modal display to flex
  });
  
  // Open edit modal
  openEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "flex"; // Set edit modal display to flex
  });

  // Function to hide the modal
  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none"; // Hide the add-course modal
  });
  
  closeEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "none"; // Hide the edit-course modal
  });

  // Close modal when clicking outside of modal-content
  window.addEventListener("click", (event) => {
    // Close add-course modal if clicked outside of it
    if (event.target === modal) {
      modal.style.display = "none";
    }

    // Close edit-course modal if clicked outside of it
    if (event.target === editModal) {
      editModal.style.display = "none";
    }
  });
});
