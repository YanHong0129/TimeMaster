package com.example.entity;

public class LecturerDTO {
    private String lecturerID;
    private String name;

    public LecturerDTO(String lecturerID, String name) {
        this.lecturerID = lecturerID;
        this.name = name;
    }

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

    // Getters and setters
    
}