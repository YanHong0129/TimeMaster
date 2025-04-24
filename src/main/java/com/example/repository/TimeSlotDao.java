package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.entity.TimeSlot;

@Repository
public class TimeSlotDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TimeSlotDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<TimeSlot> findAllTimeSlots() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from TimeSlot", TimeSlot.class).list();
        }
    }

    public TimeSlot findByID(String timeSlotID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(TimeSlot.class, timeSlotID);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public TimeSlot findNextTimeSlot(String currentTimeSlotID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT ts " +
                    "FROM TimeSlot ts " +
                    "WHERE ts.day = (" +
                    "    SELECT t.day " +
                    "    FROM TimeSlot t " +
                    "    WHERE t.timeSlotID = :currentTimeSlotID" +
                    ") " +
                    "AND ts.startTime > (" +
                    "    SELECT t.startTime " +
                    "    FROM TimeSlot t " +
                    "    WHERE t.timeSlotID = :currentTimeSlotID" +
                    ") " +
                    "ORDER BY ts.startTime ASC", TimeSlot.class)
                    .setParameter("currentTimeSlotID", currentTimeSlotID)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public TimeSlot findPreviousTimeSlot(String currentTimeSlotID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT ts " +
                    "FROM TimeSlot ts " +
                    "WHERE ts.day = (" +
                    "    SELECT t.day " +
                    "    FROM TimeSlot t " +
                    "    WHERE t.timeSlotID = :currentTimeSlotID" +
                    ") " +
                    "AND ts.startTime < (" +
                    "    SELECT t.startTime " +
                    "    FROM TimeSlot t " +
                    "    WHERE t.timeSlotID = :currentTimeSlotID" +
                    ") " +
                    "ORDER BY ts.startTime DESC", TimeSlot.class)
                    .setParameter("currentTimeSlotID", currentTimeSlotID)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    
}
