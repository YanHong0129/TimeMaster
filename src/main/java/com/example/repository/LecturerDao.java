package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Lecturer;

@Repository
public class LecturerDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public LecturerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Lecturer> getAllLecturers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Lecturer", Lecturer.class)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Lecturer findByLecturerID(String lecturerID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Lecturer.class, lecturerID);
        }
    }
    
    public List<Lecturer> findAvailableLecturers(String timeSlotID) {
        try (Session session = sessionFactory.openSession()) {
//        	"SELECT s " +
//            	    "FROM Section s " +
//            	    "JOIN s.course c " +
//            	    "WHERE c.courseCode = :courseCode " +
//            	    "AND s.sectionID NOT IN (SELECT sc.section.sectionID FROM Schedule sc WHERE sc.section.sectionID IS NOT NULL)";

            // HQL query to find lecturers not assigned to a schedule with the given timeSlotID
        	String hql = "SELECT l " +
                    "FROM Lecturer l " +
                    "LEFT JOIN Schedule s ON l.lecturerID = s.lecturer.lecturerID AND s.timeSlot.timeSlotID = :timeSlotID " +
                    "WHERE s.lecturer.lecturerID IS NULL";


            List<Lecturer> lecturers = session.createQuery(hql, Lecturer.class)
                          .setParameter("timeSlotID", timeSlotID)
                          .list();
            if (lecturers.isEmpty()) {
                System.out.println("No unassigned lecturers found for this time " + timeSlotID); // Log if no sections found
            } else {
                System.out.println("Found unassigned lecturers: " + lecturers.size());
            }
            return lecturers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Lecturer> searchLecturer(String keyword) {
	    try (Session session = sessionFactory.openSession()) {
	        String query = "FROM Lecturer l WHERE " +
	                       "l.name LIKE :keyword OR " +
	                       "l.email LIKE :keyword";
	        return session.createQuery(query, Lecturer.class)
	                      .setParameter("keyword", "%" + keyword + "%")
	                      .list();
	    }
	}
	
	public void save(Lecturer lecturer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(lecturer);
            session.getTransaction().commit();
        }
    }
	
	public void update(Lecturer lecturer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(lecturer);
            session.getTransaction().commit();
        }
    }
	
	public void deleteById(String lecturerId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lecturer lecturer = session.get(Lecturer.class, lecturerId);
            if (lecturer != null) {
                session.delete(lecturer);
            }
            session.getTransaction().commit();
        }
    }
	
	// Check if a lecturer exists by ID
    public boolean existsById(String lecturerID) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(l) FROM Lecturer l WHERE l.lecturerID = :lecturerID";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("lecturerID", lecturerID)
                                .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
