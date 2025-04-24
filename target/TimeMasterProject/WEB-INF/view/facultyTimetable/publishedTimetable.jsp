<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Timetable Management (Academic Office Staff)</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/publishedTimetable.css">
</head>
<body>
	<!-- Header -->
    <%@ include file="/WEB-INF/view/header/headerStaffTimetable.jsp" %>


	<!-- Timetable Section -->
	<div class="timetable">
		<div class="icons return">
			<a href="/TimeMasterProject/faculty/timetable/management"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
		</div>
		<h2>Timetable Year ${timetable.year }</h2>
         <table id="scheduleTable">
            <thead>
                <tr>
    				<th>No.</th>
					<th>Course Code</th>
					<th>Course Name</th>
					<th>Program</th>
					<th>Section</th>
					<th>Day</th>
					<th>Time</th>
					<th>Classroom</th>
					<th>Lecturer</th>
                </tr>
            </thead>
            <tbody>
                <!-- Predefined rows with classroom info -->
		        <c:forEach var="schedule" items="${schedules}" varStatus="status">
		            <tr>
		                <!-- No. -->
		                <td><c:out value="${status.index + 1}" /></td> 
		                
		                <!-- Course Code -->
		                <td><c:out value="${schedule.section.course.courseCode}" /></td>		
		               
		                <!-- Course Name -->
		                <td><c:out value="${schedule.section.course.courseName}" /></td> 		                
		                
		                <!-- Program -->
		                <td><c:out value="${schedule.section.program}" /></td> 
		
		                <!-- Section -->
		                <td><c:out value="${schedule.section.sectionName}" /></td> 
		                
		                <!-- Day -->
		                <td><c:out value="${schedule.timeSlot.day}" /></td> 
		                
		                <!-- Time -->
		                <td><c:out value="${schedule.timeSlot.time}" /></td> 
		                		
		                <!-- Classroom Name -->
		                <td><c:out value="${schedule.classroom.classroomName}" /></td> 
		
		                <!-- Lecturer -->
		                <td><c:out value="${schedule.lecturer.name}" /></td> 
		
		            </tr>
		        </c:forEach>
            </tbody>
        </table>

		<div class="result">
			<p>
				Show <span>1</span> to <span>10</span> of <span>76</span> results
			</p>
		</div>

		
		<nav>
			<ul class="pagination">
				<li class="page-item disabled"><a class="page-link" href="#"
					tabindex="-1">Previous</a></li>
				<li class="page-item active"><a class="page-link" href="#">1</a>
				</li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>


	</div>

	<!-- Footer -->
	<div class="footer">
		<p>© 2024 TimeMaster Inc. All rights reserved.</p>
	</div>
</body>
</html>