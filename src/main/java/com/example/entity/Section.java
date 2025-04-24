package com.example.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @Column(name = "section_id")
    private String sectionID;  // Unique identifier for the section (CouseseCode_Section Number)
    
    @ManyToOne
    @JoinColumn(name = "course_code", nullable = false)
    private Course course;   // Reference to the associated course
    
    @Column(name = "section_name", nullable = false)
    private String sectionName;  // Name or identifier for the section (01)
    
    @Column(name = "program", nullable = false)
    @JsonIgnore
    private String program;      // Program for this section
    
    @Column(name = "capacity", nullable = false)
    @JsonIgnore
    private int capacity;        // Maximum capacity for the section
    
    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY) // One-to-One mapping with Schedule
    private List<Schedule> schedule;
    
    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY) // One-to-One mapping with Schedule
    private List<PublishedSchedule> publishedSchedule;

    // Getter and Setter Methods
    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSectionName() {
        return sectionName;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}


    
}
