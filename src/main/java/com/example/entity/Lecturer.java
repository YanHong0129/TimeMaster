package com.example.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lecturer")
public class Lecturer {
	@Id
    @Column(name = "lecturer_id", nullable = false)
    private String lecturerID;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", nullable = false)
    @JsonIgnore
    private String email;
    
    @Column(name = "phone_num", nullable = false)
    @JsonIgnore
    private String phoneNum;  
    
    @Column(name = "office", nullable = false)
    @JsonIgnore
    private String office;
 
    @Column(name = "faculty", nullable = false)
    @JsonIgnore
    private String faculty;
    
    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Schedule> schedules;
    
    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PublishedSchedule> publishedSchedules;

	public Lecturer() {}
    
	public String getLecturerID() {
		return lecturerID;
	}

	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
    

}
