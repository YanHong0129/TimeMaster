<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Schedule Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/scheduleTable.css"> 
    <script src="<%= request.getContextPath() %>/js/schedule.js" defer></script>
</head>
<body>
	<div class="wrapper">
    <%@ include file="/WEB-INF/view/header/headerStaffTimetable.jsp" %>
    
    <!-- Content -->
    <div class="content">
        <div class="icons return">
			<a href="/TimeMasterProject/faculty/timetable/management"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
		</div>
		<h2>Schedule Table</h2>
         <p><strong>Day:</strong> ${timeSlot.day }</p>
         <p><strong>Time:</strong> ${timeSlot.time }</p>

         <table id="scheduleTable">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Classroom ID</th>
                    <th>Classroom Name</th>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Program</th>
                    <th>Section</th>
                    <th>Capacity</th>
                    <th>Lecturer</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Predefined rows with classroom info -->
		        <c:forEach var="schedule" items="${schedules}" varStatus="status">
		            <tr>
		                <!-- No. -->
		                <td><c:out value="${status.index + 1}" /></td> 
		
		                <!-- Classroom ID -->
		                <td><c:out value="${schedule.classroom.classroomID}" /></td> 
		
		                <!-- Classroom Name -->
		                <td><c:out value="${schedule.classroom.classroomName}" /></td> 
		
		                <!-- Course Code -->
		                <td><c:out value="${schedule.section.course.courseCode}" /></td> 
		
		                <!-- Course Name -->
		                <td><c:out value="${schedule.section.course.courseName}" /></td> 
		
		                <!-- Program -->
		                <td><c:out value="${schedule.section.program}" /></td> 
		
		                <!-- Section -->
		                <td><c:out value="${schedule.section.sectionName}" /></td> 
		
		                <!-- Capacity -->
		                <td><c:out value="${schedule.section.capacity}" /></td> 
		
		                <!-- Lecturer -->
		                <td><c:out value="${schedule.lecturer.name}" /></td> 
		
		                <!-- Actions -->
		                <td class="action-icons">
		                   <!-- <button class="open-modal-btn">
		                        <i class="fa fa-pencil"></i>
		                    </button>
		                    <button class="delete-btn">
		                        <i class="fa fa-trash-o"></i>
		                    </button>-->
		                    
		                        <!-- Edit Button -->
						    <form action="scheduleForm" method="get" style="display: inline;">
						        <input type="hidden" name="timeSlotID" value="${schedule.timeSlot.timeSlotID}" />
						        <input type="hidden" name="scheduleID" value="${schedule.scheduleID}" />
						        <button type="submit" class="edit-btn">
						            <i class="fa fa-pencil"></i>
						        </button>
						    </form>
						
						    <!-- Delete Button -->
							<form id="deleteForm-${schedule.scheduleID}" action="schedule/delete" method="post" style="display: inline;">
							    <input type="hidden" name="timeSlotID" value="${schedule.timeSlot.timeSlotID}" />
							    <input type="hidden" name="scheduleID" value="${schedule.scheduleID}" />
							    <button type="button" class="delete-btn" onclick="confirmDelete('${schedule.scheduleID}')">
							        <i class="fa fa-trash-o"></i>
							    </button>
							</form>
		                </td>
		            </tr>
		        </c:forEach>
            </tbody>
        </table>
        <div>
	        <c:if test="${currentPage > 1}">
	            <a href="?time_slot_id=${param.time_slot_id}&page=${currentPage - 1}">Previous</a>
	        </c:if>
	        <c:forEach begin="1" end="${totalPages}" var="i">
	            <c:choose>
	                <c:when test="${i == currentPage}">
	                    <strong>${i}</strong>
	                </c:when>
	                <c:otherwise>
	                    <a href="?time_slot_id=${param.time_slot_id}&page=${i}">${i}</a>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:if test="${currentPage < totalPages}">
	            <a href="?time_slot_id=${param.time_slot_id}&page=${currentPage + 1}">Next</a>
	        </c:if>
	        
   			<c:if test="${not empty successMessage1}">
			    <script>
			        alert("${successMessage1}");
			    </script>
			</c:if>
   			<c:if test="${not empty errorMessage1}">
			    <script>
			        alert("${errorMessage1}");
			    </script>
			</c:if>
   			<c:if test="${not empty errorMessage2}">
			    <script>
			        alert("${errorMessage2}");
			    </script>
			</c:if>
  			<c:if test="${not empty errorMessage3}">
			    <script>
			        alert("${errorMessage2}");
			    </script>
			</c:if>
	    </div>
  	
    </div>
        <!-- Modal -->
    <div class="modal" id="modal">
        <div class="modal-content">
            <span class="close-btn">&times;</span>
            <h3></h3>

            <!-- Form -->
            <form action="your_jsp_handler.jsp" method="post">
                <label for="course">Course</label>
                <select id="course" name="course">
                    <option value="" selected disabled>Select Course</option>
                    <option value="CS101">CS101</option>
                </select>

                <label for="program">Program</label>
                <select id="program" name="program">
                    <option value="" selected disabled>Select Program</option>
                    <option value="SE">Software Engineering</option>
                </select>

                <label for="section">Section</label>
                <select id="section" name="section">
                    <option value="" selected disabled>Select Section</option>
                </select>

                <label for="lecturer">Lecturer</label>
                <select id="lecturer" name="lecturer">
                    <option value="" selected disabled>Select Lecturer</option>
                </select>

                <button type="submit" class="save-btn">Save</button>
            </form>
        </div>
    </div>     
    <!-- Footer -->
     <div class="footer">
         <p>© 2024 TimeMaster Inc. All rights reserved.</p>
     </div>
	</div>
	<script>
	    function confirmDelete(scheduleID) {
	        Swal.fire({
	            title: 'Are you sure?',
	            text: "You won't be able to revert this!",
	            icon: 'warning',
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: 'Yes, delete it!',
	            cancelButtonText: 'Cancel'
	        }).then((result) => {
	            if (result.isConfirmed) {
	                // Submit the form if the user confirms
	                document.getElementById('deleteForm-' + scheduleID).submit();
	            }
	        });
	    }
	</script>
</body>
</html>