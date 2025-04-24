package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;

    // Assuming role is either 'staff' or 'student'
    @Column(name = "role", nullable = false)
    private String role;
    
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

//    // Staff relationship
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username", referencedColumnName = "staff_id", insertable = false, updatable = false)
//    private Staff staff;
//
//    // Student relationship
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username", referencedColumnName = "matric_no", insertable = false, updatable = false)
//    private Student student;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

//    public Staff getStaff() {
//        return staff;
//    }
//
//    public void setStaff(Staff staff) {
//        this.staff = staff;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
}
