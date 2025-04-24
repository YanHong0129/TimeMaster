package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
    @Column(name = "staff_id")
    private String staffID;
	
    @Column(name = "name", nullable = false)
    private String name;
	
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "office", nullable = false)
    private String office;
    
    @Column(name = "faculty", nullable = false)
    private String faculty;

    public Staff() {}
    
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
    
    
    public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
   
}
