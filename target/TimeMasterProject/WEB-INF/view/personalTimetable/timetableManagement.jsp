<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Timetable(Student)</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/personalTimetableManagement.css?v=1.0">
	<script src="<%= request.getContextPath() %>/js/customize_timetable.js" defer></script>
</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/view/header/headerStudentTimetable.jsp" %>

		<div class="header-bar">
		    <div class="icons return">
		        <a href="/TimeMasterProject/student/home/">
		            <i class="fa fa-arrow-left" aria-hidden="true"></i>
		        </a>
		    </div>
		    <h2>Manage Timetable</h2>
		    <button class="export-btn">Export</button>
		</div>
	<!-- Timetable Section -->
	<div class="customize-timetable">

		<div class="overlay" id="overlay"></div>
		<!-- form for student to fill course in timetable -->
		<div class="form-container" id="form">
			<form action="example1" method="post">
				<i class="fa fa-times" id="close-btn" aria-hidden="true"></i>
				<h3>Enter Your Course Information</h3>
				<label for="course">Course </label><br> <input type="text" name="course"
					placeholder="Enter course e.g Internet Programming" required> <br> <label for="section">Section
				</label> <br>
				<input type="text" name="section" placeholder="Enter section e.g 03" required> <br> <label
					for="venue">Venue </label><br>
				<input type="text" name="venue" placeholder="Enter venue e.g MPK3" required> <br> <input type="submit"
					value="Save">
			</form>
		</div>
		
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
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>9:00 - 9:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>10:00 - 10:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>11:00 - 11:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>12:00 - 12:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
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
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>3:00 - 3:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>4:00 - 4:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
			<tr>
				<td>5:00 - 5:50</td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
				<td><button class="add-btn">Add</button></td>
			</tr>
		</table>
	</div>

	<!-- Footer -->
	<div class="footer">
		<p>© 2024 TimeMaster Inc. All rights reserved.</p>
	</div>
</body>
</html>