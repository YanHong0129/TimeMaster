<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Timetable Management (Academic Office Staff)</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css?v=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/classroomManagement.css?v=1.0">
    <script src="<%= request.getContextPath() %>/js/classroomManagement.js?v=1.0" defer></script>
    
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
	            <h2>Manage Classroom Details</h2>
				<input type="text" class="search-bar" placeholder="Search...">
	            <button class="addClassroom-btn" id="addClassroomBtn">Add Classroom</button>

	            <table>
	                <thead>
	                    <tr>
	                        <th>No.</th>
	                        <th>Classroom ID</th>
	                        <th>Classroom Name</th>
	                        <th>Abbreviation</th>
	                        <th>Block</th>
	                        <th>Floor</th>
	                        <th>Capacity</th>
	                        <th>Faculty</th>
	                        <th>Type</th>
	                        <th>Action</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td>1</td>
	                        <td>N28-105-01</td>
	                        <td>Bilik Kuliah 1</td>
	                        <td>BK1</td>
	                        <td>N28</td>
	                        <td>1</td>
	                        <td>120</td>
	                        <td>FC</td>
	                        <td>Bilik Kuliah</td>
	                        <td>
	                        	<div class="actionIcons">
                    				<a id="EditClassroomBtn" ><i class="fa fa-pencil"></i></a>
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
	                        <td></td>
	                    </tr>
	                </tbody>
	            </table>
            </div>
            
            <!-- Modal Structure for Adding Classroom -->
		        <div id="add-classroom-modal" class="modal">
		            <div class="modal-content">
		                <span class="close-btn" id="closeModalBtn">&times;</span>
		                <h2>Add Classroom</h2>
		                <form action="your_jsp_handler.jsp" class="classroomForm" method="post">
		                    <label for="classroom-id">Classroom ID:</label>
		                    <input type="text" id="classroom-id" name="classroom-id" placeholder="Enter classroom ID" required />
		        
		                    <label for="classroom-name">Classroom Name:</label>
		                    <input type="text" id="classroom-name" name="classroom-name" placeholder="Enter classroom name" required />
		        
		                    <label for="abbreviation">Abbreviation:</label>
		                    <input type="text" id="abbreviation" name="abbreviation" placeholder="Enter abbreviation" required />
		        
		                    <label for="block">Block:</label>
		                    <input type="text" id="block" name="block" placeholder="Enter block name" required />
		        
		                    <label for="floor">Floor:</label>
		                    <input type="number" id="floor" name="floor" placeholder="Enter floor number" required />
		        
		                    <label for="capacity">Capacity:</label>
		                    <input type="number" id="capacity" name="capacity" placeholder="Enter capacity" required />
		        
		                    <label for="faculty">Faculty:</label>
		                    <input type="text" id="faculty" name="faculty" placeholder="Enter faculty" required />
		        
		                    <label for="type">Type:</label>
		                    <select id="type" name="type">
		                        <option value="Bilik Kuliah">Bilik Kuliah</option>
		                        <option value="Lab">Lab</option>
		                        <option value="Auditorium">Auditorium</option>
		                    </select>
		                    
		                    <button type="submit" class="submit-btn">Add Classroom</button>
		                </form>
		            </div>
		        </div>
		        
		        <!-- Modal Structure for Editing Classroom -->
				<div id="edit-classroom-modal" class="modal">
				    <div class="modal-content">
				        <span class="close-btn" id="closeEditModalBtn">&times;</span>
				        <h2>Edit Classroom</h2>
				        <form action="your_jsp_handler.jsp" class="classroomForm" method="post">
				            <!-- Hidden field for Classroom ID (used to identify which classroom is being edited) -->
				            <input type="hidden" id="edit-classroom-id" name="classroom-id" />
				
				            <label for="classroom-name">Classroom Name:</label>
				            <input type="text" id="edit-classroom-name" name="classroom-name" placeholder="Enter classroom name" required />
				
				            <label for="abbreviation">Abbreviation:</label>
				            <input type="text" id="edit-abbreviation" name="abbreviation" placeholder="Enter abbreviation" required />
				
				            <label for="block">Block:</label>
				            <input type="text" id="edit-block" name="block" placeholder="Enter block name" required />
				
				            <label for="floor">Floor:</label>
				            <input type="number" id="edit-floor" name="floor" placeholder="Enter floor number" required />
				
				            <label for="capacity">Capacity:</label>
				            <input type="number" id="edit-capacity" name="capacity" placeholder="Enter capacity" required />
				
				            <label for="faculty">Faculty:</label>
				            <input type="text" id="edit-faculty" name="faculty" placeholder="Enter faculty" required />
				
				            <label for="type">Type:</label>
				            <select id="edit-type" name="type">
				                <option value="Bilik Kuliah">Bilik Kuliah</option>
				                <option value="Lab">Lab</option>
				                <option value="Auditorium">Auditorium</option>
				            </select>
				
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