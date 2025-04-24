package com.example.controller;

import javax.servlet.http.HttpSession;  // Import HttpSession
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Import Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.entity.Authority;
import com.example.entity.Login;
import com.example.entity.Staff;
import com.example.entity.Student;
import com.example.service.AuthorityService;
import com.example.service.LoginService;
import com.example.service.StaffService;
import com.example.service.StudentService;

@Controller
@RequestMapping("/")
public class UserManagementController {

    @Autowired
    private StudentService studentService; // Assuming this is for accessing student data, if needed
    @Autowired
    private StaffService staffService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthorityService authorityService;
//     Default redirect to login page
    @RequestMapping("/")
    public String defaultPage() {
        return "redirect:/login"; // Redirect to login
    }
    
    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "role", required = false) String role,
    		@RequestParam(value = "error", required = false) String error) {
        if (role == null) {
            // Default to Staff login page or handle appropriately
            role = "STAFF";
        }
     // Pass role and error flag to the view
        model.addAttribute("role", role);
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", true);
        }
        return "login";  // This will load the login.jsp page
    }

    // Show staff home page with session details
    @RequestMapping("/staff/home")
    public String showStaffHomePage(HttpSession session, Model model) { 
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
        
        return "staff/home";
    }
 
    
    //Show staff sample register page
    @RequestMapping("/staff/register")
    public String showStaffRegister(HttpSession session, Model model) {
        return "staff/register";
    }
    
    //Process staff registration
    @PostMapping("/staff/register/process")
    public String processStaffRegister(@ModelAttribute("staff") Staff staff, 
    		@RequestParam("password") String password,
    		Model model,
    		RedirectAttributes redirectAttributes) {
    	
    	// Encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        // Create and populate Login object
        Login staffLogin = new Login();
        staffLogin.setUsername(staff.getStaffID());
        staffLogin.setPassword(encryptedPassword);
        staffLogin.setRole("ROLE_STAFF");
        staffLogin.setEnabled(true);

        // Create and populate Authority object
        Authority staffAuthority = new Authority();
        staffAuthority.setAuthority("ROLE_STAFF");
        staffAuthority.setUsername(staff.getStaffID());

        // Save staff, login, and authority to the database
        boolean isStaffSaved = staffService.saveStaffInfo(staff);
        boolean isAuthoritySaved = authorityService.saveAuthority(staffAuthority);
        boolean isLoginSaved = loginService.saveLogin(staffLogin);

        if (isStaffSaved && isAuthoritySaved && isLoginSaved) {
            redirectAttributes.addFlashAttribute("successMessage", "Account registered successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
        }
        return "redirect:/login?role=STAFF";
        
    }
    
    @RequestMapping("/student/home")
    public String showStudentHomePage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            System.out.println("Student Name: " + student.getName());
            model.addAttribute("student", student);
        } else {
            System.out.println("No student found in session.");
        }
        return "student/home";
    }

    @RequestMapping("/student/register")
    public String showStudentRegister(HttpSession session, Model model) {
        return "student/register";
    }
    
    @PostMapping("/student/register/process")
    public String processStudentRegister(@ModelAttribute("student") Student student, 
    		@RequestParam("password") String password,
    		Model model,
    		RedirectAttributes redirectAttributes) {
    	
    	// Encrypt password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        // Create and populate Login object
        Login studentLogin = new Login();
        studentLogin.setUsername(student.getMatricNo());
        studentLogin.setPassword(encryptedPassword);
        studentLogin.setRole("ROLE_STUDENT");
        studentLogin.setEnabled(true);

        // Create and populate Authority object
        Authority studentAuthority = new Authority();
        studentAuthority.setAuthority("ROLE_STUDENT");
        studentAuthority.setUsername(student.getMatricNo());

        // Save student, login, and authority to the database
        boolean isStudentSaved = studentService.saveStudentInfo(student);
        boolean isAuthoritySaved = authorityService.saveAuthority(studentAuthority);
        boolean isLoginSaved = loginService.saveLogin(studentLogin);

        if (isStudentSaved && isAuthoritySaved && isLoginSaved) {
            redirectAttributes.addFlashAttribute("successMessage", "Account registered successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
        }
        return "redirect:/login?role=STUDENT";
        
    }
    
    // Show staff profile page
    @RequestMapping("/staff/profile")
    public String showStaffProfile(HttpSession session, Model model) {
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
        return "staff/profile";
    }
    
    @PostMapping("/staff/profile/update")
    public String updateStaffProfile(@RequestParam("email") String email,
                                       @RequestParam("location") String location,
                                       HttpSession session,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            staff.setEmail(email);
            staff.setOffice(location);

            // Update staff information in the database
            boolean isUpdated = staffService.updateStaffInfo(staff);
            if (isUpdated) {
                session.setAttribute("user", staff); // Update session with the latest staff object
                redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile. Please try again.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No staff found in session.");
        }
        return "redirect:/staff/profile";

    }
    
    @GetMapping("/staff/password")
    public String showStaffPasswordForm(HttpSession session,
            Model model) {
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            System.out.println("Staff Name: " + staff.getName());
            model.addAttribute("staff", staff);
        } else {
            System.out.println("No staff found in session.");
        }
    	return "staff/passwordForm";
    }
    
    @PostMapping("/staff/password/update")
    public String changeStaffPassword(@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
    	Staff staff = (Staff) session.getAttribute("user");
        String staffID = staff.getStaffID();
        
        
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPassword);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedNewPass =  passwordEncoder.encode(newPassword);

 
        // Validate the old password
        Login login = loginService.getLoginByUsername(staffID);   
        if (!passwordEncoder.matches(oldPassword, login.getPassword())) {
            System.out.println("Old password is incorrect!");
        	redirectAttributes.addFlashAttribute("errorMessage", "Old password is incorrect!");
        	return "redirect:/staff/password";
        }else {
	        // Validate new password and confirmation match
	        if (!(newPassword.equals(confirmPassword))) {
	            System.out.println("New password and confirmation do not match!");
	        	redirectAttributes.addFlashAttribute("errorMessage", "New password and confirmation do not match!");
	        	return "redirect:/staff/password";
	        }      
	        else {
	            System.out.println("Password have been updated successfully!");
	            login.setPassword(encodedNewPass);
	            loginService.updatePassword(login);	   
	        	redirectAttributes.addFlashAttribute("successMessage", "Password have been updated successfully!");
	        	return "redirect:/staff/profile";
	        }

        }
    }
    
    // Show student profile page
    @RequestMapping("/student/profile")
    public String showStudentProfile(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            System.out.println("Student Name: " + student.getName());
            model.addAttribute("student", student);
        } else {
            System.out.println("No student found in session.");
        }
        return "student/profile";
    }
    
    @PostMapping("/student/profile/update")
    public String updateStudentProfile(@RequestParam("email") String email,
                                       @RequestParam("location") String location,
                                       HttpSession session,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            student.setEmail(email);
            student.setLocation(location);

            // Update student information in the database
            boolean isUpdated = studentService.updateStudentInfo(student);
            if (isUpdated) {
                session.setAttribute("user", student); // Update session with the latest student object
                redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
            } else {
            	redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile. Please try again.");
            }
        } else {
        	redirectAttributes.addFlashAttribute("errorMessage", "No student found in session.");
        }
        return "redirect:/student/profile";

    }
    
    @GetMapping("/student/password")
    public String showStudentPasswordForm(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            System.out.println("Student Name: " + student.getName());
            model.addAttribute("student", student);
        } else {
            System.out.println("No student found in session.");
        }
    	return "student/passwordForm";
    }
    
    @PostMapping("/student/password/update")
    public String changeStudentPassword(@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
    	Student student = (Student) session.getAttribute("user");
        String matricNo = student.getMatricNo();
        
        
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPassword);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedNewPass =  passwordEncoder.encode(newPassword);

 
        // Validate the old password
        Login login = loginService.getLoginByUsername(matricNo);
        if (!passwordEncoder.matches(oldPassword, login.getPassword())) {
            System.out.println("Old password is incorrect!");
        	redirectAttributes.addFlashAttribute("errorMessage", "Old password is incorrect!");
        	return "redirect:/student/password";
        }else {
	        // Validate new password and confirmation match
	        if (!(newPassword.equals(confirmPassword))) {
	            System.out.println("New password and confirmation do not match!");
	        	redirectAttributes.addFlashAttribute("errorMessage", "New password and confirmation do not match!");
	        	return "redirect:/student/password";
	        }      
	        else {
	            System.out.println("Password have been updated successfully!");
	            login.setPassword(encodedNewPass);
	            loginService.updatePassword(login);	   
	        	redirectAttributes.addFlashAttribute("successMessage", "Password have been updated successfully!");
	        	return "redirect:/student/profile";
	        }

        }
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

