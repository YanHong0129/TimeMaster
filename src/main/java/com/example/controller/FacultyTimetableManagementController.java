package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.entity.Course;
import com.example.entity.FacultyTimetable;
import com.example.entity.Lecturer;
import com.example.entity.LecturerDTO;
import com.example.entity.Notification;
import com.example.entity.PublishedSchedule;
import com.example.entity.Schedule;
import com.example.entity.Section;
import com.example.entity.SectionDTO;
import com.example.entity.Staff;
import com.example.entity.Student;
import com.example.entity.TimeSlot;
import com.example.service.CourseService;
import com.example.service.FacultyTimetableService;
import com.example.service.LecturerService;
import com.example.service.NotificationService;
import com.example.service.PublishedScheduleService;
import com.example.service.ScheduleService;
import com.example.service.SectionService;
import com.example.service.StudentService;
import com.example.service.TimeSlotService;

@Controller
@RequestMapping("/faculty/timetable")
public class FacultyTimetableManagementController {
	
    @Autowired
    FacultyTimetableService facultyTimetableService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PublishedScheduleService publishedScheduleService;
    @Autowired
    TimeSlotService timeSlotService;
	@Autowired
	CourseService courseService;
    @Autowired
    SectionService sectionService;
    @Autowired
    LecturerService lecturerService;
    @Autowired
    StudentService studentService;
    @Autowired
    NotificationService notificationService;
    
    @RequestMapping("/management")
    public String showTimetableManagementPage(HttpSession session,
            Model model) {
        Staff staff = (Staff) session.getAttribute("user");
        model.addAttribute("staff",staff);
        return "facultyTimetable/timetableManagement";
    }
    
    @RequestMapping("/schedule")
    public String showScheduleWithPagination(
            @RequestParam("time_slot_id") String timeSlotId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            HttpSession session,
            Model model) {
    	
   	 	System.out.println("TimeSlot: " + timeSlotId);
    	TimeSlot timeSlot = timeSlotService.getTimeSlotByID(timeSlotId);
        int pageSize = 20; // Maximum schedules per page
        int offset = (page - 1) * pageSize; // Calculate offset
        Staff staff = (Staff) session.getAttribute("user");
        // Get schedules for the current page
        List<Schedule> schedules = scheduleService.getSchedulesWithPagination(timeSlotId, offset, pageSize);
        schedules.get(0);
        int totalSchedules = scheduleService.getScheduleCount(timeSlotId); // Implement this method
        int totalPages = (int) Math.ceil((double) totalSchedules / pageSize);

        // Add data to the model
        model.addAttribute("staff",staff);
        model.addAttribute("schedules", schedules);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("timeSlot", timeSlot);

        return "facultyTimetable/scheduleTable";
    }

    @RequestMapping("/scheduleForm")
    public String showScheduleForm(
            @RequestParam("timeSlotID") String timeSlotID,
            @RequestParam("scheduleID") String scheduleID,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String section,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
    	
        Staff staff = (Staff) session.getAttribute("user");
        model.addAttribute("staff",staff);
        
    	 System.out.println("TimeSlot: " + timeSlotID);
        List<Course> availableCourses = courseService.getAvaillableCourses();
        model.addAttribute("availableCourses", availableCourses);
        
        Schedule schedule = scheduleService.getScheduleByID(scheduleID);
        Schedule nextSchedule = scheduleService.getNextSchedule(scheduleID);
        Schedule previousSchedule = scheduleService.getPreviousSchedule(scheduleID);
        // Log details about current, previous, and next schedules
        if (schedule != null) {
            System.out.println("Current Schedule ID: " + schedule.getScheduleID());
            System.out.println("Current Section: " + 
                (schedule.getSection() != null ? schedule.getSection().getSectionID() : "null"));
        }
        if (previousSchedule != null) {
            System.out.println("Previous Schedule ID: " + previousSchedule.getScheduleID());
            System.out.println("Previous Section: " + 
                (previousSchedule.getSection() != null ? previousSchedule.getSection().getSectionID() : "null"));
        } else {
            System.out.println("No previous schedule found.");
        }
        if (nextSchedule != null) {
            System.out.println("Next Schedule ID: " + nextSchedule.getScheduleID());
        } else {
            System.out.println("No next schedule found.");
        }
//        System.out.println(schedule.getSection().getSectionID().equals(previousSchedule.getSection().getSectionID()));
        TimeSlot timeSlot = timeSlotService.getTimeSlotByID(timeSlotID);
        if((timeSlot.getTime().equals("12.00-12.50")||timeSlot.getTime().equals("4.00-4.50"))) {
        	redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot is not available!");
        	return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;
        }
        else {
	        model.addAttribute("timeSlotID", timeSlotID);
	        model.addAttribute("scheduleID", scheduleID);
	        model.addAttribute("schedule", schedule);
	        if(schedule.getSection() == null){
	        	if(nextSchedule.getSection()!=null){
	        		redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot is not available!");
	            	return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;	        		
	        	}
	        	else {
	        		return "facultyTimetable/editScheduleForm";	        		
	        	}
	        }
        	List<Section> availableSections = sectionService.getAvailableSections(schedule.getSection().getCourse().getCourseCode());
        	availableSections.add(schedule.getSection());
        	List<Lecturer> availableLecturers = lecturerService.getAvailableLecturers(timeSlotID);
        	availableLecturers.add(schedule.getLecturer());
            model.addAttribute("availableSections", availableSections);
            model.addAttribute("availableLecturers", availableLecturers);
        	if (previousSchedule != null && previousSchedule.getSection() != null) { 
        		if(schedule.getSection().getSectionID().equals(previousSchedule.getSection().getSectionID())) {
	         		redirectAttributes.addFlashAttribute("errorMessage", "Please edit from start time!");
	        		TimeSlot preTimeSlot = timeSlotService.getPreviousTimeSlot(timeSlotID);
	            	return "redirect:/faculty/timetable/schedule?time_slot_id=" + preTimeSlot.getTimeSlotID();       			
        		}
        		else {
		            return "facultyTimetable/editScheduleFormFilled";
        		}
        	}
        	else {
        		if(nextSchedule != null && nextSchedule.getSection() != null) {
        			if(schedule.getSection().getSectionID().equals(nextSchedule.getSection().getSectionID())) {
        	            return "facultyTimetable/editScheduleFormFilled";     			
            		}
        			else {
        				redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot is not available!");
    	            	return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;	
        			}
        		}
        		else {
        			return "facultyTimetable/editScheduleFormFilled";   
        		}
        	}	        	
        }
    }

    @RequestMapping("/getSectionsAndLecturers")
    @ResponseBody
    public Map<String, Object> getSectionsAndLecturers(@RequestParam("courseCode") String courseCode,@RequestParam String timeSlotID) {
        Map<String, Object> response = new HashMap<>();
   	 	System.out.println("TimeSlot: " + timeSlotID);
   	 	System.out.println("Course: " + courseCode);
        // Get the available sections for the course
        List<SectionDTO> availableSections = sectionService.getAvailableSections(courseCode)
        .stream()
        .map(section -> new SectionDTO(section.getSectionID(), section.getSectionName()))
        .collect(Collectors.toList());     
        response.put("sections", availableSections);
        
        // Get the available lecturers for the time
        List<LecturerDTO> availableLecturers = lecturerService.getAvailableLecturers(timeSlotID)
        .stream()
        .map(lecturer -> new LecturerDTO(lecturer.getLecturerID(), lecturer.getName()))
        .collect(Collectors.toList());
        response.put("lecturers", availableLecturers);
        System.out.println("Lecturers: " + availableLecturers);
        return response;
    }
    
    
    @PostMapping("/schedule/update")
    public String updateSchedule( @RequestParam("timeSlotID") String timeSlotID,
          @RequestParam("scheduleID") String scheduleID,
          @RequestParam("course") String courseCode,
          @RequestParam("section") String sectionID,
          @RequestParam("lecturer") String lecturerID,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {

    	Course course = courseService.getCourseByCourseCode(courseCode);
    	Section section = sectionService.getSectionByID(sectionID);
    	Lecturer lecturer = lecturerService.getLecturerByID(lecturerID);
    	String facultyTimetableID = " ";
    	switch(course.getYear()) {
    		case 1: facultyTimetableID = "Y1/S1";
    				break;
    		case 2: facultyTimetableID = "Y2/S1";
					break;
    		case 3: facultyTimetableID = "Y3/S1";
					break;
    		case 4: facultyTimetableID = "Y4/S1";
					break;
    	}
    	FacultyTimetable facultyTimetable = facultyTimetableService.getFacultyTimetableByID(facultyTimetableID);
    	
    	Schedule schedule = scheduleService.getScheduleByID(scheduleID);    	
    	schedule.setSection(section);
    	schedule.setLecturer(lecturer);
    	schedule.setFacultyTimetable(facultyTimetable);
    	Schedule nextSchedule = scheduleService.getNextSchedule(scheduleID);    	
    	nextSchedule.setSection(section);
    	nextSchedule.setLecturer(lecturer);
    	nextSchedule.setFacultyTimetable(facultyTimetable);
        // Update the schedule using the data provided
        boolean isUpdated = (scheduleService.updateSchedule(schedule)&&scheduleService.updateSchedule(nextSchedule));
        if (isUpdated) {
        	redirectAttributes.addFlashAttribute("successMessage", "Schedule updated successfully!");
        } else {
        	redirectAttributes.addFlashAttribute("errorMessage", "Failed to update schedule. Please try again.");
        }
        return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;

    }
    
    @PostMapping("/schedule/delete")
    public String deleteSchedule(
            @RequestParam("timeSlotID") String timeSlotID,
            @RequestParam("scheduleID") String scheduleID,
            Model model,
            RedirectAttributes redirectAttributes) {
        Schedule schedule = scheduleService.getScheduleByID(scheduleID);
        Schedule nextSchedule = scheduleService.getNextSchedule(scheduleID);
        Schedule previousSchedule = scheduleService.getPreviousSchedule(scheduleID);
    	if (previousSchedule != null && previousSchedule.getSection() != null) { 
    		if(schedule.getSection().getSectionID().equals(previousSchedule.getSection().getSectionID())) {
         		redirectAttributes.addFlashAttribute("errorMessage", "Please clear from start time!");
        		TimeSlot preTimeSlot = timeSlotService.getPreviousTimeSlot(timeSlotID);
            	return "redirect:/faculty/timetable/schedule?time_slot_id=" + preTimeSlot.getTimeSlotID();       			
    		}
    		else {
    	    	schedule.setSection(null);
    	    	schedule.setLecturer(null);
    	    	schedule.setFacultyTimetable(null);
    	    	nextSchedule.setSection(null);
    	    	nextSchedule.setLecturer(null);
    	    	nextSchedule.setFacultyTimetable(null);
    	    	 boolean isUpdated = (scheduleService.updateSchedule(schedule)&&scheduleService.updateSchedule(nextSchedule));
    	         if (isUpdated) {
    	        	 redirectAttributes.addFlashAttribute("successMessage", "Schedule have been cleared successfully!");
    	         } else {
    	        	 redirectAttributes.addFlashAttribute("errorMessage", "Failed to clear schedule!");
    	         }
    	         return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;
    		}
    	}
    	else {
    		if(nextSchedule != null && nextSchedule.getSection() != null) {
    			if(schedule.getSection().getSectionID().equals(nextSchedule.getSection().getSectionID())) {
    		    	schedule.setSection(null);
    		    	schedule.setLecturer(null);
    		    	schedule.setFacultyTimetable(null);
    		    	nextSchedule.setSection(null);
    		    	nextSchedule.setLecturer(null);
    		    	nextSchedule.setFacultyTimetable(null);
    		    	 boolean isUpdated = (scheduleService.updateSchedule(schedule)&&scheduleService.updateSchedule(nextSchedule));
    		         if (isUpdated) {
    		        	 redirectAttributes.addFlashAttribute("successMessage", "Schedule have been cleared successfully!");
    		         } else {
    		        	 redirectAttributes.addFlashAttribute("errorMessage", "Failed to clear schedule!");
    		         }    
    		         return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;
        		}
    			else {
    				redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot cannot clear!");
	            	return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;	
    			}
    		}
    		else {
		    	schedule.setSection(null);
		    	schedule.setLecturer(null);
		    	schedule.setFacultyTimetable(null);
		    	nextSchedule.setSection(null);
		    	nextSchedule.setLecturer(null);
		    	nextSchedule.setFacultyTimetable(null);
		    	 boolean isUpdated = (scheduleService.updateSchedule(schedule)&&scheduleService.updateSchedule(nextSchedule));
		         if (isUpdated) {
		        	 redirectAttributes.addFlashAttribute("successMessage", "Schedule have been cleared successfully!");
		         } else {
		        	 redirectAttributes.addFlashAttribute("errorMessage", "Failed to clear schedule!");
		         }    
		         return "redirect:/faculty/timetable/schedule?time_slot_id=" + timeSlotID;
    		}
    	}

    }
    
    
    @GetMapping("/publish")
	public String publishTimetable(RedirectAttributes redirectAttributes) {
		List<Schedule> schedules = scheduleService.getAllSchedules();
		List<PublishedSchedule> publishedSchedules = publishedScheduleService.getAllSchedules();
		for (Schedule schedule : schedules) {
			for (PublishedSchedule publishedSchedule : publishedSchedules) {
				if (publishedSchedule.getPublisehedScheduleID().equals(schedule.getScheduleID())) {
					publishedSchedule.setClassroom(schedule.getClassroom());
					publishedSchedule.setLecturer(schedule.getLecturer());
					publishedSchedule.setSection(schedule.getSection());
					publishedSchedule.setTimeSlot(schedule.getTimeSlot());
					publishedSchedule.setFacultyTimetable(schedule.getFacultyTimetable());
					break; // Exit inner loop once a match is found
				}
			}
		}
		boolean isUpdated = publishedScheduleService.updatePublishedSchedule(publishedSchedules);
		if (isUpdated) {
			redirectAttributes.addFlashAttribute("successMessage", "Timetable have been published successfully!");
			
			 List<String> matricNos = studentService.findAllMatricNos(); // Get all student matric numbers
		        for (String matricNo : matricNos) {
		            // Create notification for each student
		            Notification notification = new Notification();
		            notification.setStudent(new Student(matricNo)); // Set the student reference based on matricNo
		            notification.setTitle("Timetable Update");
		            notification.setMessage("New timetable has been published");
		            notification.setRead(false); 
		            notification.setCreatedAt(LocalDateTime.now());

		            // Save notification via the notification service
		            notificationService.createNotification(notification);
		            System.out.println("Notification sent to student: " + matricNo + " - " + notification.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to publish timetable!");
		}
		return "redirect:/faculty/timetable/management";

	}
    
    @RequestMapping("/view")
    public String showPublishedTimetable(@RequestParam("timetableID") String timetableID,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session,
                                         Model model) {
        Staff staff = (Staff) session.getAttribute("user");

        // Fetch all schedules for the timetable
        List<PublishedSchedule> publishedSchedules = publishedScheduleService.getSchedulesByYear(timetableID);

        // Merge schedules with the same sectionID and day
        Map<String, PublishedSchedule> mergedScheduleMap = new LinkedHashMap<>();
        for (PublishedSchedule schedule : publishedSchedules) {
            String sectionID = schedule.getSection().getSectionID();
            String day = schedule.getTimeSlot().getDay();
            String key = sectionID + "_" + day;
            if (!mergedScheduleMap.containsKey(key)) {
                mergedScheduleMap.put(key, schedule);
            } else {
                PublishedSchedule existing = mergedScheduleMap.get(key);
                if (schedule.getTimeSlot().getStartTime() < existing.getTimeSlot().getStartTime()) {
                    existing.getTimeSlot().setStartTime(schedule.getTimeSlot().getStartTime());
                }
                if (schedule.getTimeSlot().getEndTime() > existing.getTimeSlot().getEndTime()) {
                    existing.getTimeSlot().setEndTime(schedule.getTimeSlot().getEndTime());
                }
            }
        }

        // Convert the merged map to a list
        List<PublishedSchedule> mergedSchedules = new ArrayList<>(mergedScheduleMap.values());

        // Calculate total pages based on merged schedules
        int totalSchedules = mergedSchedules.size();
        int totalPages = (int) Math.ceil((double) totalSchedules / pageSize);
        System.out.println("Total: " + totalSchedules );
        System.out.println("Page: " + totalPages );
        // Apply pagination to the merged schedules
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalSchedules);
        List<PublishedSchedule> paginatedMergedSchedules = mergedSchedules.subList(startIndex, endIndex);

        // Add data to the model
        model.addAttribute("staff", staff);
        model.addAttribute("schedules", paginatedMergedSchedules);
        model.addAttribute("timetable", facultyTimetableService.getFacultyTimetableByID(timetableID));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);

        return "facultyTimetable/publishedTimetable";
    }

    @RequestMapping("/student/view")
    public String showStudentPublishedTimetable(@RequestParam("year") String year,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            HttpSession session,
            Model model) {
        Student student = (Student) session.getAttribute("user");
        model.addAttribute("student",student);
        
        String timetableID = " ";
    	switch(year) {
    		case "1": timetableID = "Y1/S1";
    				break;
    		case "2": timetableID = "Y2/S1";
					break;
    		case "3": timetableID = "Y3/S1";
					break;
    		case "4": timetableID = "Y4/S1";
					break;
    	}
        // Fetch all schedules for the timetable
        List<PublishedSchedule> publishedSchedules = publishedScheduleService.getSchedulesByYear(timetableID);

        // Merge schedules with the same sectionID and day
        Map<String, PublishedSchedule> mergedScheduleMap = new LinkedHashMap<>();
        for (PublishedSchedule schedule : publishedSchedules) {
            String sectionID = schedule.getSection().getSectionID();
            String day = schedule.getTimeSlot().getDay();
            String key = sectionID + "_" + day;
            if (!mergedScheduleMap.containsKey(key)) {
                mergedScheduleMap.put(key, schedule);
            } else {
                PublishedSchedule existing = mergedScheduleMap.get(key);
                if (schedule.getTimeSlot().getStartTime() < existing.getTimeSlot().getStartTime()) {
                    existing.getTimeSlot().setStartTime(schedule.getTimeSlot().getStartTime());
                }
                if (schedule.getTimeSlot().getEndTime() > existing.getTimeSlot().getEndTime()) {
                    existing.getTimeSlot().setEndTime(schedule.getTimeSlot().getEndTime());
                }
            }
        }

        // Convert the merged map to a list
        List<PublishedSchedule> mergedSchedules = new ArrayList<>(mergedScheduleMap.values());

        // Calculate total pages based on merged schedules
        int totalSchedules = mergedSchedules.size();
        int totalPages = (int) Math.ceil((double) totalSchedules / pageSize);
        System.out.println("Total: " + totalSchedules );
        System.out.println("Page: " + totalPages );
        // Apply pagination to the merged schedules
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalSchedules);
        List<PublishedSchedule> paginatedMergedSchedules = mergedSchedules.subList(startIndex, endIndex);

        // Add data to the model
        model.addAttribute("schedules", paginatedMergedSchedules);
        model.addAttribute("timetable", facultyTimetableService.getFacultyTimetableByID(timetableID));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);

    
        return "facultyTimetable/studentPublishedTimetable";
    }
}
