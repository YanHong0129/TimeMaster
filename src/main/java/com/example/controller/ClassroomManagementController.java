package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Classroom;
import com.example.entity.Schedule;
import com.example.entity.Staff;
import com.example.entity.TimeSlot;
import com.example.service.ClassroomService;
import com.example.service.ScheduleService;
import com.example.service.TimeSlotService;


@Controller
@RequestMapping("/classroom")
public class ClassroomManagementController {
	
	private final ClassroomService classroomService;
	private final ScheduleService scheduleService;
	private final TimeSlotService timeSlotService;
	
	@Autowired
    public ClassroomManagementController(ClassroomService classroomService,
    		ScheduleService scheduleService,
    		TimeSlotService timeSlotService) {
        this.classroomService = classroomService;
        this.scheduleService = scheduleService;
        this.timeSlotService = timeSlotService;
    }
	
	@GetMapping("/management")
    public String showAllClassrooms(HttpSession session, Model model) {
		
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
        // Retrieve the list of classrooms from the service
        List<Classroom> classrooms = classroomService.getAllClassrooms();
		System.out.println(classrooms.size());
        // Add the list to the model
        model.addAttribute("classrooms", classrooms);

        // Return the name of the JSP file (logical view name)
        return "classroom/classroomManagement";
    }
	
	@GetMapping("/search")
	@ResponseBody
	public List<Classroom> searchClassrooms(@RequestParam("keyword") String keyword) {
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return classroomService.getAllClassrooms(); // Return all classrooms if no keyword
	    }
	    return classroomService.searchClassrooms(keyword.trim()); // Search classrooms by keyword
	}
	
	@PostMapping("/add")
	public String addClassroom(@ModelAttribute Classroom classroom, RedirectAttributes redirectAttributes) {
	    // Get the current number of classrooms in the database
	    int num = classroomService.getClassroomCount()+1;

	    // Retrieve all available time slots
	    List<TimeSlot> timeSlots = timeSlotService.getTimeSlots();

	    try {
	        // Check if the classroom already exists before adding
	        if (classroomService.getClassroomById(classroom.getClassroomID()) != null) {
	            redirectAttributes.addFlashAttribute("error", "Classroom with the same ID already exists.");
	            return "redirect:/classroom/management";
	        }

	        // Proceed to add the classroom
	        classroomService.addClassroom(classroom);		    
	        
	        // Add schedules for each time slot
	        for (TimeSlot timeSlot : timeSlots) {
	            Schedule schedule = new Schedule();
	            String scheduleID = timeSlot.getTimeSlotID() + "_" + num;
	            System.out.println(scheduleID);
	            schedule.setScheduleID(scheduleID); // Assuming Schedule class has this method
	            schedule.setTimeSlot(timeSlot);
	            schedule.setClassroom(classroom);

	            // Save the schedule to the database
	            scheduleService.addSchedule(schedule);
	        }
	        
	        redirectAttributes.addFlashAttribute("success", "Classroom added successfully!");
	    } catch (IllegalArgumentException ex) {
	        redirectAttributes.addFlashAttribute("error", ex.getMessage());
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("error", "An unexpected error occurred.");
	    }
	    return "redirect:/classroom/management";
	}
	@PostMapping("/edit")
	public String editClassroom(@ModelAttribute Classroom classroom, RedirectAttributes redirectAttributes) {
		try {
	        classroomService.updateClassroom(classroom);
	        redirectAttributes.addFlashAttribute("success", "Lecturer updated successfully!");
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("error", "An error occurred while updating the lecturer. Please try again.");
	    }
	    return "redirect:/classroom/management"; // Redirect to the classroom management page
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> deleteClassroom(@RequestParam("id") String classroomId,
			RedirectAttributes redirectAttributes) {
		Classroom classroom = classroomService.getClassroomById(classroomId);
	    // Check if classroom is assigned to any schedule
	    List<Schedule> relatedSchedules = scheduleService.getSchedulesByClassroom(classroom);
	    for (Schedule schedule : relatedSchedules) {
	        if (schedule.getSection() != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                    .body("Error: This classroom is assigned to a schedule and cannot be deleted.");
	        }
	    }
	    try {
	    	scheduleService.deleteSchedule(classroom);
	        classroomService.deleteClassroomById(classroomId);
	        return ResponseEntity.ok("Classroom deleted successfully!");
	    } catch (DataIntegrityViolationException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Cannot delete classroom because it is linked to a schedule.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while deleting the classroom.");
	    }
	}
}
