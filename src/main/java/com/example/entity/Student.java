package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "matric_no", nullable = false)
	private String matricNo;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "program", nullable = false)
	private String program;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "semester", nullable = false)
	private int semester;

	@Column(name = "phone_num", nullable = false)
	private String phoneNum;

	@Column(name = "faculty", nullable = false)
	private String faculty;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<StudentTimetable> studentTimetables;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Notification> notifications;

	public Student() {
	}
	
	public Student(String matricNo) {
		this.matricNo = matricNo;
	}
	
	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public List<StudentTimetable> getStudentTimetables() {
		return studentTimetables;
	}

	public void setStudentTimetables(List<StudentTimetable> studentTimetables) {
		this.studentTimetables = studentTimetables;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

}
