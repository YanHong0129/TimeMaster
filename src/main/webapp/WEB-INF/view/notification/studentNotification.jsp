<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
   	<%@ include file="/WEB-INF/view/header/headerStudent.jsp" %>
	
	
    <!-- Notification Section -->
    <div class="notification-container">
        <h2>Notification</h2>
        <div class="notification-list">
            <c:forEach var="notification" items="${notifications}">
                <div class="notification-item ${notification.read ? 'viewed' : 'not-viewed'}">
                    <div class="notification-details">
                        <p><strong>${notification.title}</strong> - ${notification.message}</p>
                        <span class="status">${notification.read ? 'Viewed' : 'Not Viewed'}</span>
                    </div>
                    <span class="timestamp">${notification.createdAt}</span>
                    <a href="<c:url value='/notification/student/read/${notification.notificationId}' />">
                        <button class="read-btn">Read</button>
                    </a>
                    <a href="<c:url value='/notification/student/delete/${notification.notificationId}' />">
                        <button class="delete-btn">Delete</button>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>© 2024 TimeMaster Inc. All rights reserved.</p>
    </div>
</body>
</html>