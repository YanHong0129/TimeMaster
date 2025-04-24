// Open Modal

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


