<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Header</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css">
    <script src="<%= request.getContextPath() %>/js/profile.js" defer></script>
</head>
<body>
	    <!-- Header -->
	    <div class="header">
	        <div class="logo">
	            <img src="<%= request.getContextPath() %>/images/logo.png" alt="TimeMaster Logo">
	        </div>
	        <div class="menu">
	            <a href="/TimeMasterProject/staff/home">Home</a>
	            <a href="/TimeMasterProject/faculty/timetable/management" class="active">Timetable Management</a>
	        </div>
	        <div class="title">Academic Office Staff</div>
	        <div class="icons">            
	            <a href="/TimeMasterProject/notification/staff" title="Notifications">
				    <i class="fa fa-bell-o"></i>
				</a>
	            <i class="fa fa-cog"></i>
	            <div class="user-dropdown">
				    <i class="fa fa-user-o" id="userIcon"></i>
				    <span class="user-name">${staff.name }</span>
				    <div class="dropdown-menu" id="dropdownMenu">
				        <a href="/TimeMasterProject/staff/profile">View Profile</a>
				        <a href="/TimeMasterProject/login">Log Out</a>
				    </div>
				</div>
	        </div>
	    </div>

</body>
</html>
