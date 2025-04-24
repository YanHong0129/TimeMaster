package com.example.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.entity.FacultyTimetable;

@Repository
public class FacultyTimetableDao {
	private final SessionFactory sessionFactory;

    @Autowired
    public FacultyTimetableDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public FacultyTimetable findByFacultyTimetableID(String facultyTimetableID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(FacultyTimetable.class, facultyTimetableID);
        }
    }
    
}
