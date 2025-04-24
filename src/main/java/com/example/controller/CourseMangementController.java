package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Course;
import com.example.entity.Section;
import com.example.entity.Staff;
import com.example.service.CourseService;
import com.example.service.SectionService;

@Controller
@RequestMapping("/course")
public class CourseMangementController {
	@Autowired
	CourseService courseService;
	@Autowired
	SectionService sectionService;
	
    @RequestMapping("/management")
    public String showCourseManagementPage(HttpSession session, Model model) {
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
    	List<Course> courses = courseService.getCoursesWithSectionCount();
    	model.addAttribute("courses", courses);
        return "course/courseManagement";
    }
    
    @GetMapping("/search")
    @ResponseBody
    public List<Course> searchCourses(@RequestParam String keyword) {
        return courseService.searchCourses(keyword);
    }
    @PostMapping("/add")
   	public String addCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
       	try {
       		courseService.addCourse(course);
               redirectAttributes.addFlashAttribute("success", "Course added successfully!");
               return "redirect:/course/management";
           } catch (Exception e) {
               // Handle duplicate primary key error
               redirectAttributes.addFlashAttribute("error", "Course with the same code already exists. Please use a different code.");
               return "redirect:/course/management";
           }
   	}
       @PostMapping("/edit")
   	public String editCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
       	try {
   	        courseService.updateCourse(course);
   	        redirectAttributes.addFlashAttribute("success", "Course updated successfully!");
   	    } catch (Exception ex) {
   	        redirectAttributes.addFlashAttribute("error", "An error occurred while updating the course. Please try again.");
   	    }
   	    return "redirect:/course/management"; // Redirect to the course management page
   	}
       @PostMapping("/delete")
       public String deleteCourse(@RequestParam String courseCode) {
           courseService.deleteCourse(courseCode);
           return "redirect:/course/management"; // Redirect to the lecturer management page
       }
    
    
    @GetMapping(value = "/getSectionsByCourse", produces = "application/json")
    @ResponseBody
    public List<Section> getSectionsByCourse(@RequestParam String courseCode) {
        System.out.println("Course: " + courseCode);
        List<Section> availableSections = sectionService.getAvailableSections(courseCode);
        System.out.println("Sections: " + availableSections);
        return availableSections;
    }

    
    @GetMapping("/sections")
    public String getSectionsByCourse(@RequestParam String courseCode,
    		HttpSession session,
    		Model model) {
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
    	List<Section> sections = sectionService.getSectionsByCourseCode(courseCode);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("sections", sections);
        return "course/sectionManagement"; // View name for section management
    }
//  Delete section
    @PostMapping("/section/delete")
    @ResponseBody
    public String deleteSection(@RequestParam String sectionID) {
        boolean deleted = sectionService.deleteSection(sectionID);
        return deleted ? "Section deleted successfully" : "Failed to delete section";
    }
    
 // Edit section
    @PostMapping("/section/edit")
    @ResponseBody
    public String editSection(@RequestParam String sectionID,
                              @RequestParam String sectionName,
                              @RequestParam String program,
                              @RequestParam int capacity) {
        Section section = sectionService.getSectionByID(sectionID);
        if (section == null) {
            return "Failed to update section: Section not found.";
        }

        // Check for duplicate section name
        boolean isDuplicate = sectionService.existsBySectionNameAndProgram(sectionName, program);
        if (isDuplicate && !section.getSectionName().equals(sectionName)) {
            return "Failed to update section: Section name already exists in the same program.";
        }

        // Update section details
        section.setSectionName(sectionName);
        section.setProgram(program);
        section.setCapacity(capacity);
        sectionService.updateSection(section);
        return "Section updated successfully";
    }
    
    @PostMapping("/section/add")
    @ResponseBody
    public Map<String, Object> addSection(
        @RequestParam String courseCode,
        @RequestParam String sectionName,
        @RequestParam String program,
        @RequestParam int capacity
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Section section = new Section();
            section.setSectionName(sectionName);
            section.setProgram(program);
            section.setCapacity(capacity);
            section.setCourse(courseService.getCourseByCourseCode(courseCode)); // Set the course

            // Save the section with the generated sectionID
            sectionService.saveSection(courseCode, section);

            response.put("success", true);
            response.put("message", "Section added successfully!");
        } catch (ConstraintViolationException e) {
            response.put("success", false);
            response.put("message", "Section with the same ID already exists.");
        }catch (Exception e) {
            response.put("success", false);
            response.put("message", "Section with the same ID already exists.");
        }
        return response;
    }

    
    
}

