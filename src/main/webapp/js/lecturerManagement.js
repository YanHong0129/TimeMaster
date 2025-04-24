document.addEventListener("DOMContentLoaded", () => {
  // References to modals and buttons
  const addModal = document.getElementById("add-lecturer-modal");
  const editModal = document.getElementById("edit-lecturer-modal");
  const openAddModalBtn = document.getElementById("addLecturerBtn");
  const closeAddModalBtn = document.getElementById("closeAddModalBtn");
  const closeEditModalBtn = document.getElementById("closeEditModalBtn");

  // Open Add Lecturer Modal
  openAddModalBtn.addEventListener("click", () => {
    addModal.style.display = "flex";
  });

  // Close Add Lecturer Modal
  closeAddModalBtn.addEventListener("click", () => {
    addModal.style.display = "none";
  });

  // Close Edit Lecturer Modal
  closeEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });

  // Close modals when clicking outside modal content
  window.addEventListener("click", (event) => {
    if (event.target === addModal) {
      addModal.style.display = "none";
    } else if (event.target === editModal) {
      editModal.style.display = "none";
    }
  });

  // Attach event listeners to dynamically created Edit buttons
  function attachEditButtonListeners() {
    document.querySelectorAll(".EditLecturerBtn").forEach((button) => {
      button.addEventListener("click", () => {
        // Populate the edit modal with lecturer data
        document.getElementById("edit-lecturer-id").value = button.dataset.lecturerId;
        document.getElementById("edit-lecturer-name").value = button.dataset.name;
        document.getElementById("edit-email").value = button.dataset.email;
        document.getElementById("edit-phone").value = button.dataset.phone;
        document.getElementById("edit-office").value = button.dataset.office;
        document.getElementById("edit-faculty").value = button.dataset.faculty;

        // Show the edit modal
        editModal.style.display = "flex";
      });
    });
  }

  // Attach event listeners to dynamically created Delete buttons
  function attachDeleteButtonListeners() {
    document.querySelectorAll(".DeleteLecturerBtn").forEach((button) => {
      button.addEventListener("click", () => {
        const lecturerID = button.dataset.lecturerId;

        if (confirm("Are you sure you want to delete this lecturer?")) {
          fetch(`/TimeMasterProject/lecturer/delete`, {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
            body: `lecturerID=${encodeURIComponent(lecturerID)}`,
          })
            .then((response) => {
              if (response.ok) {
                alert("Lecturer deleted successfully!");
                location.reload(); // Refresh the page
              } else {
                alert("Failed to delete lecturer. Please try again.");
              }
            })
            .catch((error) => {
              console.error("Error deleting lecturer:", error);
              alert("An error occurred while deleting the lecturer.");
            });
        }
      });
    });
  }

  // Dynamic search functionality
  document.getElementById("searchKeyword").addEventListener("input", function () {
    const keyword = this.value;

    fetch(`/TimeMasterProject/lecturer/search?keyword=${encodeURIComponent(keyword)}`)
      .then((response) => response.json())
      .then((data) => {
        const tbody = document.querySelector("#results table tbody");
        tbody.innerHTML = ""; // Clear existing rows

        if (data.length === 0) {
          tbody.innerHTML = '<tr><td colspan="8" style="text-align: center;">No lecturers found.</td></tr>';
        } else {
          data.forEach((lecturer, index) => {
            tbody.innerHTML += `
              <tr>
                <td>${index + 1}</td>
                <td>${lecturer.lecturerID}</td>
                <td>${lecturer.name}</td>
                <td>${lecturer.email}</td>
                <td>${lecturer.phoneNum}</td>
                <td>${lecturer.office}</td>
                <td>${lecturer.faculty}</td>
                <td>
                  <div class="actionIcons">
                    <a id="EditLecturerBtn-${lecturer.lecturerID}" 
                       class="EditLecturerBtn" 
                       data-lecturer-id="${lecturer.lecturerID}" 
                       data-name="${lecturer.name}" 
                       data-email="${lecturer.email}" 
                       data-phone="${lecturer.phoneNum}" 
                       data-office="${lecturer.office}" 
                       data-faculty="${lecturer.faculty}">
                      <i class="fa fa-pencil"></i>
                    </a>
                    <a id="DeleteLecturerBtn-${lecturer.lecturerID}" 
                       class="DeleteLecturerBtn" 
                       data-lecturer-id="${lecturer.lecturerID}">
                      <i class="fa fa-trash"></i>
                    </a>
                  </div>
                </td>
              </tr>
            `;
          });

          // Reattach event listeners to the new buttons
          attachEditButtonListeners();
          attachDeleteButtonListeners();
        }
      })
      .catch((error) => {
        console.error("Error fetching search results:", error);
      });
  });

  // Initial attachment of event listeners
  attachEditButtonListeners();
  attachDeleteButtonListeners();
});

document.addEventListener("DOMContentLoaded", () => {
  const addLecturerForm = document.getElementById("addLecturerForm");

  // Handle form submission for adding a lecturer
  addLecturerForm.addEventListener("submit", (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    const formData = new FormData(addLecturerForm); // Collect form data
    const params = new URLSearchParams(formData).toString(); // Convert to URL-encoded string

    // Send the request to add the lecturer
    fetch("/TimeMasterProject/lecturer/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: params,
    })
      .then((response) => {
        if (response.ok) {
          alert("Lecturer added successfully!"); // Show success alert
          location.reload(); // Reload the page to show the updated list
        } else {
          alert("Failed to add lecturer. Please try again."); // Show failure alert
        }
      })
      .catch((error) => {
        console.error("Error adding lecturer:", error);
        alert("An error occurred while adding the lecturer."); // Show error alert
      });
  });
});
document.addEventListener("DOMContentLoaded", () => {
  const editLecturerForm = document.getElementById("editLecturerForm");

  // Handle form submission for editing a lecturer
  editLecturerForm.addEventListener("submit", (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    const formData = new FormData(editLecturerForm); // Collect form data
    const params = new URLSearchParams(formData).toString(); // Convert to URL-encoded string

    // Send the request to edit the lecturer
    fetch("/TimeMasterProject/lecturer/edit", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: params,
    })
      .then((response) => {
        if (response.ok) {
          alert("Lecturer updated successfully!"); // Show success alert
          location.reload(); // Reload the page to reflect changes
        } else {
          alert("Failed to update lecturer. Please try again."); // Show failure alert
        }
      })
      .catch((error) => {
        console.error("Error updating lecturer:", error);
        alert("An error occurred while updating the lecturer."); // Show error alert
      });
  });
});

