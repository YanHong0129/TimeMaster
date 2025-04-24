package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entity.Login;
import com.example.service.LoginService;
import java.util.Collections;




@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String expectedRole = "ROLE_"+authentication.getDetails().toString(); // This is the role passed from the form
        
        // Load user details (Staff or Student)
        Login userDetails = loginService.getLoginByUsername(username);
        System.out.println("User: "+username);
        System.out.println("Password: "+password);      
        System.out.println("Expected Role: " + expectedRole);
        System.out.println("User Role: " + userDetails.getRole());

        // Validate credentials
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Check if the role matches the expected role
            if (!userDetails.getRole().equalsIgnoreCase(expectedRole)) {
                throw new BadCredentialsException("You are not authorized to log in with this role.");
            }
            System.out.println("User Role: " + userDetails.getRole());

            return new UsernamePasswordAuthenticationToken(userDetails, password,
                    Collections.singleton(new SimpleGrantedAuthority(userDetails.getRole())));
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}