<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Schedule Form</title>
	<!-- Include jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/scheduleEditForm.css"> 
        <script src="<%= request.getContextPath() %>/js/schedule.js" defer></script>
</head>
<body>
	<div class="wrapper">
    <%@ include file="/WEB-INF/view/header/headerStaffTimetable.jsp" %>
    
    <!-- Content -->
    <div class="content">
        <div class="icons return">
			<a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=${timeSlotID}"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
		</div>
		<h2>Edit Schedule</h2>
		<form class="schedule-form-test" action="/TimeMasterProject/faculty/timetable/schedule/update" method="POST">
			<input type="hidden" id="timeSlotID" name="timeSlotID" value="${timeSlotID}">
			<input type="hidden" id="scheduleID" name="scheduleID" value="${schedule.scheduleID}">
		
		    <label for="course">Course</label>
		    <select class="select-course" id="course" name="course">
		        <option value="" selected disabled>Select Course</option>
		        <c:forEach var="course" items="${availableCourses}">
		            <option value="${course.courseCode}" 
		                ${schedule.section != null && course.courseCode == schedule.section.course.courseCode ? 'selected' : ''}>
		                ${course.courseCode} - ${course.courseName}
		            </option>
		        </c:forEach>
		    </select>
		
		    <label for="section">Section</label>
		    <select class="select-section" id="section" name="section">
		        <option value="" selected disabled>Select Section</option>
                <c:forEach var="section" items="${availableSections}">
		           <option value="${section.sectionID}" 
		               ${schedule.section != null && section.sectionID == schedule.section.sectionID ? 'selected' : ''}>
		               ${section.sectionName}
		           </option>
		       </c:forEach>
		    </select>
		
		    <label for="lecturer">Lecturer</label>
		    <select class="select-lecturer" id="lecturer" name="lecturer">
		        <option value="" selected disabled>Select Lecturer</option>
		        <c:forEach var="lecturer" items="${availableLecturers}">
		            <option value="${lecturer.lecturerID}" 
		                ${schedule.lecturer != null && lecturer.lecturerID == schedule.lecturer.lecturerID ? 'selected' : ''}>
		                ${lecturer.name}
		            </option>
		        </c:forEach>
		    </select>
		
		    <button type="submit" class="save-btn">Save</button>
		</form>

    </div>

    <!-- Footer -->
     <div class="footer">
         <p>© 2024 TimeMaster Inc. All rights reserved.</p>
     </div>
	</div>
	<!-- Select2 Initialization -->
    <script>
        $(document).ready(function() {
            // Apply Select2 to all dropdowns
            $('.select-course, .select-section, .select-lecturer').select2({
                placeholder: 'Select an option',
                allowClear: true,
                width: 'resolve'
            });
        });
    </script>
</body>
</html>