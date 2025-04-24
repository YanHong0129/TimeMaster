package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Classroom;
import com.example.entity.Schedule;

@Repository
public class ScheduleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ScheduleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Schedule findByID(String scheduleID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Schedule.class, scheduleID);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Schedule> findAllSchedules(){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Schedule", Schedule.class)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Schedule> findSchedulesByClassroom(Classroom classroom) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Schedule WHERE classroom = :classroom", Schedule.class)
                          .setParameter("classroom", classroom)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public List<Schedule> findSchedulesByTimeSlotId(String timeSlotID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Schedule where timeSlot.timeSlotID = :timeSlotID", Schedule.class)
                          .setParameter("timeSlotID", timeSlotID)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Schedule findNextSchedule(String currentScheduleID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT s " +
                    "FROM Schedule s " +
                    "JOIN s.timeSlot ts " +
                    "WHERE ts.day = (" +
                    "    SELECT sc.timeSlot.day " +
                    "    FROM Schedule sc " +
                    "    WHERE sc.scheduleID = :currentScheduleID" +
                    ") " +
                    "AND ts.startTime > (" +
                    "    SELECT sc.timeSlot.startTime " +
                    "    FROM Schedule sc " +
                    "    WHERE sc.scheduleID = :currentScheduleID" +
                    ") " +
                    "AND s.scheduleID LIKE CONCAT('%_', SUBSTRING_INDEX(:currentScheduleID, '_', -1)) " +
                    "ORDER BY ts.startTime ASC", Schedule.class)
                    .setParameter("currentScheduleID", currentScheduleID)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Schedule findPreviousSchedule(String currentScheduleID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT s " +
                    "FROM Schedule s " +
                    "JOIN s.timeSlot ts " +
                    "WHERE ts.day = (" +
                    "    SELECT sc.timeSlot.day " +
                    "    FROM Schedule sc " +
                    "    WHERE sc.scheduleID = :currentScheduleID" +
                    ") " +
                    "AND ts.startTime < (" +
                    "    SELECT sc.timeSlot.startTime " +
                    "    FROM Schedule sc " +
                    "    WHERE sc.scheduleID = :currentScheduleID" +
                    ") " +
                    "AND s.scheduleID LIKE CONCAT('%_', SUBSTRING_INDEX(:currentScheduleID, '_', -1)) " +
                    "ORDER BY ts.startTime DESC", Schedule.class)
                    .setParameter("currentScheduleID", currentScheduleID)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getScheduleCount(String timeSlotID) {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("select count(*) from Schedule where timeSlot.timeSlotID = :timeSlotID", Long.class)
                                .setParameter("timeSlotID", timeSlotID)
                                .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

//    + "order by (case when section.course.courseCode is null then 1 else 0 end), "
//    + "classroom.classroomName, section.course.courseCode", Schedule.class)
    public List<Schedule> findSchedulesWithPagination(String timeSlotID, int offset, int limit) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
//            		 "from Schedule where timeSlot.timeSlotID = :timeSlotID "
//            	     + "order by classroom.classroomName", Schedule.class)
            		"SELECT s FROM Schedule s " +
            	    "LEFT JOIN s.section sec " +
            	    "WHERE s.timeSlot.timeSlotID = :timeSlotID " +
            	    "ORDER BY (CASE WHEN sec IS NULL THEN 1 ELSE 0 END), " +
            	    "s.classroom.classroomName", Schedule.class)
                          .setParameter("timeSlotID", timeSlotID)
                          .setFirstResult(offset)
                          .setMaxResults(limit)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
	public void save(Schedule schedule) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(schedule);
            session.getTransaction().commit();
        }
    }
	
    public boolean update(Schedule schedule) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(schedule);
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
    
    public void delete(Classroom classroom) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Find the Schedule(s) associated with the given Classroom
            List<Schedule> schedules = session.createQuery("FROM Schedule WHERE classroom = :classroom", Schedule.class)
                                               .setParameter("classroom", classroom)
                                               .getResultList();

            // Delete each Schedule
            for (Schedule schedule : schedules) {
                session.delete(schedule);
            }
            transaction.commit();
    
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    

}
