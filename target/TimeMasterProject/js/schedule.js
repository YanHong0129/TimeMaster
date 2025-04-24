// Open Modal
const openModalBtns = document.querySelectorAll('.open-modal-btn');
const modal = document.getElementById('modal');
const closeModalBtn = document.querySelector('.close-btn');

// Add event listener to each edit button
openModalBtns.forEach((btn) => {
    btn.addEventListener('click', (event) => {
        const row = event.target.closest('tr'); // Get the row of the clicked button
        const cells = row.querySelectorAll('td'); // Get all cells in the row
        
        // Retrieve Classroom ID and Name (e.g., N28-105-01 and Bilik Kuliah 1)
        const classroomID = cells[1].textContent.trim(); // Classroom ID (column 2)
        const classroomName = cells[2].textContent.trim(); // Classroom Name (column 3)
        
        // Construct the classroom information for modal header
        const classroom = `${classroomID} (${classroomName})`;
        
        // Update modal header with the format "N28-105-01 (Bilik Kuliah 1)"
        document.querySelector('.modal-content h3').innerText = classroom;

        // Populate form fields with values from the row
        document.getElementById('course').value = cells[3].textContent.trim() || '';
        document.getElementById('program').value = cells[5].textContent.trim() || '';
        document.getElementById('section').value = cells[6].textContent.trim() || '';
        document.getElementById('lecturer').value = cells[8].textContent.trim() || '';

        // Show modal
        modal.style.display = 'flex';
    });
});

// Close Modal
closeModalBtn.addEventListener('click', () => {
    modal.style.display = 'none';
    clearModal();
});

window.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
        clearModal();
    }
});

// Clear modal fields when closed
function clearModal() {
    document.querySelector('.modal-content h3').textContent = ''; // Clear header
    document.getElementById('course').value = ''; // Clear course field
    document.getElementById('program').value = ''; // Clear program field
    document.getElementById('section').value = ''; // Clear section field
    document.getElementById('lecturer').value = ''; // Clear lecturer field
}

/*
function loadSections() {
    const courseCode = document.getElementById('course').value;
	const timeSlotID = document.getElementById('timeSlotID').value;
    if (!courseCode) return;

    // Fetch available sections and lecturers for the selected course
    fetch(`/TimeMasterProject/faculty/timetable/getSectionsAndLecturers?courseCode=${encodeURIComponent(courseCode)}&timeSlotID=${encodeURIComponent(timeSlotID)}`)
        .then(response => response.json())
        .then(data => {
            // Update the sections dropdown
            const sectionDropdown = document.getElementById('section');
            sectionDropdown.innerHTML = '<option value="" selected disabled>Select Section</option>';
            data.sections.forEach(section => {
                const option = document.createElement('option');
                option.value = section.sectionID;
                option.textContent = section.sectionName;
                sectionDropdown.appendChild(option);
            });

            // Update the lecturers dropdown
            const lecturerDropdown = document.getElementById('lecturer');
            lecturerDropdown.innerHTML = '<option value="" selected disabled>Select Lecturer</option>';
            data.lecturers.forEach(lecturer => {
                const option = document.createElement('option');
                option.value = lecturer.lecturerID;
                option.textContent = lecturer.lecturerName;
                lecturerDropdown.appendChild(option);
            });
        })
        .catch(error => console.error('Error loading sections and lecturers:', error));
}*/

$(document).ready(function() {

	// Handle form submission with AJAX
    $('.schedule-form').on('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Serialize the form data
        var formData = $(this).serialize();

        // Send the data via AJAX
        $.ajax({
            url: 'schedule/update', // The endpoint that handles the schedule update
            method: 'POST',
            data: formData,
            success: function(response) {
                // Handle success response
                if (response.success) {
                    alert('Schedule updated successfully!');
                    // Optionally, redirect or update UI
                    // window.location.reload(); // Uncomment to reload the page after success
                } else {
                    alert('Error updating schedule.');
                }
            },
            error: function(xhr, status, error) {
                // Handle error response
                console.error('Error:', error);
                alert('An error occurred. Please try again.');
            }
        });
    });
});

function loadSections() {
    const courseCode = document.getElementById('course').value;
    const timeSlotID = document.getElementById('timeSlotID').value;

    if (!courseCode) return;

    // Fetch sections and lecturers dynamically
    fetch(`/TimeMasterProject/faculty/timetable/getSectionsAndLecturers?courseCode=${encodeURIComponent(courseCode)}&timeSlotID=${encodeURIComponent(timeSlotID)}`)
        .then(response => response.json())
        .then(data => {
            console.log('API Response:', data); 
            console.log($('#lecturer'));  // Check if the DOM element is correct

            // Populate sections dropdown
            const sectionDropdown = $('#section');
            sectionDropdown.empty().append('<option value="" selected disabled>Select Section</option>');
            
            if (data.sections.length > 0) {
                data.sections.forEach(section => {
                    sectionDropdown.append(new Option(section.sectionName, section.sectionID));
                });
            } else {
                sectionDropdown.append('<option value="" disabled>No sections available</option>');
            }

            // Populate lecturers dropdown
            const lecturerDropdown = $('#lecturer');
            lecturerDropdown.empty().append('<option value="" selected disabled>Select Lecturer</option>');

            if (data.lecturers.length > 0) {
                data.lecturers.forEach(lecturer => {
                    lecturerDropdown.append(new Option(lecturer.name, lecturer.lecturerID));
                });
            } else {
                lecturerDropdown.append('<option value="" disabled>No lecturers available</option>');
            }

            // Initialize Select2 on section and lecturer dropdowns
            sectionDropdown.select2({
                placeholder: 'Select a Section',
                allowClear: true
            });

            lecturerDropdown.select2({
                placeholder: 'Select a Lecturer',
                allowClear: true
            });

            // Trigger Select2 refresh after populating the dropdowns
            sectionDropdown.trigger('change');
            lecturerDropdown.trigger('change');
        })
        .catch(error => {
            console.error('Error loading sections and lecturers:', error);
            // Optional: Add error handling UI or feedback here
        });
}

// Call select2 on the course dropdown if it's dynamic (populated with Java)
$(document).ready(function() {
    $('.select-course').select2({
        placeholder: 'Select a Course',
        allowClear: true
    });
});


