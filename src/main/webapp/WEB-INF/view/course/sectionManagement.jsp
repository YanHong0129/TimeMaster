<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Sections</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css?v=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/courseManagement.css?v=1.0">
    <script src="<%= request.getContextPath() %>/js/sectionManagement.js?v=1.0"></script>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/view/header/headerStaff.jsp" %>

    <div class="manage-timetable">
        <br>
        <div class="icons">
            <a href="/TimeMasterProject/course/management"><i class="fa fa-arrow-left custom-arrow"></i></a>
        </div>
        <h2>Sections for Course: ${courseCode}</h2>
		<button class="addCourse-btn" id="addSectionBtn">Add Section</button> <!-- Add Section Button -->
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Section Name</th>
                    <th>Program</th>
                    <th>Capacity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="section" items="${sections}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${section.sectionName}</td>
                        <td>${section.program}</td>
                        <td>${section.capacity}</td>
                        <td>
                            <div class="actionIcons">
                                <!-- Edit Button -->
			                    <a href="#" 
			                       class="editSectionBtn" 
			                       data-section-id="${section.sectionID}" 
			                       data-section-name="${section.sectionName}" 
			                       data-program="${section.program}" 
			                       data-capacity="${section.capacity}">
			                        <i class="fa fa-pencil"></i>
			                    </a>
			                    <!-- Delete Button -->
			                    <a href="#" 
			                       class="deleteSectionBtn" 
			                       data-section-id="${section.sectionID}">
			                        <i class="fa fa-trash"></i>
			                    </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty sections}">
                    <tr>
                        <td colspan="5" style="text-align: center;">No sections available for this course.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
    
    
    <div id="edit-section-modal" class="modal">
	    <div class="modal-content">
	        <span class="close-btn" id="closeEditModalBtn">&times;</span>
	        <h2>Edit Section</h2>
	        <form id="editSectionForm" action="/TimeMasterProject/course/section/edit">
	            <input type="hidden" id="edit-section-id" name="sectionID" />
	
	            <label for="edit-section-name">Section Name:</label>
	            <input type="text" id="edit-section-name" name="sectionName" required />
	
	            <label for="edit-program">Program:</label>
	            <input type="text" id="edit-program" name="program" required />
	
	            <label for="edit-capacity">Capacity:</label>
	            <input type="number" id="edit-capacity" name="capacity" required />
	
	            <button type="submit" class="submit-btn">Save</button>
	        </form>
	    </div>
	</div>
	
	<!-- Add Section Modal -->
	<div id="add-section-modal" class="modal">
	    <div class="modal-content">
	        <span class="close-btn" id="closeAddModalBtn">&times;</span>
	        <h2>Add Section</h2>
	        <form id="addSectionForm" action="/TimeMasterProject/course/section/add">
	            <input type="hidden" id="add-course-code" name="courseCode" value="${courseCode}" />
	            
	            <label for="add-section-name">Section Name:</label>
	            <input type="text" id="add-section-name" name="sectionName" placeholder="Enter section name" required />
	            
	            <label for="add-program">Program:</label>
	            <input type="text" id="add-program" name="program" placeholder="Enter program" required />
	            
	            <label for="add-capacity">Capacity:</label>
	            <input type="number" id="add-capacity" name="capacity" placeholder="Enter capacity" required />
	            
	            <button type="submit" class="submit-btn">Add Section</button>
	        </form>
	    </div>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>© 2024 TimeMaster Inc. All rights reserved.</p>
    </div>
</body>
</html>

