package com.example.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Lecturer;
import com.example.entity.Student;
import com.example.entity.StudentTimetable;
import com.example.entity.StudentTimetableId;
import com.example.entity.TimeSlot;

import java.util.List;

@Repository
public class StudentTimetableDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudentTimetableDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<StudentTimetable> findByStudentMatricNo(String matricNo) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM StudentTimetable WHERE id.matricNo = :matricNo";
            Query<StudentTimetable> query = session.createQuery(hql, StudentTimetable.class);
            query.setParameter("matricNo", matricNo);
            return query.list();
        }
    }
    
    public void initializeStudentTimetable(Student student, List<TimeSlot> timeSlots)  {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            for (TimeSlot timeSlot : timeSlots) {
                StudentTimetableId id = new StudentTimetableId(student.getMatricNo(), timeSlot.getTimeSlotID());
                StudentTimetable timetable = new StudentTimetable(id, student, timeSlot, null, null, null);
                session.saveOrUpdate(timetable);
            }

            transaction.commit();
        }
    }
 
    
    public StudentTimetable getById(StudentTimetableId id) {
        Session session = sessionFactory.openSession();
        StudentTimetable timetable = session.get(StudentTimetable.class, id);
        session.close();
        return timetable;
    }

    // Save a new StudentTimetable record
    public void save(StudentTimetable studentTimetable) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            session.saveOrUpdate(studentTimetable);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
	public void update(StudentTimetable studentTimetable) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(studentTimetable);     
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a StudentTimetable record by composite primary key (matricNo and timeSlotId)
	public void delete(String matricNo, String timeSlotId) {
	    Transaction transaction = null;
	    try (Session session = sessionFactory.openSession()) {
	        transaction = session.beginTransaction();

	        // HQL query to update course, section, and venue to null for the specified student and time slot
	        String hql = "UPDATE StudentTimetable st " +
	                     "SET st.course = null, st.section = null, st.venue = null " +
	                     "WHERE st.student.matricNo = :matricNo AND st.timeSlot.timeSlotId = :timeSlotId";

	        Query query = session.createQuery(hql);
	        query.setParameter("matricNo", matricNo);    // Bind matricNo parameter
	        query.setParameter("timeSlotId", timeSlotId); // Bind timeSlotId parameter

	        int rowsUpdated = query.executeUpdate();     // Execute the query
	        transaction.commit();                        // Commit the transaction

	        System.out.println(rowsUpdated + " rows updated."); // Log the update
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback(); // Rollback on exception
	        e.printStackTrace();
	    }
	}


    
    public void clearTimetableEntries(String matricNo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            // HQL query to update course, section, and venue to null for the specified student
            String hql = "UPDATE StudentTimetable st SET st.course = '', st.section = '', st.venue = '' " +
                         "WHERE st.student.matricNo = :matricNo AND st.timeSlot IS NOT NULL";
            
            // Execute the update operation
            int result = session.createQuery(hql)
                                .setParameter("matricNo", matricNo)
                                .executeUpdate();
            
            // Check the result (optional) and commit the transaction
            if (result > 0) {
                System.out.println("Cleared " + result + " timetable entries.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}


