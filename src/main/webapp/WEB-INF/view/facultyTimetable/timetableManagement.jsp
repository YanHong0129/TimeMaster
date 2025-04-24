<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Timetable Management (Academic Office Staff)</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/facultyTimetableManagement.css"> 
</head>
<body>
	    <!-- Header -->
    	<%@ include file="/WEB-INF/view/header/headerStaffTimetable.jsp" %>


	   <!-- Manage Timetable Section -->
	    <div class="manage-timetable">
	        <h2>Manage Timetable</h2>
			<form id="publish-timetable" action="publish" method="GET">
			    <button type="button" class="publish-btn" onclick="confirmPublish('publish-timetable')">Publish</button>
			</form>
	        
	        <table>
	            <tr>
	                <th>Day</th>
	                <th>Sunday</th>
	                <th>Monday</th>
	                <th>Tuesday</th>
	                <th>Wednesday</th>
	                <th>Thursday</th>
	            </tr>
				<tr>
				    <td>8:00 - 8:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_8.00-8.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_8.00-8.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_8.00-8.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_8.00-8.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_8.00-8.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>9:00 - 9:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_9.00-9.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_9.00-9.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_9.00-9.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_9.00-9.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_9.00-9.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>10:00 - 10:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_10.00-10.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_10.00-10.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_10.00-10.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_10.00-10.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_10.00-10.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>11:00 - 11:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_11.00-11.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_11.00-11.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_11.00-11.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_11.00-11.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_11.00-11.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>12:00 - 12:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_12.00-12.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_12.00-12.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_12.00-12.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_12.00-12.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_12.00-12.50" class="view-btn">View</a></td>
				</tr>
	            <tr>
	                <td>1:00 - 1:50</td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td></td>
	                <td></td>
	            </tr>
	            <tr>
				    <td>2:00 - 2:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_2.00-2.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_2.00-2.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_2.00-2.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_2.00-2.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_2.00-2.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>3:00 - 3:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_3.00-3.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_3.00-3.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_3.00-3.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_3.00-3.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_3.00-3.50" class="view-btn">View</a></td>
				</tr>
				<tr>
				    <td>4:00 - 4:50</td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=SUN_4.00-4.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=MON_4.00-4.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=TUE_4.00-4.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=WED_4.00-4.50" class="view-btn">View</a></td>
				    <td><a href="/TimeMasterProject/faculty/timetable/schedule?time_slot_id=THU_4.00-4.50" class="view-btn">View</a></td>
				</tr>
	        </table>
	    </div>
	
	    <!-- View Timetable Section -->
	    <div class="view-timetable">
	        <h2>View Timetable</h2>
	        <div class="card-container">
	            <div class="card" style="background-color: #d8f7b4;">
	                <i class="fa fa-calendar"></i>
	                <h3>Year 1</h3>
	                <a href="/TimeMasterProject/faculty/timetable/view?timetableID=Y1/S1" class="view-year-btn">View</a>
	            </div>
	            <div class="card" style="background-color: #f3ea9d;">
	                <i class="fa fa-calendar"></i>
	                <h3>Year 2</h3>
	                <a href="/TimeMasterProject/faculty/timetable/view?timetableID=Y2/S1" class="view-year-btn">View</a>
	            </div>
	            <div class="card" style="background-color: #f2a188;">
	                <i class="fa fa-calendar"></i>
	                <h3>Year 3</h3>
	                <a href="/TimeMasterProject/faculty/timetable/view?timetableID=Y3/S1" class="view-year-btn">View</a>
	            </div>
	            <div class="card" style="background-color: #f39590;">
	                <i class="fa fa-calendar"></i>
	                <h3>Year 4</h3>
	                <a href="/TimeMasterProject/faculty/timetable/view?timetableID=Y4/S1" class="view-year-btn">View</a>
	            </div>
	        </div>
	    </div>
        <!-- Footer -->
        <div class="footer">
            <p>© 2024 TimeMaster Inc. All rights reserved.</p>
        </div>
        <c:if test="${not empty successMessage}">
		    <script>
		        alert("${successMessage}");
		    </script>
		</c:if>
  			<c:if test="${not empty errorMessage}">
		    <script>
		        alert("${errorMessage}");
		    </script>
		</c:if>
		<script>
		    function confirmPublish(formId) {
		        Swal.fire({
		            title: 'Are you sure?',
		            text: "You won't be able to revert this!",
		            icon: 'warning',
		            showCancelButton: true,
		            confirmButtonColor: '#3085d6',
		            cancelButtonColor: '#d33',
		            confirmButtonText: 'Yes',
		            cancelButtonText: 'Cancel'
		        }).then((result) => {
		            if (result.isConfirmed) {
		                // Submit the form if the user confirms
		                document.getElementById(formId).submit();
		            }
		        });
		    }
		</script>
</body>
</html>