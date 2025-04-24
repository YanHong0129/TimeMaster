package com.example.entity;


import javax.persistence.*;


@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @Column(name = "classroom_id", nullable = false)
    private String classroomID;  // Unique identifier for the classroom
    
    @Column(name = "name", nullable = false)
    private String classroomName;  // The full name of the classroom
    
    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;   // Abbreviation (e.g., "CS101")
    
    @Column(name = "block", nullable = false)
    private String block;          // The block where the classroom is located (e.g., "A", "B")
    
    @Column(name = "floor", nullable = false)
    private String floor;          // The floor where the classroom is located (e.g., "1st Floor")
    
    @Column(name = "capacity", nullable = false)
    private int capacity;          // The maximum capacity of students that the classroom can hold
    
    @Column(name = "faculty", nullable = false)
    private String faculty;        // The faculty associated with the classroom (e.g., "Engineering")
    
    @Column(name = "type", nullable = false)
    private String type;           // The type of classroom (e.g., "Lecture Hall", "Lab")

	public String getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(String classroomID) {
		this.classroomID = classroomID;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
