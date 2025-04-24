package com.example.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Notification;
import com.example.entity.Student;
import com.example.service.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationManagementController {
	@Autowired
    private NotificationService notificationService;
	
	@RequestMapping("/staff")
    public String staffNotification() {
        return "notification/staffNotification"; 
    }
	
	 @RequestMapping("/student")
	    public String studentNotification(Model model, HttpSession session) {
		 Student student = (Student) session.getAttribute("user");
			model.addAttribute("student", student);
	        List<Notification> notifications = notificationService.retrieveByStudent(student.getMatricNo());
	        model.addAttribute("notifications", notifications);
	        System.out.println("Notification: " + notifications);
	        return "notification/studentNotification"; 
	    }

	    @RequestMapping("/student/read/{notificationId}")
	    public String readNotification(@PathVariable("notificationId") Long notificationId, RedirectAttributes redirectAttributes) {
	        try {
	            notificationService.markAsRead(notificationId);
	            redirectAttributes.addFlashAttribute("successMessage", "Notification marked as read.");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to mark notification as read.");
	        }
	        return "redirect:/notification/student";
	    }

	    @RequestMapping("/student/delete/{notificationId}")
	    public String deleteNotification(@PathVariable("notificationId") Long notificationId, RedirectAttributes redirectAttributes) {
	        try {
	            notificationService.deleteNotification(notificationId);
	            redirectAttributes.addFlashAttribute("successMessage", "Notification deleted successfully.");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete notification.");
	        }
	        return "redirect:/notification/student";
	    }
}