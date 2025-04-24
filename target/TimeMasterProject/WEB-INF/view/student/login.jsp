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
            <form action="/TimeMasterProject/processStudentLogin" method="POST">
                <label for="no-matric">No Matric</label>
                <div class="input-group">
                    <input type="text" id="no-matric" name="username" placeholder="Enter your no matric">
                </div>
                <label for="password">Password</label>
                <div class="input-group">
                    <input type="password" id="password" name="password" placeholder="Enter your password">
                    <span class="toggle" onclick="togglePassword('password')"></span>
                </div>

                <div class="options">
                    <p>Login as <a href="/TimeMasterProject/staff/login" class="toggle-link">Staff</a></p>
                </div>
                <button type="submit" class="btn">Sign in</button>               	
                <!-- Error message display -->
                <% if ("true".equals(request.getParameter("error"))) { %>
                    <p class="error-message">Invalid No Matric or password. Please try again.</p>
                <% } %>
            </form>
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
	            toggleButton.classList.add('hide');
	        } else {
	            passwordInput.type = 'password';
	            toggleButton.classList.remove('hide');
	        }
	    }
    </script>
</body>
</html>
