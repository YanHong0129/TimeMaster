package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

	@Id
    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;  // Unique identifier for the course
    
    @Column(name = "course_name", nullable = false)
    private String courseName;  // The name of the course
    
    @Column(name = "year", nullable = false)
    private int year;           // The year in which the course is offered
    
    @Column(name = "course_credit", nullable = false)
    private int courseCredit;   // The number of credits for the course
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;  // List of sections associated with the course
    
    @Transient // Mark this as not persisted in the database
    private int sectionCount;

    // Getter and Setter Methods
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }
    
    public int getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(int sectionCount) {
        this.sectionCount = sectionCount;
    }

}
