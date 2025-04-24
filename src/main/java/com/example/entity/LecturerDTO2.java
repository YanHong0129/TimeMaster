package com.example.entity;

public class LecturerDTO2 {
    private String lecturerID;
    private String name;
    private String email;
    private String phoneNum;
    private String office;
    private String faculty;

    // Constructor to map Lecturer to LecturerDTO
    public LecturerDTO2(Lecturer lecturer) {
        this.lecturerID = lecturer.getLecturerID();
        this.name = lecturer.getName();
        this.email = lecturer.getEmail();
        this.phoneNum = lecturer.getPhoneNum();
        this.office = lecturer.getOffice();
        this.faculty = lecturer.getFaculty();
    }

	public String getLecturerID() {
		return lecturerID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getOffice() {
		return office;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

    // Getters and setters (if needed)
    
}
