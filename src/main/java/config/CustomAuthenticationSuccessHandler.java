package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.service.StaffService;
import com.example.service.StudentService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private StaffService staffService;   // Service to load staff details
    @Autowired
    private StudentService studentService; // Service to load student details

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();  // Get the username
        System.out.println("Authenticated user: " + username);

        // Determine user type by authority or username format
        Object userDetails;
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STAFF"))) {
            userDetails = staffService.getStaffByStaffID(username);  // Fetch staff details
            System.out.println("Staff logged in: " + userDetails);
            response.sendRedirect("/TimeMasterProject/staff/home");
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
            userDetails = studentService.getStudentByMatricNo(username);  // Fetch student details
            System.out.println("Student logged in: " + userDetails);
            response.sendRedirect("/TimeMasterProject/student/home");
        } else {
            throw new IllegalStateException("Unknown role for user: " + username);
        }

        // Store user details in session
        request.getSession().setAttribute("user", userDetails);
    }
}
