<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css?v=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/notification.css?v=1.0">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="<%= request.getContextPath() %>/js/notification.js" defer></script>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/view/header/headerStaff.jsp" %>

    <!-- Notification Section -->
    <div class="notification-container">
        <h2>Notification</h2>
        <div class="notification-list">
            <!-- Notification 1 -->
            <div class="notification-item viewed">
                <div class="notification-details">
                    <p><strong>System Update</strong> - The system will undergo maintenance on 25th Nov.</p>
                    <span class="status">Viewed</span>
                </div>
                <span class="timestamp">2 hours ago</span>
                <button class="delete-btn">Delete</button>
            </div>
			
            <!-- Notification 2 -->
            <div class="notification-item not-viewed">
                <div class="notification-details">
                    <p><strong>Timetable Updated</strong> - Year 3 Timetable has been updated.</p>
                    <span class="status">Not Viewed</span>
                </div>
                <span class="timestamp">Yesterday</span>
                <button class="delete-btn">Delete</button>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>© 2024 TimeMaster Inc. All rights reserved.</p>
    </div>
</body>
</html>