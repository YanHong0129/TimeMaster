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


	   <!-- Manage Timetable Section -->
            <div class="manage-timetable">
            <br>
            <div class="icons">
                    <a href="/TimeMasterProject/staff/home"><i class="fa fa-arrow-left custom-arrow"></i></a>
            </div>
	            <h2>Manage Lecturer Details</h2>
				<input type="text" class="search-bar" placeholder="Search...">
	            <button class="addLecturer-btn" id="addBtn">Add Lecturer</button>
	            
	            <table>
	                <thead>
	                    <tr>
	                        <th>No.</th>
	                        <th>Lecturer ID</th>
	                        <th>Lecturer Name</th>
	                        <th>Email Address</th>
	                        <th>Phone Number</th>
	                        <th>Office Location</th>
	                        <th>Action</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td>1</td>
	                        <td>LD0001</td>
	                        <td>Prof. Lim Ming Ze</td>
	                        <td>petersonOhxuan@utm.my</td>
	                        <td>+6012 7384759</td>
	                        <td>N28A, 103</td>
	                        <td>
	                        	<div class="actionIcons">
                    				<a id="EditBtn" ><i class="fa fa-pencil"></i></a>
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
	                        
	                    </tr>
	                    <tr>
	                        <td>3</td>
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
	                        
	                    </tr>
	                    <tr>
	                        <td>5</td>
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
				    <span class="close-btn" id="closeBtn">&times;</span>
				    <h2>Add Lecturer</h2>
				    <form action="courseExample" method="post">
				      <label for="lecturer-ID">Lecturer ID:</label>
				      <input type="text" id="lecturer-ID" name="lecturer-ID" placeholder="Enter Lecturer ID" required />
				
				      <label for="lecturer-name">Lecturer Name:</label>
				      <input type="text" id="lecturer-name" name="lecturer-name" placeholder="Enter Lecturer Name" required />
				
				      <label for="email">Email Address:</label>
				      <input type="text" id="email" name="email" placeholder="Enter Email Address" required />
				
				      <label for="phone">Phone Number:</label>
				      <input type="text" id="phone" name="phone" placeholder="Enter Phone Number" required />
				
				      <label for="office">Office Location:</label>
				      <input type="number" id="office" name="office" placeholder="Enter Office Location" required />
				
				      <button type="submit" class="submit-btn">Add Lecturer</button>
				    </form>
				  </div>
				</div>
				
				<!-- Modal Structure for Editing Course -->
				<div id="edit-course-modal" class="modal">
				    <div class="modal-content">
				        <span class="close-btn" id="closeEditBtn">&times;</span>
				        <h2>Edit Lecturer</h2>
				        <form action="your_jsp_handler.jsp" method="post">
				            <label for="lecturer-ID">Lecturer ID:</label>
				      		<input type="text" id="lecturer-ID" name="lecturer-ID" placeholder="Enter Lecturer ID" required />
				
				            <label for="lecturer-name">Lecturer Name:</label>
						      <input type="text" id="lecturer-name" name="lecturer-name" placeholder="Enter Lecturer Name" required />
						
						      <label for="email">Email Address:</label>
						      <input type="text" id="email" name="email" placeholder="Enter Email Address" required />
						
						      <label for="phone">Phone Number:</label>
						      <input type="text" id="phone" name="phone" placeholder="Enter Phone Number" required />
						
						      <label for="office">Office Location:</label>
						      <input type="number" id="office" name="office" placeholder="Enter Office Location" required />
						
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