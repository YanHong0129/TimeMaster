package com.example.entity;

public class SectionDTO2 {
	private String sectionID;
    private String sectionName;
    private String program;
    private Integer capacity;

    // Getters and Setters
    
    public String getSectionName() {
        return sectionName;
    }
    public String getSectionID() {
		return sectionID;
	}
	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}
	public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}

