<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/studentProfile.css">
     <script src="<%= request.getContextPath() %>/js/studentProfileEdit.js" defer></script>
</head>
<body>
    <!-- Header -->
   	<%@ include file="/WEB-INF/view/header/headerStudent.jsp" %>
   		 
    <div class="profile-container">

        <div class="profile-card">    
	        <div class="icons return">
				<a href="/TimeMasterProject/student/home"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
			</div>
			<div class="profile-content">
	            <div class="profile-header">
	                <div class="profile-picture"></div>
	                <h2>${student.name }</h2>
	                <p>(${student.matricNo })</p><br>
	            </div>
	            <div class="profile-details">
		            <div class="details-header">
		                <h3>User Details</h3>
		                <button class="edit-profile-btn" onclick="openEditForm()">Edit</button>
		            </div>	
	                <p>Email Address: <span>${student.email }</span></p>
	                <p>Current Location: <span>${student.location }</span></p><br>
	                <h3>Academic Information</h3>
	                <p>Faculty: <span>${student.faculty }</span></p>
	                <p>Program of Study: <span>${student.program }</span></p>
	                <p>Year/Semester: <span>${student.year }/Semester ${student.semester }</span></p><br>
	            </div>
	            <form action="/TimeMasterProject/student/password" method="GET">
				    <button type="submit" class="change-password-btn">Change Password</button>
				</form>
			
			</div>
			<c:if test="${not empty successMessage}">
			    <script>
			        alert("${successMessage}");
			    </script>
			</c:if>
			<c:if test="${not empty errorMessage}">
			    <script>
			        alert("${errorMessage}");
			    </script>
			</c:if>
			
        </div>
    </div>
	<!-- Modal for Editing User Details -->
	<div id="editModal" class="modal">
	    <div class="modal-content">
	        <span class="close-btn" onclick="closeEditForm()">&times;</span>
	        <h2>Edit Profile</h2>
	        <form id="editForm" action="profile/update" method="post">
	            <label for="editEmail">Email Address:</label>
	            <input type="email" id="editEmail" name="email" value="${student.email }" required>
	
	            <label for="editLocation">Location:</label>
	            <input type="text" id="editLocation" name="location" value="${student.location }" required>
	
	            <button type="submit">Save</button>
	        </form>
	    </div>
	    
	</div>
	
	<!-- Modal for Changing Password -->
	<div id="passwordModal" class="modal">
	    <div class="modal-content">
	        <span class="close-btn" onclick="closePasswordForm()">&times;</span>
	        <h2>Change Password</h2>
	        <form id="passwordForm">
	            <label for="oldPassword">Old Password:</label>
			    <div class="password-container">
			        <input type="password" id="oldPassword" name="oldPassword" placeholder="Enter your old password" required>
			        <button type="button" class="toggle-password" onclick="togglePassword('oldPassword', this)">👁️</button>
			    </div>
			
			    <label for="newPassword">New Password:</label>
			    <div class="password-container">
			        <input type="password" id="newPassword" name="newPassword" placeholder="Enter your new password" required>
			        <button type="button" class="toggle-password" onclick="togglePassword('newPassword', this)">👁️</button>
			    </div>
			
			    <label for="confirmPassword">Confirm New Password:</label>
			    <div class="password-container">
			        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your new password" required>
			        <button type="button" class="toggle-password" onclick="togglePassword('confirmPassword', this)">👁️</button>
			    </div>
	
	            <button type="submit">Save</button>
	        </form>
	    </div>
	</div>
     <!-- Footer -->
     <div class="footer">
         <p>© 2024 TimeMaster Inc. All rights reserved.</p>
     </div>
     
     <script>
     document.getElementById("passwordForm").addEventListener("submit", function (event) {
         event.preventDefault(); // Prevent form from reloading the page
  
         const messageDiv = document.getElementById("message");
     
         const formData = new FormData(this);  console.log([...formData]);  // Log form data to check if it's correct
         // Send the form data via an AJAX POST request
         fetch("/TimeMasterProject/student/profile/changePassword", {
             method: "POST",
             body: formData
         })
         .then(response => {
             if (!response.ok) {
                 throw new Error(`HTTP error! status: ${response.status}`);
             }
             return response.text();
         })
         .then(message => {
             // Display the message in the message container
             messageDiv.style.color = message.includes("successfully") ? "green" : "red";
             messageDiv.textContent = message;
         })
         .catch(error => {
             console.error("Error:", error);
             messageDiv.style.color = "red";
             messageDiv.textContent = "An error occurred while processing your request.";
         });
     });
  // Toggle password visibility
     function togglePassword(fieldId, toggleButton) {
         const passwordField = document.getElementById(fieldId);
         const fieldType = passwordField.type;

         if (fieldType === "password") {
             passwordField.type = "text";
             toggleButton.textContent = "🙈"; // Change icon to "hide"
         } else {
             passwordField.type = "password";
             toggleButton.textContent = "👁️"; // Change icon to "show"
         }
     }
     </script>
</body>
</html>
