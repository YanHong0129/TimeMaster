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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/passwordForm.css">
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/view/header/headerStudent.jsp" %>
    
	<div class="content">    
		<form action="/TimeMasterProject/student/profile" method="GET" style="display: inline;">
		    <button type="submit" class="close-btn">&times;</button>
		</form>
		<h2>Change Password</h2>	
		<!-- Modal for Changing Password -->
		<div id="passwordModal" class="modal">
	       
	        <form id="passwordForm" action="/TimeMasterProject/student/password/update" method="POST">
		        <label for="oldPassword">Old Password:</label>
				<div class="password-group">
		            <input type="password" id="oldPassword" name="oldPassword" placeholder="Enter your old password" required>
		             <button type="button" class="toggle-password" onclick="togglePassword('oldPassword')">👁️</button>					
				</div>
				
				
	            <label for="newPassword">New Password:</label>        	
				<div class="password-group">
	            	<input type="password" id="newPassword" name="newPassword" placeholder="Enter your new password" required>
       			    <button type="button" class="toggle-password" onclick="togglePassword('newPassword')">👁️</button>		
				</div>
				
		        <label for="confirmPassword">Confirm New Password:</label>				
				<div class="password-group">
		            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your new password" required>
		            <button type="button" class="toggle-password" onclick="togglePassword('confirmPassword')">👁️</button>		
				</div>
	
	            <button type="submit" id="submit">Save</button>
	        </form>
   			<c:if test="${not empty errorMessage}">
			    <script>
			        alert("${errorMessage}");
			    </script>
			</c:if>
			<c:if test="${not empty successMessage}">
			    <script>
			        alert("${sucessMessage}");
			    </script>
			</c:if>
	    </div>
	</div>
     <!-- Footer -->
     <div class="footer">
         <p>© 2024 TimeMaster Inc. All rights reserved.</p>
     </div>
</body>
</html>