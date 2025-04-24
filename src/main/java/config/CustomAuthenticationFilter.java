package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	  public CustomAuthenticationFilter() {
	        super();
	        // Use the default login URL, or set a custom one
	        setFilterProcessesUrl("/processLogin");
	    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String role = request.getParameter("role"); // Extract role from the form


        // Validate that the role is either STAFF or STUDENT before proceeding
        if (role == null || (!role.equals("STAFF") && !role.equals("STUDENT"))) {
            throw new BadCredentialsException("Invalid role");
        }
        System.out.println("Username: " + username); // Add more debug information here
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(role); // Attach role as details
        return this.getAuthenticationManager().authenticate(authRequest);
    }
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//	    String username = obtainUsername(request);
//	    String password = obtainPassword(request);
//
//	    System.out.println("Attempting authentication for: " + username);
//
//	    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//
//	    // Log the authentication request to check the username and password
//	    System.out.println("Username: " + username + " Password: " + password);
//
//	    // Proceed with authentication using the manager
//	    Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
//
//	    System.out.println("Authentication result: " + authentication);
//
//	    return authentication;
//	}

}

