package com.example.entity;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "faculty_timetable")
public class FacultyTimetable {

    @Id
    @Column(name = "faculty_timetable_id", nullable = false)
    private String facultyTimetableID;  // e.g., "Y3/S1" for Year 3, Semester 1

    @Column(name = "year", nullable = false)
    private int year;
    
    @Column(name = "semester", nullable = false)
    private int semester;
    
    @OneToMany(mappedBy = "facultyTimetable", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "facultyTimetable", cascade = CascadeType.ALL)
    private List<PublishedSchedule> publishedSchedules;
    
    // Getter and setter methods
	public String getFacultyTimetableID() {
		return facultyTimetableID;
	}

	public void setFacultyTimetableID(String facultyTimetableID) {
		this.facultyTimetableID = facultyTimetableID;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
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

	public List<PublishedSchedule> getPublishedSchedules() {
		return publishedSchedules;
	}

	public void setPublishedSchedules(List<PublishedSchedule> publishedSchedules) {
		this.publishedSchedules = publishedSchedules;
	}


}