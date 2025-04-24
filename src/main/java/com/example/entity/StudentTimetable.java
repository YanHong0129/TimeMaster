package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_timetable")
public class StudentTimetable {

	@EmbeddedId  // This is the embedded primary key
    private StudentTimetableId id;  

	// Foreign key relations
    @ManyToOne
    @JoinColumn(name = "matric_no")
    @MapsId("matricNo")  // Refer to the field inside the composite key
    private Student student;  // Foreign key to the student table

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    @MapsId("timeSlotId")  // Refer to the field inside the composite key
    private TimeSlot timeSlot;  // Foreign key to the time_slot table

    @Column(name = "course", nullable = true)
    private String course;  

    @Column(name = "section", nullable = true)
    private String section;  

    @Column(name = "venue", nullable = true)
    private String venue;  
    
    
    
    public StudentTimetable() {};

	public StudentTimetable(StudentTimetableId id, Student student, TimeSlot timeSlot, String course, String section,
			String venue) {
		this.id = new StudentTimetableId(student.getMatricNo(), timeSlot.getTimeSlotID());
		this.course = course;
		this.section = section;
		this.venue = venue;
	}

	// Getters and Setters
    public StudentTimetableId getId() {
        return id;
    }

    public void setId(StudentTimetableId id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
