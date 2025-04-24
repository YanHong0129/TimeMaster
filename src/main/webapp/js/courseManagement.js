document.addEventListener("DOMContentLoaded", () => {
  // Get references to the modals and buttons
  const modal = document.getElementById("add-course-modal");
  const editModal = document.getElementById("edit-course-modal");
  const openModalBtn = document.getElementById("addCourseBtn");
  const closeModalBtn = document.getElementById("closeModalBtn");
  const closeEditModalBtn = document.getElementById("closeEditModalBtn");
  const searchInput = document.querySelector(".search-bar");
  const resultsTable = document.querySelector("tbody");

  // Function to show the "Add Course" modal
  openModalBtn.addEventListener("click", () => {
    modal.style.display = "flex";
  });

  // Function to hide the "Add Course" modal
  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Function to hide the "Edit Course" modal
  closeEditModalBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });

  // Event delegation for dynamically created "Edit" buttons
  document.body.addEventListener("click", (event) => {
    if (event.target.closest(".EditCourseBtn")) {
      const button = event.target.closest(".EditCourseBtn");

      // Populate the edit modal with data (example: courseCode, courseName)
      const courseCode = button.dataset.courseCode;
      const courseName = button.dataset.courseName;
	  const courseCredit = button.dataset.courseCredit;
	  const year = button.dataset.year;

      document.getElementById("edit-course-code").value = courseCode || "";
      document.getElementById("edit-course-name").value = courseName || "";
	  document.getElementById("edit-course-credit").value = courseCredit || "";
	  document.getElementById("edit-year").value = year || "";

      // Show the edit modal
      editModal.style.display = "flex";
    }
  });

  // Event delegation for dynamically created "Delete" buttons
  document.body.addEventListener("click", (event) => {
    if (event.target.closest(".DeleteCourseBtn")) {
      const button = event.target.closest(".DeleteCourseBtn");
      const courseCode = button.dataset.courseCode;

      if (confirm(`Are you sure you want to delete the course with code ${courseCode}?`)) {
        fetch(`/TimeMasterProject/course/delete`, {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: `courseCode=${encodeURIComponent(courseCode)}`,
        })
          .then((response) => {
            if (response.ok) {
              alert("Course deleted successfully.");
              location.reload(); // Refresh the page to reflect changes
            } else {
              alert("Failed to delete course. Please try again.");
            }
          })
          .catch((error) => {
            console.error("Error deleting course:", error);
            alert("An error occurred while deleting the course.");
          });
      }
    }
  });

  // Close modals when clicking outside modal-content
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
    if (event.target === editModal) {
      editModal.style.display = "none";
    }
  });

  // Search functionality
  searchInput.addEventListener("input", () => {
    const keyword = searchInput.value.trim();

    fetch(`/TimeMasterProject/course/search?keyword=${encodeURIComponent(keyword)}`)
      .then((response) => response.json())
      .then((data) => {
        resultsTable.innerHTML = ""; // Clear existing rows

        if (data.length === 0) {
          resultsTable.innerHTML = '<tr><td colspan="7" style="text-align: center;">No courses found.</td></tr>';
        } else {
          data.forEach((course, index) => {
            resultsTable.innerHTML += `
              <tr>
                <td>${index + 1}</td>
                <td>${course.courseCode}</td>
                <td>${course.courseName}</td>
                <td>${course.courseCredit}</td>
                <td>${course.year}</td>
                <td>${course.sectionCount}</td>
                <td>
                  <div class="actionIcons">
                    <a href="/TimeMasterProject/course/sections?courseCode=${course.courseCode}" 
                       class="ViewSectionsBtn" 
                       data-course-code="${course.courseCode}">
                      <i class="fa fa-eye"></i>
                    </a>
                    <a class="EditCourseBtn" 
                       data-course-code="${course.courseCode}" 
                       data-course-name="${course.courseName}"
					   data-course-credit="${course.courseCredit}"
					   data-course-year="${course.year}"
					   data-course-name="${course.sectionCount}">
                      <i class="fa fa-pencil"></i>
                    </a>
                    <a class="DeleteCourseBtn" 
                       data-course-code="${course.courseCode}">
                      <i class="fa fa-trash"></i>
                    </a>
                  </div>
                </td>
              </tr>
            `;
          });
        }
      })
      .catch((error) => {
        console.error("Error fetching search results:", error);
      });
  });
});


