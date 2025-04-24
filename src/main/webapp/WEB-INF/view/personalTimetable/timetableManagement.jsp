<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


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
		<form id="clearTimetableForm"
			action="<%=request.getContextPath()%>/personal/timetable/clear"
			method="POST">
			<input type="hidden" name="matricNo" value="${student.matricNo }">
			<button type="button" class="clear-btn"
				onclick="confirmClear('clearTimetableForm')">Clear</button>
		</form>
	</div>
	<!-- Timetable Section -->
	<div class="customize-timetable">

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

	<c:if test="${not empty successMessage}">
		<script>
        Swal.fire({
            title: 'Success!',
            text: '${successMessage}',
            icon: 'success',
            confirmButtonText: 'OK'
        });
    </script>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<script>
        Swal.fire({
            title: 'Error!',
            text: '${errorMessage}',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    </script>
	</c:if>

	<script>
		    function confirmClear(formId) {
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