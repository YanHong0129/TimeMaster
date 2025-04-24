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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/lecturerManagement.css?v=1.0">
      <script src="<%= request.getContextPath() %>/js/lecturerManagement.js?v=1.0" defer></script>
</head>
<body>
	    <!-- Header -->
    	<%@ include file="/WEB-INF/view/header/headerStaff.jsp" %>


    <!-- Manage Lecturer Section -->
    <div class="manage-timetable">
        <br>
        <div class="icons">
            <a href="/TimeMasterProject/staff/home"><i class="fa fa-arrow-left custom-arrow"></i></a>
        </div>
        <h2>Manage Lecturer Details</h2>
        
        <!-- JavaScript Alert for Flash Messages -->
        <script>
            <c:if test="${not empty success}">
                alert("${success}");
            </c:if>
            <c:if test="${not empty error}">
                alert("${error}");
            </c:if>
        </script>
        <input type="text" id="searchKeyword" placeholder="Search..." style="padding: 5px; width: 250px;" />
        <button class="addLecturer-btn" id="addLecturerBtn">Add Lecturer</button>

        <div id="results" style="margin-top: 10px;">
            <table>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Lecturer ID</th>
                        <th>Lecturer Name</th>
                        <th>Email Address</th>
                        <th>Phone Number</th>
                        <th>Office Location</th>
                        <th>Faculty</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="lecturer" items="${lecturers}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${lecturer.lecturerID}</td>
                        <td>${lecturer.name}</td>
                        <td>${lecturer.email}</td>
                        <td>${lecturer.phoneNum}</td>
                        <td>${lecturer.office}</td>
                        <td>${lecturer.faculty}</td>
                        <td>
                            <div class="actionIcons">
                                <a id="EditLecturerBtn-${lecturer.lecturerID}" 
                                   class="EditLecturerBtn" 
                                   data-lecturer-id="${lecturer.lecturerID}" 
                                   data-name="${lecturer.name}" 
                                   data-email="${lecturer.email}" 
                                   data-phone="${lecturer.phoneNum}" 
                                   data-office="${lecturer.office}" 
                                   data-faculty="${lecturer.faculty}">
                                    <i class="fa fa-pencil"></i>
                                </a>
                                <a id="DeleteLecturerBtn-${lecturer.lecturerID}" 
                                   class="DeleteLecturerBtn" 
                                   data-lecturer-id="${lecturer.lecturerID}">
                                    <i class="fa fa-trash"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure for Adding Lecturer -->
    <div id="add-lecturer-modal" class="modal">
        <div class="modal-content">
            <span class="close-btn" id="closeAddModalBtn">&times;</span>
            <h2>Add Lecturer</h2>
            <form action="/TimeMasterProject/lecturer/add" method="post">
                <label for="lecturer-id">Lecturer ID:</label>
                <input type="text" id="lecturer-id" name="lecturerID" placeholder="Enter Lecturer ID" required />

                <label for="lecturer-name">Lecturer Name:</label>
                <input type="text" id="lecturer-name" name="name" placeholder="Enter Lecturer Name" required />

                <label for="email">Email Address:</label>
                <input type="text" id="email" name="email" placeholder="Enter Email Address" required />

                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phoneNum" placeholder="Enter Phone Number" required />

                <label for="office">Office Location:</label>
                <input type="text" id="office" name="office" placeholder="Enter Office Location" required />

                <label for="faculty">Faculty:</label>
                <input type="text" id="faculty" name="faculty" placeholder="Enter Faculty" required />

                <button type="submit" class="submit-btn">Add Lecturer</button>
            </form>
        </div>
    </div>

    <!-- Modal Structure for Editing Lecturer -->
    <div id="edit-lecturer-modal" class="modal">
        <div class="modal-content">
            <span class="close-btn" id="closeEditModalBtn">&times;</span>
            <h2>Edit Lecturer</h2>
            <form action="/TimeMasterProject/lecturer/edit" method="post">
                <input type="hidden" id="edit-lecturer-id" name="lecturerID" />

                <label for="edit-lecturer-name">Lecturer Name:</label>
                <input type="text" id="edit-lecturer-name" name="name" required />

                <label for="edit-email">Email Address:</label>
                <input type="text" id="edit-email" name="email" required />

                <label for="edit-phone">Phone Number:</label>
                <input type="text" id="edit-phone" name="phoneNum" required />

                <label for="edit-office">Office Location:</label>
                <input type="text" id="edit-office" name="office" required />

                <label for="edit-faculty">Faculty:</label>
                <input type="text" id="edit-faculty" name="faculty" required />

                <button type="submit" class="submit-btn">Save</button>
            </form>
        </div>
    </div>

				
        <!-- Footer -->
        <div class="footer">
            <p>© 2024 TimeMaster Inc. All rights reserved.</p>
        </div>
</body>
</html>