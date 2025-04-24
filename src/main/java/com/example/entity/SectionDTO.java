package com.example.entity;

public class SectionDTO {
    private String sectionID;
    private String sectionName;

    public SectionDTO(String sectionID, String sectionName) {
        this.sectionID = sectionID;
        this.sectionName = sectionName;
    }

	public String getSectionID() {
		return sectionID;
	}

	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setName(String sectionName) {
		this.sectionName = sectionName;
	}
}
