<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css">
</head>
<body>
	
    <h1>Student Registration</h1>

    <form action="<%=request.getContextPath()%>/student/register/process" method="post">
        <label for="matricNo">Matric Number:</label>
        <input type="text" id="matricNo" name="matricNo" value="${param.matricNo}" placeholder="Enter your matric number" required><br><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Enter your full name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${param.email}" placeholder="Enter your email address" required><br><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" value="${param.location}" placeholder="Your current location" required><br><br>

        <label for="program">Program:</label>
        <input type="text" id="program" name="program" value="${param.program}" placeholder="Your academic program" required><br><br>

        <label for="year">Year:</label>
        <input type="number" id="year" name="year" min="1" max="4" value="${param.year}" placeholder="Year of study" required><br><br>

        <label for="semester">Semester:</label>
        <input type="number" id="semester" name="semester" min="1" max="2" value="${param.semester}" placeholder="Current semester" required><br><br>

        <label for="phoneNum">Phone Number:</label>
        <input type="text" id="phoneNum" name="phoneNum" value="${param.phoneNum}" placeholder="Your phone number" required><br><br>

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