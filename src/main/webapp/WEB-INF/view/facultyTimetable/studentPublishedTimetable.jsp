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
   	<%@ include file="/WEB-INF/view/header/headerStudent.jsp" %>


	<!-- Timetable Section -->
	<div class="timetable">
		<div class="icons return">
			<a href="/TimeMasterProject/student/home"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
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
		                <td><c:out value="${schedule.timeSlot.startTime}0 - ${schedule.timeSlot.endTime}0" /></td> 
		                		
		                <!-- Classroom Name -->
		                <td><c:out value="${schedule.classroom.classroomName}" /></td> 
		
		                <!-- Lecturer -->
		                <td><c:out value="${schedule.lecturer.name}" /></td> 
		
		            </tr>
		        </c:forEach>
            </tbody>
        </table>
		
	<div class="pagination">
	    <c:if test="${currentPage > 1}">
	        <a href="?year=${param.year}&page=${currentPage - 1}&pageSize=${pageSize}">Previous</a>
	    </c:if>
	    <c:forEach begin="1" end="${totalPages}" var="i">
	        <c:choose>
	            <c:when test="${i == currentPage}">
	                <strong>${i}</strong>
	            </c:when>
	            <c:otherwise>
	                <a href="?year=${param.year}&page=${i}&pageSize=${pageSize}">${i}</a>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	    <c:if test="${currentPage < totalPages}">
	        <a href="?year=${param.year}&page=${currentPage + 1}&pageSize=${pageSize}">Next</a>
	    </c:if>
	</div>



	</div>

	<!-- Footer -->
	<div class="footer">
		<p>© 2024 TimeMaster Inc. All rights reserved.</p>
	</div>
</body>
</html>