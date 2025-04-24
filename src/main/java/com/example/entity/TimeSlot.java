package com.example.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "time_slot")
public class TimeSlot {

    @Id
    @Column(name = "time_slot_id", nullable = false)
    private String timeSlotID;  // e.g., "Y3/S1_SUN_8.00-8.50"
    
    @Column(name = "day", nullable = false)
    private String day;
    
    @Column(name = "time", nullable = false)
    private String time;
    
    @Column(name = "start_time", nullable = false)
    private double startTime;
    
    @Column(name = "end_time", nullable = false)
    private double endTime;

    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
    
    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    private List<StudentTimetable> studentTimetables;
      
    // Getter and setter methods
    public String getTimeSlotID() {
        return timeSlotID;
    }

    public void setTimeSlotID(String timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    public List<StudentTimetable> getStudentTimetables() {
        return studentTimetables;
    }

    public void setStudentTimetables(List<StudentTimetable> studentTimetables) {
        this.studentTimetables = studentTimetables;
    }
    
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

}

