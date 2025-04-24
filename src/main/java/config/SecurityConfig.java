package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler; // Inject the custom handler
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * Configures authentication with JDBC and custom queries for retrieving users and authorities.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT username, password, enabled FROM login WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username, authority FROM authority WHERE username = ?")  // Ensure correct role is retrieved
            .passwordEncoder(passwordEncoder());
//    	  auth.authenticationProvider(customAuthenticationProvider);
    }

    /**
     * Configures HTTP security, including authorization rules and form login.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
	        .authorizeRequests()	        
	        .antMatchers("/login","/student/register/**","/staff/register/**","/css/**","/images/**").permitAll()            
	        .antMatchers("/faculty/timetable/student/view").hasRole("STUDENT")
            .antMatchers("/staff/**", "/classroom/**", "/lecturer/**", "/course/**", "/faculty/timetable/**", "/notification/staff").hasRole("STAFF")
            .antMatchers("/student/**", "/personal/**", "/notification/student").hasRole("STUDENT")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()
            .loginPage("/login") // Use a common login page for both Staff and Student
            .loginProcessingUrl("/processLogin") // Common processing URL
            .successHandler(customAuthenticationSuccessHandler) // Custom handler for success
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll()
	        .and()
//	        .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .logout()
	        .logoutUrl("/logout")
	        .logoutSuccessUrl("/login")
	        .invalidateHttpSession(true) // Invalidate the session
	        .deleteCookies("JSESSIONID") // Remove the session cookie
	        .permitAll();
    }

    /**
     * Provides a password encoder bean for encrypting and verifying passwords.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
