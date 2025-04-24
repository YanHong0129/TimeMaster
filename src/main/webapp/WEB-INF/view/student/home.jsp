<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page (student)</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/studentHome.css"> 
</head>
<body>
	    <!-- Header -->
   		 <%@ include file="/WEB-INF/view/header/headerStudentHome.jsp" %>


        <!-- Welcome Section -->
        <div class="welcome-section">
            <h1>Welcome to <span>TimeMaster</span>,</h1>
            <p>the ultimate solution for streamlined timetable management.</p>
         <img src="<%= request.getContextPath() %>/images/calendar.jpg" alt="Calendar Image">

        </div>

        <!-- Resource Management Section -->
        <div class="timetable-section">
            <h2>YEAR ${student.year}</h2>
			<a href="/TimeMasterProject/faculty/timetable/student/view?year=${student.year}" class="button">View Timetable</a>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>© 2024 TimeMaster Inc. All rights reserved.</p>
        </div>
</body>
</html>