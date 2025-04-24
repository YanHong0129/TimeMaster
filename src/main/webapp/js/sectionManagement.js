document.addEventListener("DOMContentLoaded", () => {
  const addModal = document.getElementById("add-section-modal");
  const editModal = document.getElementById("edit-section-modal");
  const openAddModalBtn = document.getElementById("addSectionBtn");
  const closeAddModalBtn = document.getElementById("closeAddModalBtn");
  const closeEditModalBtn = document.getElementById("closeEditModalBtn");

  // Open Add Section Modal
  openAddModalBtn.addEventListener("click", () => {
    addModal.style.display = "flex";
  });

  // Close Add Section Modal
  closeAddModalBtn.addEventListener("click", () => {
    addModal.style.display = "none";
  });

  // Open Edit Section Modal
  document.querySelectorAll(".editSectionBtn").forEach((button) => {
    button.addEventListener("click", () => {
      document.getElementById("edit-section-id").value = button.dataset.sectionId;
      document.getElementById("edit-section-name").value = button.dataset.sectionName;
      document.getElementById("edit-program").value = button.dataset.program;
      document.getElementById("edit-capacity").value = button.dataset.capacity;

      editModal.style.display = "flex";
    });
  });

  // Close Edit Section Modal
  closeEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });

  // Submit Add Section Form
  document.getElementById("addSectionForm").addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    fetch("/TimeMasterProject/course/section/add", {
      method: "POST",
      body: new URLSearchParams(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.success) {
          alert("Section added successfully!");
          location.reload(); // Reload the page to reflect changes
        } else {
          alert(data.message || "Failed to add section.");
        }
      })
      .catch((error) => {
        console.error("Error adding section:", error);
      });
  });

  // Submit Edit Section Form
  document.getElementById("editSectionForm").addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    fetch("/TimeMasterProject/course/section/edit", {
      method: "POST",
      body: new URLSearchParams(formData),
    })
      .then((response) => response.text())
      .then((message) => {
        alert(message);
        location.reload(); // Reload the page to reflect changes
      })
      .catch((error) => {
        console.error("Error updating section:", error);
      });
  });

  // Attach Delete Section Functionality
  document.querySelectorAll(".deleteSectionBtn").forEach((button) => {
    button.addEventListener("click", () => {
      const sectionID = button.dataset.sectionId;

      if (confirm("Are you sure you want to delete this section?")) {
        fetch("/TimeMasterProject/course/section/delete", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: `sectionID=${encodeURIComponent(sectionID)}`,
        })
          .then((response) => response.text())
          .then((message) => {
            alert(message);
            location.reload(); // Reload the page to reflect changes
          })
          .catch((error) => {
            console.error("Error deleting section:", error);
          });
      }
    });
  });
});
document.getElementById("addSectionForm").addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    fetch("/course/section/add", {
        method: "POST",
        body: new URLSearchParams(formData),
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
    })
    .then((response) => response.json())
    .then((data) => {
        if (data.success) {
            alert(data.message);
            location.reload();
        } else {
            alert(data.message);
        }
    })
    .catch((error) => {
        console.error("Error adding section:", error);
        alert("An error occurred while adding the section.");
    });
});
