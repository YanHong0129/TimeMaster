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
    <%@ include file="/WEB-INF/view/header/headerStaff.jsp" %>
    		
    <div class="profile-container">

        <div class="profile-card">    
	        <div class="icons return">
					<a href="/TimeMasterProject/staff/home"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
			</div>
			<div class="profile-content">
	            <div class="profile-header">
	                <div class="profile-picture"></div>
	                <h2>${staff.name }</h2>
	                <p>(${staff.staffID })</p><br>
	            </div>
	            <div class="profile-details">
		            <div class="details-header">
		                <h3>User Details</h3>
		                <button class="edit-profile-btn" onclick="openEditForm()">Edit</button>
		            </div>	
	                <p>Email Address: <span>${staff.email }</span></p>
	                <p>Office Location: <span>${staff.office }</span></p>
	                <p>Faculty: <span>${staff.faculty }</span></p>
	            </div>
	            <form action="/TimeMasterProject/staff/password" method="GET">
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
	        <form id="editForm" action="profile/update" method="POST">
	            <label for="editEmail">Email Address:</label>
	            <input type="email" id="editEmail" name="email" value="${staff.email }" required>
	            <label for="editLocation" >Office Location:</label>
	            <input type="text" id="editLocation" name="location" value="${staff.office }" required>
	
	            <button type="submit">Save</button>
	        </form>
	    </div>
	</div>
	

     <!-- Footer -->
     <div class="footer">
         <p>© 2024 TimeMaster Inc. All rights reserved.</p>
     </div>
</body>
</html>