<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Registration</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css">
</head>
<body>
    <h1>Staff Registration</h1>
    <form action="<%=request.getContextPath()%>/staff/register/process" method="post">
        
        <label for="staffID">Staff ID:</label>
        <input type="text" id="staffID" name="staffID" value="${param.staffID}" placeholder="Enter your staff ID" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Enter your full name" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${param.email}" placeholder="Enter your email address" required><br><br>
        
        <label for="office">Office:</label>
        <input type="text" id="office" name="office" value="${param.office}" placeholder="Enter your office location" required><br><br>
        
        <label for="faculty">Faculty:</label>
        <select id="faculty" name="faculty" required>
            <option value="" disabled selected>Select Faculty</option>
            <option value="Faculty of Computing" ${param.faculty == 'Faculty of Computing' ? 'selected' : ''}>Faculty of Computing</option>
            <!-- Add more faculties as needed -->
        </select><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Create a strong password" required><br><br>
        
        <button type="submit">Register</button>
    </form>
</body>
</html>
