package com.example.service;

import com.example.entity.Student;
import com.example.entity.StudentTimetable;
import com.example.entity.StudentTimetableId;
import com.example.entity.TimeSlot;
import com.example.repository.StudentTimetableDao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentTimetableService {

    @Autowired
    private StudentTimetableDao studentTimetableDao;
    
    @Autowired
    private TimeSlotService timeSlotService;
    
    public List<StudentTimetable> findByStudentMatricNo(String matricNo) {
        return studentTimetableDao.findByStudentMatricNo(matricNo);
    }

    public StudentTimetableService(StudentTimetableDao studentTimetableDao) {
        this.studentTimetableDao = studentTimetableDao;
    }
    
    public void initializeStudentTimetable(Student student) {
        List<TimeSlot> allTimeSlots = timeSlotService.getTimeSlots();
        for (TimeSlot timeSlot : allTimeSlots) {
            StudentTimetableId id = new StudentTimetableId(student.getMatricNo(), timeSlot.getTimeSlotID());
            
                StudentTimetable timetable = new StudentTimetable();
                timetable.setId(id);
                timetable.setStudent(student); // Set the student object
                timetable.setTimeSlot(timeSlot); // Set the time slot object
                timetable.setCourse(""); // Empty course
                timetable.setSection(""); // Empty section
                timetable.setVenue(""); // Empty venue

                studentTimetableDao.save(timetable);
            
        }
    }
    
    public StudentTimetable getById(StudentTimetableId id) {
    		return studentTimetableDao.getById(id);
	}

    // Save a new StudentTimetable
    public void saveStudentTimetable(StudentTimetable studentTimetable) {
        studentTimetableDao.save(studentTimetable);
    }

    // Delete a StudentTimetable by composite primary key
    public void deleteStudentTimetable(String matricNo, String timeSlotId) {
        studentTimetableDao.delete(matricNo, timeSlotId);
    }
    
    public void clearAllEntries(String matricNo) {
    	studentTimetableDao.clearTimetableEntries(matricNo);
    }
    
}