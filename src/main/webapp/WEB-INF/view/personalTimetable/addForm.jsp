<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Timetable(Student) Add Form</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/personalTimetableManagement.css?v=1.0">

</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/view/header/headerStudentTimetable.jsp"%>

	<div class="header-bar">
		<div class="icons return">
			<a href="/TimeMasterProject/student/home/"> <i
				class="fa fa-arrow-left" aria-hidden="true"></i>
			</a>
		</div>
		<h2>Manage Timetable</h2>
		<button class="export-btn">Clear</button>
	</div>
	<!-- Timetable Section -->
	<div class="customize-timetable">
		<div class="overlay" id="overlay"></div>
		<!-- form for student to fill course in timetable -->
		<div class="form-container" id="form">
			<form
				action="<%=request.getContextPath()%>/personal/timetable/add?timeSlotId=${timeSlotId}"
				method="POST">
				<a href="/TimeMasterProject/personal/timetable/"><i
					class="fa fa-times" id="close-btn" aria-hidden="true"></i></a>
				<h3>Enter Your Course Information</h3>

				<!-- Hidden fields to pass matricNo and timeSlotId to the controller -->
				<input type="hidden" name="matricNo" value="${student.matricNo }">
				<label for="course">Course </label><br> <input type="text"
					name="course" placeholder="Enter course e.g Internet Programming"
					value="${timetable.course != null ? timetable.course : ''}"
					required><br> <label for="section">Section </label><br>
				<input type="text" name="section" placeholder="Enter section e.g 03"
					value="${timetable.section != null ? timetable.section : ''}"
					required><br> <label for="venue">Venue </label><br>
				<input type="text" name="venue" placeholder="Enter venue e.g MPK3"
					value="${timetable.venue != null ? timetable.venue : ''}" required><br>
					<div class="checkbox-container">
					<input type="checkbox" id="addNext" name="addNext" value="true" />
					<label for="addNext">Next Timeslot</label>
				</div>
				
				<input type="submit" value="Save">
				<!-- Delete Button with JavaScript -->
				<button type="button" id="deleteButton" class="delete-btn"
					onclick="deleteTimetable('${timeSlotId}')">Delete</button>

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

			<!-- 8:00 - 8:50 -->
			<tr>
				<td>8:00 - 8.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_8.00-8.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '8.00-8.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 9:00 - 9.50 -->
			<tr>
				<td>9:00 - 9.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_9.00-9.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '9.00-9.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 10:00 - 10.50 -->
			<tr>
				<td>10:00 - 10.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_10.00-10.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '10.00-10.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 11:00 - 11.50 -->
			<tr>
				<td>11:00 - 11.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

						<td><a href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_11.00-11.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
						aria-hidden="true"></i></a>
					
					<c:forEach var="timeSlot" items="${timeSlots}">
						<c:if
							test="${timeSlot.day eq day && timeSlot.time eq '11.00-11.50'}">
							<c:forEach var="studentTimetable" items="${timetable}">
								<c:if
									test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
									<c:choose>
										<c:when
											test="${studentTimetable.course == null || studentTimetable.course == ''}">
											<!-- No course, show only the button -->
										</c:when>
										<c:otherwise>
											<p>${studentTimetable.course}</p>
											<p>${studentTimetable.section}</p>
											<p>${studentTimetable.venue}</p>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
					</td>
				</c:forEach>
			</tr>

			<!-- 12:00 - 12.50 -->
			<tr>
				<td>12:00 - 12.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_12.00-12.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '12.00-12.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 1:00 - 1.50 (No classes here, no add buttons) -->
			<tr>
				<td>1:00 - 1.50</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<!-- 2:00 - 2.50 -->
			<tr>
				<td>2:00 - 2.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_2.00-2.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '2.00-2.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 3:00 - 3.50 -->
			<tr>
				<td>3:00 - 3.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_3.00-3.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '3.00-3.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 4:00 - 4.50 -->
			<tr>
				<td>4:00 - 4.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_4.00-4.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '4.00-4.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

			<!-- 5:00 - 5.50 -->
			<tr>
				<td>5:00 - 5.50</td>
				<c:forEach var="day" items="${daysOfWeek}">

					<td><a
						href="/TimeMasterProject/personal/timetable/add?timeSlotId=${day.substring(0, 3)}_5.00-5.50"
						class="edit-btn"> <i class="fa fa-pencil-square-o"
							aria-hidden="true"></i>
					</a> <c:forEach var="timeSlot" items="${timeSlots}">
							<c:if
								test="${timeSlot.day eq day && timeSlot.time eq '5.00-5.50'}">
								<c:forEach var="studentTimetable" items="${timetable}">
									<c:if
										test="${studentTimetable.timeSlot.day eq timeSlot.day && studentTimetable.timeSlot.time eq timeSlot.time}">
										<c:choose>
											<c:when
												test="${studentTimetable.course == null || studentTimetable.course == ''}">
												<!-- No course, show only the button -->
											</c:when>
											<c:otherwise>
												<p>${studentTimetable.course}</p>
												<p>${studentTimetable.section}</p>
												<p>${studentTimetable.venue}</p>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>

		</table>

	</div>

	<!-- Footer -->
	<div class="footer">
		<p>© 2024 TimeMaster Inc. All rights reserved.</p>
	</div>
</body>
<script>
function deleteTimetable(timeSlotId) {
	// Get the checkbox value
    var addNextChecked = document.getElementById("addNext").checked;
    
    // If checked, set addNext to "true", otherwise to "false"
    var addNextValue = addNextChecked ? "true" : "false";
	var form = document.createElement('form');
    form.method = 'POST';
    form.action = '<%=request.getContextPath()%>/personal/timetable/delete';
    
    // Create an input field to store the timeSlotId
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'timeSlotId';
    input.value = timeSlotId;
    
 // Create an input field for addNext
    var addNextInput = document.createElement('input');
    addNextInput.type = 'hidden';
    addNextInput.name = 'addNext';
    addNextInput.value = addNextValue;
    
    // Append the input field to the form
    form.appendChild(input);
    form.appendChild(addNextInput);
    
    // Append the form to the body (it won't be visible to the user)
    document.body.appendChild(form);
    
    // Submit the form
    form.submit();
}
</script>

</html>