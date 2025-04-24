package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.entity.Lecturer;
import com.example.entity.LecturerDTO2;
import com.example.entity.Staff;
import com.example.service.LecturerService;

@Controller
@RequestMapping("/lecturer")
public class LecturerManagementController {
	
	@Autowired
	LecturerService lecturerService;
	
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
        List<Lecturer> lecturers = lecturerService.getAllLecturers();

        // Add the list to the model
        model.addAttribute("lecturers", lecturers);

        // Return the name of the JSP file (logical view name)
        return "lecturer/lecturerManagement";
    }
	
	@GetMapping("/search")
	@ResponseBody
	public List<LecturerDTO2> searchLecturers(@RequestParam String keyword) {
	    List<Lecturer> lecturers = lecturerService.searchLecturer(keyword);
	    return lecturers.stream().map(LecturerDTO2::new).collect(Collectors.toList());
	}
	
	@PostMapping("/add")
    public String addlecturer(@ModelAttribute Lecturer lecturer, RedirectAttributes redirectAttributes) {
        try {
            lecturerService.addLecturer(lecturer);
            redirectAttributes.addFlashAttribute("success", "Lecturer added successfully!");
            return "redirect:/lecturer/management";
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate primary key error
            redirectAttributes.addFlashAttribute("error", "Lecturer with the same ID already exists. Please use a different ID.");
            return "redirect:/lecturer/management";
        } catch (ConstraintViolationException e) {
            // Handle generic exceptions
            redirectAttributes.addFlashAttribute("error", "Lecturer with the same ID already exists. Please use a different ID.");
            return "redirect:/lecturer/management";
        } catch (PersistenceException e) {
            // Handle generic exceptions
            redirectAttributes.addFlashAttribute("error", "Lecturer with the same ID already exists. Please use a different ID.");
            return "redirect:/lecturer/management";
        } catch (Exception ex) {
            // Handle generic exceptions
            redirectAttributes.addFlashAttribute("error", "Lecturer with the same ID already exists. Please use a different ID.");
            return "redirect:/lecturer/management";
        }
    }
	
	@PostMapping("/edit")
	public String editLecturer(@ModelAttribute Lecturer lecturer, RedirectAttributes redirectAttributes) {
	    try {
	        lecturerService.updateLecturer(lecturer);
	        redirectAttributes.addFlashAttribute("success", "Lecturer updated successfully!");
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("error", "An error occurred while updating the lecturer. Please try again.");
	    }
	    return "redirect:/lecturer/management"; // Redirect to the lecturer management page
	}
	
	@PostMapping("/delete")
	public String deleteLecturer(@RequestParam String lecturerID, RedirectAttributes redirectAttributes) {
	    try {
	        lecturerService.deleteLecturerById(lecturerID);
	        redirectAttributes.addFlashAttribute("success", "Lecturer deleted successfully!");
	    } catch (Exception ex) {
	        redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the lecturer. Please try again.");
	    }
	    return "redirect:/lecturer/management"; // Redirect to the lecturer management page
	}
}
