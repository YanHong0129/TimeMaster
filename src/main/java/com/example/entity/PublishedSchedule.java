package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "published_schedule")
public class PublishedSchedule {

    @Id
    @Column(name = "schedule_id", nullable = false)
    private String publisehedScheduleID;  // Unique identifier, e.g., "Y3/S1_SUN_8.00-8.50_1"

    @ManyToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "faculty_timetable_id")
    private FacultyTimetable facultyTimetable;
    
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    
    

    // Getter and setter methods
    public String getPublisehedScheduleID() {
        return publisehedScheduleID;
    }

    public void setPublisehedScheduleID(String publisehedScheduleID) {
        this.publisehedScheduleID = publisehedScheduleID;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public FacultyTimetable getFacultyTimetable() {
		return facultyTimetable;
	}

	public void setFacultyTimetable(FacultyTimetable facultyTimetable) {
		this.facultyTimetable = facultyTimetable;
	}

	public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}

