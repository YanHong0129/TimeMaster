<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TimeMaster Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="left-section">
            <h1>Welcome to <span class="brand-name">TimeMaster</span></h1>
            <h2>Login</h2>
            <c:if test="${role eq 'STAFF'}">
	            <form action="/TimeMasterProject/processLogin" method="POST">
	                <label for="staff-id">Staff ID</label>
	                <div class="input-group">
	                    <input type="text" id="staff-id" name="username" placeholder="Enter your staff ID">
	                </div>
	                <label for="password">Password</label>
	                <div class="input-group">
	                    <input type="password" id="staff-password" name="password" placeholder="Enter your password">
	                    <button type="button" class="toggle-password" onclick="togglePassword('staff-password')">👁️</button>
	                </div>
	               <div class="register">
	                	<a href="/TimeMasterProject/staff/register">Register Account</a>
	                </div>
		            <div class="options">
	                	<p>Login as <a href="/TimeMasterProject/login?role=STUDENT" class="toggle-link">Student</a></p>
	                </div>	                
		            <c:if test="${error}">
		                <p class="error-message">Invalid Staff ID or password. Please try again.</p>
		            </c:if>
		            <input type="hidden" name="role" value="STAFF">

	                <button type="submit" class="btn">Sign in</button>
	            </form>	                
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

            </c:if>
            <c:if test="${role eq 'STUDENT'}">
                <form action="/TimeMasterProject/processLogin" method="POST">
	                <label for="no-matric">No Matric</label>
	                <div class="input-group">
	                    <input type="text" id="no-matric" name="username" placeholder="Enter your no matric">
	                </div>
	                <label for="password">Password</label>
	                <div class="input-group">
	                    <input type="password" id="student-password" name="password" placeholder="Enter your password">
	                     <button type="button" class="toggle-password" onclick="togglePassword('student-password')">👁️</button>
	                </div>
	                <div class="register">
	                	<a href="/TimeMasterProject/student/register">Register Account</a>
	                </div>
		            <div class="options">
	                    <p>Login as <a href="/TimeMasterProject/login?role=STAFF" class="toggle-link">Staff</a></p>
		            </div>  	                
	                
                    <c:if test="${error}">
		                <p class="error-message">Invalid No Matric or password. Please try again.</p>
		            </c:if>
		            <input type="hidden" name="role" value="STUDENT">

	                <button type="submit" class="btn">Sign in</button>
	            </form> 	                
       		    <c:if test="${not empty successMessage}">
			    	<script>
			    		alert("${successMessage}")
			    	</script>
			    </c:if>
			    <c:if test="${not empty errorMessage}">
			       	<script>
			    		alert("${errorMessage}")
			    	</script>
			    </c:if>	
            </c:if>
        </div>
        <div class="right-section">
        </div>
    </div>

    <script>
	    function togglePassword(inputId) {
	        const passwordInput = document.getElementById(inputId);
	        const toggleButton = passwordInput.nextElementSibling;
	        
	        if (passwordInput.type === 'password') {
	            passwordInput.type = 'text';
	            toggleButton.textContent = '🙈'; // Change icon to "hide"
	        } else {
	            passwordInput.type = 'password';
	            toggleButton.textContent = '👁️'; // Change icon to "show"
	        }
	    }
    </script>
</body>
</html>