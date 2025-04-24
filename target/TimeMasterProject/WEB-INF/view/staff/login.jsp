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
            <form action="/TimeMasterProject/processStaffLogin" method="POST">
                <label for="staff-id">Staff ID</label>
                <div class="input-group">
                    <input type="text" id="staff-id" name="username" placeholder="Enter your staff ID">
                </div>
                <label for="password">Password</label>
                <div class="input-group">
                    <input type="password" id="password" name="password" placeholder="Enter your password">
                </div>
               <!-- Error message display -->
                <% if ("true".equals(request.getParameter("error"))) { %>
                    <p class="error-message">Invalid Staff ID or password. Please try again.</p>
                <% } %>
                <div class="options">
                	<p>Login as <a href="/TimeMasterProject/student/login" class="toggle-link">Student</a></p>
                </div>
                <button type="submit" class="btn">Sign in</button>
            </form>
        </div>
        <div class="right-section">
        </div>
    </div>
</body>
</html>
