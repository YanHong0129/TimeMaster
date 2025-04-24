document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("add-classroom-modal");
  const editModal = document.getElementById("edit-classroom-modal");
  const openModalBtn = document.getElementById("addClassroomBtn");
  const closeModalBtn = document.getElementById("closeModalBtn");
  const closeEditModalBtn = document.getElementById("closeEditModalBtn");

  // Open Add Classroom Modal
  openModalBtn.addEventListener("click", () => {
    modal.style.display = "flex";
  });

  // Close Add Classroom Modal
  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Close Edit Classroom Modal
  closeEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });

  // Close modals when clicking outside
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    } else if (event.target === editModal) {
      editModal.style.display = "none";
    }
  });

  // Event delegation for the #results table
  document.getElementById("results").addEventListener("click", (event) => {
    const target = event.target;

    // Check if the clicked element is an Edit button
    if (target.closest(".EditClassroomBtn")) {
      const button = target.closest(".EditClassroomBtn");

      // Populate the edit modal with button data
      document.getElementById("edit-classroom-id").value = button.dataset.classroomId;
      document.getElementById("edit-classroom-name").value = button.dataset.classroomName;
      document.getElementById("edit-abbreviation").value = button.dataset.abbreviation;
      document.getElementById("edit-block").value = button.dataset.block;
      document.getElementById("edit-floor").value = button.dataset.floor;
      document.getElementById("edit-capacity").value = button.dataset.capacity;
      document.getElementById("edit-faculty").value = button.dataset.faculty;
      document.getElementById("edit-type").value = button.dataset.type;

      editModal.style.display = "flex";
    }

    // Check if the clicked element is a Delete button
    if (target.closest(".DeleteClassroomBtn")) {
      const button = target.closest(".DeleteClassroomBtn");
      const classroomId = button.dataset.classroomId;

      if (confirm(`Are you sure you want to delete classroom ID ${classroomId}?`)) {
        fetch(`/TimeMasterProject/classroom/delete?id=${encodeURIComponent(classroomId)}`, {
          method: "DELETE",
        })
          .then((response) => {
            if (response.ok) {
              alert(`Classroom ID ${classroomId} deleted successfully!`);
              button.closest("tr").remove(); // Remove the row from the table
            } else {
              alert("Failed to delete classroom. Please try again.");
            }
          })
          .catch((error) => {
            console.error("Error deleting classroom:", error);
            alert("An error occurred. Please try again.");
          });
      }
    }
  });

  // Handle edit form submission
  const editClassroomForm = document.getElementById("editClassroomForm");
  editClassroomForm.addEventListener("submit", (event) => {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(editClassroomForm);
    const params = new URLSearchParams(formData).toString();

    fetch("/TimeMasterProject/classroom/edit", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: params,
    })
      .then((response) => {
        if (response.ok) {
          alert("Classroom updated successfully!"); // Success alert
          location.reload(); // Reload the page to reflect changes
        } else {
          alert("Failed to update classroom. Please try again."); // Failure alert
        }
      })
      .catch((error) => {
        console.error("Error updating classroom:", error);
        alert("An error occurred while updating the classroom."); // Error alert
      });
  });

  // Dynamic search functionality
  document.getElementById("searchKeyword").addEventListener("input", function () {
    const keyword = this.value;

    fetch(`/TimeMasterProject/classroom/search?keyword=${encodeURIComponent(keyword)}`)
      .then((response) => response.json())
      .then((data) => {
        const tbody = document.querySelector("#results table tbody");
        tbody.innerHTML = ""; // Clear existing rows

        if (data.length === 0) {
          tbody.innerHTML = '<tr><td colspan="10" style="text-align: center;">No classrooms found.</td></tr>';
        } else {
          data.forEach((classroom, index) => {
            tbody.innerHTML += `
              <tr>
                <td>${index + 1}</td>
                <td>${classroom.classroomID}</td>
                <td>${classroom.classroomName}</td>
                <td>${classroom.abbreviation}</td>
                <td>${classroom.block}</td>
                <td>${classroom.floor}</td>
                <td>${classroom.capacity}</td>
                <td>${classroom.faculty}</td>
                <td>${classroom.type}</td>
                <td>
                  <div class="actionIcons">
                    <a class="EditClassroomBtn" 
                       data-classroom-id="${classroom.classroomID}" 
                       data-classroom-name="${classroom.classroomName}" 
                       data-abbreviation="${classroom.abbreviation}" 
                       data-block="${classroom.block}" 
                       data-floor="${classroom.floor}" 
                       data-capacity="${classroom.capacity}" 
                       data-faculty="${classroom.faculty}" 
                       data-type="${classroom.type}">
                      <i class="fa fa-pencil"></i>
                    </a>
                    <a class="DeleteClassroomBtn" 
                       data-classroom-id="${classroom.classroomID}">
                      <i class="fa fa-trash"></i>
                    </a>
                  </div>
                </td>
              </tr>
            `;
          });
        }
      })
      .catch((error) => console.error("Error fetching search results:", error));
  });
});


