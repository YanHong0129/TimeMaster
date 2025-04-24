package com.example.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

// Composite primary key 
@Embeddable
public class StudentTimetableId implements Serializable {

    private String matricNo;    // student matric number
    private String timeSlotId;  // time slot ID

    // Default constructor
    public StudentTimetableId() {}

    // Parameterized constructor
    public StudentTimetableId(String matricNo, String timeSlotId) {
        this.matricNo = matricNo;
        this.timeSlotId = timeSlotId;
    }

    // Getters and Setters
    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    // Equals and hashCode methods to ensure correct comparison of composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTimetableId id = (StudentTimetableId) o;
        if (matricNo != null ? !matricNo.equals(id.matricNo) : id.matricNo != null) return false;
        return timeSlotId != null ? timeSlotId.equals(id.timeSlotId) : id.timeSlotId == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricNo, timeSlotId);
    }
}
