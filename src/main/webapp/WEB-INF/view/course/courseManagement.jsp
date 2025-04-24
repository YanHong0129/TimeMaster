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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css?v=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/courseManagement.css?v=1.0">
    <script src="<%= request.getContextPath() %>/js/courseManagement.js?v=1.0"></script>
</head>
<body>
	    <!-- Header -->
    	<%@ include file="/WEB-INF/view/header/headerStaff.jsp" %>


	       <div class="manage-timetable">
	        <br>
	        <div class="icons">
	            <a href="/TimeMasterProject/staff/home"><i class="fa fa-arrow-left custom-arrow"></i></a>
	        </div>
	        <h2>Manage Course Details</h2>
	        <input type="text" class="search-bar" id="searchCourseBar" placeholder="Search...">
	        <button class="addCourse-btn" id="addCourseBtn">Add Course</button>
                <!-- JavaScript Alert for Flash Messages -->
		        <script>
		            <c:if test="${not empty success}">
		                alert("${success}");
		            </c:if>
		            <c:if test="${not empty error}">
		                alert("${error}");
		            </c:if>
		        </script>
	        <table>
	            <thead>
	                <tr>
	                    <th>No.</th>
	                    <th>Course Code</th>
	                    <th>Course Name</th>
	                    <th>Course Credit</th>
	                    <th>Year</th>
	                    <th>Number of Sections</th>
	                    <th>Action</th>
	                </tr>
	            </thead>
	            <tbody id="courseTableBody">
	                <c:forEach var="course" items="${courses}" varStatus="status">
	                    <tr>
	                        <td>${status.index + 1}</td>
	                        <td>${course.courseCode}</td>
	                        <td>${course.courseName}</td>
	                        <td>${course.courseCredit}</td>
	                        <td>${course.year}</td>
	                        <td>${course.sectionCount}</td>
	                        <td>
	                            <div class="actionIcons">
	                                <a href="/TimeMasterProject/course/sections?courseCode=${course.courseCode}" 
	                                   class="ViewSectionsBtn" 
	                                   data-course-code="${course.courseCode}">
	                                   <i class="fa fa-eye"></i>
	                                </a>
	                                <a class="EditCourseBtn" 
	                                   data-course-code="${course.courseCode}" 
	                                   data-course-name="${course.courseName}" 
	                                   data-course-credit="${course.courseCredit}" 
	                                   data-year="${course.year}">
	                                   <i class="fa fa-pencil"></i>
	                                </a>
	                                <a class="DeleteCourseBtn" 
	                                   data-course-code="${course.courseCode}">
	                                   <i class="fa fa-trash"></i>
	                                </a>
	                            </div>
	                        </td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </div>
	    
	    <!-- Add Course Modal -->
	    <div id="add-course-modal" class="modal">
	        <div class="modal-content">
	            <span class="close-btn" id="closeModalBtn">&times;</span>
	            <h2>Add Course</h2>
	            <form id="addCourseForm" action="/TimeMasterProject/course/add" method="post">
	                <label for="course-code">Course Code:</label>
	                <input type="text" id="course-code" name="courseCode" placeholder="Enter course code" required>
	                
	                <label for="course-name">Course Name:</label>
	                <input type="text" id="course-name" name="courseName" placeholder="Enter course name" required>
	                
	                <label for="year">Year:</label>
	                <input type="text" id="year" name="year" placeholder="Enter year" required>
	                
	                <label for="course-credit">Course Credit:</label>
	                <input type="number" id="course-credit" name="courseCredit" placeholder="Enter course credit" required>
	                
	                <button type="submit" class="submit-btn">Add Course</button>
	            </form>
	        </div>
	    </div>
	    
	    <!-- Edit Course Modal -->
	    <div id="edit-course-modal" class="modal">
	        <div class="modal-content">
	            <span class="close-btn" id="closeEditModalBtn">&times;</span>
	            <h2>Edit Course</h2>
	            <form action="/TimeMasterProject/course/edit" method="post">
	                <input type="hidden" id="edit-course-code" name="courseCode">
	                
	                <label for="edit-course-name">Course Name:</label>
	                <input type="text" id="edit-course-name" name="courseName" required>
	                
	                <label for="edit-course-credit">Course Credit:</label>
	                <input type="number" id="edit-course-credit" name="courseCredit" required>
	                
	                <label for="edit-year">Year:</label>
	                <input type="text" id="edit-year" name="year" required>
	                
	                <button type="submit" class="submit-btn">Save Changes</button>
	            </form>
	        </div>
	    </div>

				
        <!-- Footer -->
        <div class="footer">
            <p>© 2024 TimeMaster Inc. All rights reserved.</p>
        </div>
</body>
</html>