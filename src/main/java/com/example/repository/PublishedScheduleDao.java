package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Course;
import com.example.entity.PublishedSchedule;
import com.example.entity.Schedule;


@Repository
public class PublishedScheduleDao {
	private final SessionFactory sessionFactory;

    @Autowired
    public PublishedScheduleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public int getScheduleCount() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("select count(*) from PublishedSchedule where timeSlot.timeSlotID = :timeSlotID", Long.class)
                                .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

    public boolean update(List<PublishedSchedule> publishedSchedules) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Update each PublishedSchedule in the list
            for (PublishedSchedule schedule : publishedSchedules) {
                session.update(schedule);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    
    public List<PublishedSchedule>findAllSchedules() {
        try (Session session = sessionFactory.openSession()) { System.out.println("Haha");
            return session.createQuery(
            				"FROM PublishedSchedule", PublishedSchedule.class)
            	            .list();
           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    
    public List<PublishedSchedule>findSchedulesByYear(String facultyTimetableID) {
        try (Session session = sessionFactory.openSession()) {
        return session.createQuery(
                "FROM PublishedSchedule ps " +
                "WHERE ps.facultyTimetable.facultyTimetableID = :facultyTimetableID " +
                "AND ps.section IS NOT NULL " +
                "ORDER BY ps.section.course.courseCode, ps.section.sectionID", PublishedSchedule.class)
                .setParameter("facultyTimetableID", facultyTimetableID)
                .list();
           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
    
    public int getSchdeuleCount(String facultyTimetableID) {
        try (Session session = sessionFactory.openSession()) {
        	Long count = session.createQuery(
                    "SELECT COUNT(ps) FROM PublishedSchedule ps " +
                    "WHERE ps.facultyTimetable.facultyTimetableID = :facultyTimetableID " +
                    "AND ps.section IS NOT NULL", Long.class)
                    .setParameter("facultyTimetableID", facultyTimetableID)
                    .getSingleResult();
        	 return count != null ? count.intValue() : 0;
        }
    }
    
//    public List<PublishedSchedule> findSchedulesByYear(String facultyTimetableID, int page, int pageSize) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery(
//                    "FROM PublishedSchedule ps " +
//                    "WHERE ps.facultyTimetable.facultyTimetableID = :facultyTimetableID " +
//                    "AND ps.section IS NOT NULL " +
//                    "ORDER BY ps.section.course.courseCode, ps.section.sectionID", PublishedSchedule.class)
//                    .setParameter("facultyTimetableID", facultyTimetableID)
//                    .setFirstResult((page - 1) * pageSize) // Offset
//                    .setMaxResults(pageSize) // Limit
//                    .list();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
