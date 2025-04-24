document.addEventListener("DOMContentLoaded", () => {
  // Get references to the modal and buttons
  const modal = document.getElementById("add-course-modal");
  const openModalBtn = document.getElementById("addCourseBtn");
  const closeModalBtn = document.getElementById("closeModalBtn");
  const closeEditModalBtn = document.getElementById("closeEditModalBtn");
  const editModal = document.getElementById("edit-course-modal");
  const openEditModalBtn = document.getElementById("EditCourseBtn");

  // Function to show the modal
  openModalBtn.addEventListener("click", () => {
    modal.style.display = "flex"; // Set modal display to flex
  });
  //Open edit model
  openEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "flex"; // Set edit modal display to flex
  });

  // Function to hide the modal
  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none"; // Hide the modal
  });
  
  closeEditModalBtn.addEventListener("click", () => {
  	editModal.style.display = "none";
  });

  // Close modal when clicking outside of modal-content
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });
  
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
    	editModal.style.display = "none";
    }
  });
});