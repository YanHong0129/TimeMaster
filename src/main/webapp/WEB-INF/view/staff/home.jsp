<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page (Academic Office Staff)</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/staffHome.css"> 
</head>
<body>
		<%@ include file="/WEB-INF/view/header/headerStaffHome.jsp" %>


        <!-- Welcome Section -->
        <div class="welcome-section">
            <h1>Welcome to <span>TimeMaster</span>,</h1>
            <p>the ultimate solution for streamlined timetable management.</p>
         <img src="<%= request.getContextPath() %>/images/calendar.jpg" alt="Calendar Image">

        </div>

        <!-- Resource Management Section -->
        <div class="resource-section">
            <h2>Resource Management</h2>
            <div class="card-container">
                <div class="card">
                    <img src="<%= request.getContextPath() %>/images/lecturer.jpg" alt="Lecturer">
                    <div class="card-content">
                        <h3>Manage Lecturer Details</h3>
                        <p>Manage the latest lecturers' information</p>
                        <a href="/TimeMasterProject/lecturer/management" class="button">View</a>
                    </div>
                </div>
                <div class="card">
                    <img src="<%= request.getContextPath() %>/images/classroom.jpg" alt="Classroom">
                    <div class="card-content">
                        <h3>Manage Classroom Details</h3>
                        <p>Manage the classrooms' information</p>
                        <a href="/TimeMasterProject/classroom/management" class="button">View</a>
                    </div>
                </div>
                <div class="card">
                    <img src="<%= request.getContextPath() %>/images/course.jpg" alt="Course">
                    <div class="card-content">
                        <h3>Manage Course List</h3>
                        <p>Manage the offered course list</p>
                        <a href="/TimeMasterProject/course/management" class="button">View</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>© 2024 TimeMaster Inc. All rights reserved.</p>
        </div>
</body>
</html>


