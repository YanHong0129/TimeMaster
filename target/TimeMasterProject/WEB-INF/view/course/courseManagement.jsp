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


	   <!-- Manage Timetable Section -->
            <div class="manage-timetable">
            <br>
            <div class="icons">
                    <a href="/TimeMasterProject/staff/home"><i class="fa fa-arrow-left custom-arrow"></i></a>
            </div>
	            <h2>Manage Course Details</h2>
				<input type="text" class="search-bar" placeholder="Search...">
	            <button class="addCourse-btn" id="addCourseBtn">Add Course</button>
	            
	            <table>
	                <thead>
	                    <tr>
	                        <th>No.</th>
	                        <th>Course Code</th>
	                        <th>Course Name</th>
	                        <th>Program</th>
	                        <th>Year</th>
	                        <th>Course Credit</th>
	                        <th>Number of Section</th>
	                        <th>Capacity</th>
	                        <th>Action</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td>1</td>
	                        <td>SECJH3303</td>
	                        <td>Internet Programming</td>
	                        <td>SECJH</td>
	                        <td>3</td>
	                        <td>3</td>
	                        <td>5</td>
	                        <td>30</td>
	                        <td>
	                        	<div class="actionIcons">
                    				<a id="EditCourseBtn" ><i class="fa fa-pencil"></i></a>
                    				<a><i class="fa fa-trash"></i></a>
            					</div>
							</td>
	                        
	                    </tr>
	                    <tr>
	                        <td>2</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        
	                    </tr>
	                    <tr>
	                        <td>3</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        
	                    </tr>
	                    <tr>
	                        <td>4</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        
	                    </tr>
	                    <tr>
	                        <td>5</td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        
	                    </tr>
	                </tbody>
	            </table>
            </div>
            
            <!-- Modal Structure for Adding course -->
				<div id="add-course-modal" class="modal">
				  <div class="modal-content">
				    <span class="close-btn" id="closeModalBtn">&times;</span>
				    <h2>Add Course</h2>
				    <form action="courseExample" class="courseForm" method="post">
				      <label for="course-code">Course Code:</label>
				      <input type="text" id="course-code" name="course-code" placeholder="Enter course code" required />
				
				      <label for="course-name">Course Name:</label>
				      <input type="text" id="course-name" name="course-name" placeholder="Enter course name" required />
				
				      <label for="program">Program:</label>
				      <input type="text" id="program" name="program" placeholder="Enter program" required />
				
				      <label for="year">Year:</label>
				      <input type="text" id="year" name="year" placeholder="Enter year" required />
				
				      <label for="course-credit">Course credit:</label>
				      <input type="number" id="floor" name="course-credit" placeholder="Enter course credit" required />
				
					  <label for="section">Number of Section:</label>
				      <input type="text" id="section" name="section" placeholder="Enter number of section" required />
				      
				      <label for="capacity">Capacity:</label>
				      <input type="number" id="capacity" name="capacity" placeholder="Enter capacity" required />      
					 
				      <button type="submit" class="submit-btn">Add course</button>
				    </form>
				  </div>
				</div>
				
				<!-- Modal Structure for Editing Course -->
				<div id="edit-course-modal" class="modal">
				    <div class="modal-content">
				        <span class="close-btn" id="closeEditModalBtn">&times;</span>
				        <h2>Edit Course</h2>
				        <form action="your_jsp_handler.jsp" class="courseForm" method="post">
				            <!-- Hidden field for Course Code (used to identify which course is being edited) -->
				            <input type="hidden" id="edit-course-code" name="course-code" />
				
				            <label for="edit-course-name">Course Name:</label>
				            <input type="text" id="edit-course-name" name="course-name" placeholder="Enter course name" required />
				
				            <label for="edit-program">Program:</label>
				            <input type="text" id="edit-program" name="program" placeholder="Enter program" required />
				
				            <label for="edit-year">Year:</label>
				            <input type="text" id="edit-year" name="year" placeholder="Enter year" required />
				
				            <label for="edit-course-credit">Course Credit:</label>
				            <input type="number" id="edit-course-credit" name="course-credit" placeholder="Enter course credit" required />
				
				            <label for="edit-section">Number of Section:</label>
				            <input type="text" id="edit-section" name="section" placeholder="Enter number of section" required />
				
				            <label for="edit-capacity">Capacity:</label>
				            <input type="number" id="edit-capacity" name="capacity" placeholder="Enter capacity" required />
				
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