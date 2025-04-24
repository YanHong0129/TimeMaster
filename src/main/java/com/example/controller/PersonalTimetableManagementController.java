package com.example.controller;

import com.example.entity.Student;
import com.example.entity.StudentTimetable;
import com.example.entity.StudentTimetableId;
import com.example.entity.TimeSlot;
import com.example.service.StudentTimetableService;
import com.example.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/personal/timetable/")
public class PersonalTimetableManagementController {

	@Autowired
	StudentTimetableService studentTimetableService;
	@Autowired
	TimeSlotService timeSlotService;

	// Display timetable management page with data
	@GetMapping("/")
	public String showTimetableManagementPage(HttpSession session, Model model) {
		Student student = (Student) session.getAttribute("user");
		model.addAttribute("student", student);
		if (student == null) {
			return "redirect:/login"; // Redirect to login if not authenticated
		}

		// Check if timetable is empty and initialize if needed
		List<StudentTimetable> existingTimetable = studentTimetableService.findByStudentMatricNo(student.getMatricNo());
		if (existingTimetable.isEmpty()) {
			studentTimetableService.initializeStudentTimetable(student);
		}

		List<String> daysOfWeek = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY");
		model.addAttribute("daysOfWeek", daysOfWeek);

		List<TimeSlot> timeSlots = timeSlotService.getTimeSlots();
		model.addAttribute("timeSlots", timeSlots);

		List<StudentTimetable> timetable = studentTimetableService.findByStudentMatricNo(student.getMatricNo());
		System.out.println(timetable.size());
		System.out
				.println(timetable.get(28).getCourse() + timetable.get(28).getSection() + timetable.get(28).getVenue());
		// Load timetable data
		model.addAttribute("timetable", timetable);
		return "personalTimetable/timetableManagement";
	}

	@GetMapping("/add")
	public String showAddFormWithTimeSlot(@RequestParam("timeSlotId") String timeSlotId, Model model,
			HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		if (student == null) {
			return "redirect:/login"; // Redirect to login if user is not logged in
		}
		model.addAttribute("timeSlotId", timeSlotId); // Pass the timeSlotId to the form
		model.addAttribute("student", student);
		StudentTimetableId id = new StudentTimetableId(student.getMatricNo(), timeSlotId);
		StudentTimetable timetable = studentTimetableService.getById(id);
		model.addAttribute("timetable", timetable);
		return "personalTimetable/addForm";
	}

	@PostMapping("/add")
	public String addTimetable(@RequestParam("timeSlotId") String timeSlotId, @RequestParam("course") String course,
	        @RequestParam("section") String section, @RequestParam("venue") String venue,
	        @RequestParam(value = "addNext", required = false) String addNext, HttpSession session,
	        RedirectAttributes redirectAttributes) {
	    System.out.println("Received timeSlotId: " + timeSlotId);
	    System.out.println("Received course: " + course);
	    System.out.println("Received section: " + section);
	    System.out.println("Received venue: " + venue);
	    System.out.println("Add next slot: " + addNext);

	    Student student = (Student) session.getAttribute("user");
	    if (student == null) {
	        return "redirect:/login"; // Redirect to login if no user is logged in
	    }

	    try {
	        // Get the current time slot
	        TimeSlot timeSlot = timeSlotService.getTimeSlotByID(timeSlotId);
	        
	     // Check if the current timeslot is "12.00-12.50"
	        if ("12.00-12.50".equals(timeSlot.getTime())) {
	            // If the timeslot is 12.00-12.50, do not allow adding the next timeslot
	            if ("true".equals(addNext)) {
	                redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot not available.");
	                return "redirect:/personal/timetable/";
	            }
	        }

	        // Create a new StudentTimetable object for the current time slot
	        StudentTimetableId id = new StudentTimetableId(student.getMatricNo(), timeSlotId);
	        StudentTimetable timetable = new StudentTimetable(id, student, timeSlot, course, section, venue);
	        studentTimetableService.saveStudentTimetable(timetable);

	        // If the checkbox is selected, also add the next time slot
	        if ("true".equals(addNext)) {
	            TimeSlot nextSlot = timeSlotService.getNextTimeSlot(timeSlotId);
	            if (nextSlot != null) {
	                String nextSlotId = nextSlot.getTimeSlotID();
	                StudentTimetableId nextId = new StudentTimetableId(student.getMatricNo(), nextSlotId);
	                StudentTimetable nextTimetable = new StudentTimetable(nextId, student, nextSlot, course, section, venue);

	                // Save next timetable only if it's valid
	                studentTimetableService.saveStudentTimetable(nextTimetable);
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "Next timeslot not available.");
	                return "redirect:/personal/timetable/";
	            }
	        }

	        redirectAttributes.addFlashAttribute("successMessage", "Timetable added successfully!");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to add timetable. Please try again.");
	    }

	    return "redirect:/personal/timetable/";
	}


	@PostMapping("/delete")
	public String deleteTimetable(@RequestParam("timeSlotId") String timeSlotId,
	        @RequestParam(value = "addNext", required = false) String addNext, HttpSession session,
	        RedirectAttributes redirectAttributes) {
	    // Check if the user is logged in
	    Student student = (Student) session.getAttribute("user");
	    if (student == null) {
	        return "redirect:/login"; // Redirect to login if no user is logged in
	    }

	    try {
	        // Get the current time slot
	        TimeSlot timeSlot = timeSlotService.getTimeSlotByID(timeSlotId);
	        // Get the next time slot if available
	        TimeSlot nextSlot = timeSlotService.getNextTimeSlot(timeSlotId);
	        String nextSlotId = nextSlot != null ? nextSlot.getTimeSlotID() : null;

	        System.out.println("Student MatricNo: " + student.getMatricNo() + " TimeSlotId: " + timeSlotId);

	        // Clear the current timetable entry by setting course, section, and venue to empty strings
	        StudentTimetableId id = new StudentTimetableId(student.getMatricNo(), timeSlotId);
	        StudentTimetable timetable = new StudentTimetable(id, student, timeSlot, "", "", "");
	        studentTimetableService.saveStudentTimetable(timetable);
	        // Check if the current timetable entry is already empty (course, section, and venue are empty)
//	        if (timetable != null && timetable.getCourse().isEmpty() && timetable.getSection().isEmpty() && timetable.getVenue().isEmpty()) {
//	            // If the timetable is already empty, add an error message
//	            redirectAttributes.addFlashAttribute("errorMessage", "The timeslot is already empty and cannot be deleted.");
//	            return "redirect:/personal/timetable/";
//	        }

	        // If addNext is selected, also clear the next time slot if it exists
	        if ("true".equals(addNext) && nextSlotId != null) {
	            StudentTimetableId nextId = new StudentTimetableId(student.getMatricNo(), nextSlotId);
	            StudentTimetable nextTimetable = new StudentTimetable(nextId, student, nextSlot, "", "", "");
	            studentTimetableService.saveStudentTimetable(nextTimetable);
	        }

	        // Add success message
	        redirectAttributes.addFlashAttribute("successMessage", "Timeslot(s) cleared successfully!");
	    } catch (Exception e) {
	        // Add error message in case of failure
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to clear timeslot(s). Please try again.");
	    }

	    // Redirect to the timetable page
	    return "redirect:/personal/timetable/";
	}


	@PostMapping("/clear")
	public String clearTimetable(@RequestParam("matricNo") String matricNo, RedirectAttributes redirectAttributes) {
		try {
			studentTimetableService.clearAllEntries(matricNo);
			redirectAttributes.addFlashAttribute("successMessage", "Timetable cleared successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to clear timetable.");
		}
		return "redirect:/personal/timetable/";
	}

}
