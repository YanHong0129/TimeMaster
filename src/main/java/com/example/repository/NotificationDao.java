package com.example.repository;

import com.example.entity.Notification;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public NotificationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Insert a new notification
    public void insert(Notification notification) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(notification);  // Auto-generated notificationId as primary key will be handled by Hibernate
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Set a notification's "isRead" status to true (for a student)
    public void setReadStatus(Long notificationId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // HQL Update Query to mark notification as read by ID
            String hql = "UPDATE Notification n SET n.isRead = true WHERE n.notificationId = :notificationId";
            int updatedRows = session.createQuery(hql)
                    .setParameter("notificationId", notificationId)
                    .executeUpdate();

            if (updatedRows == 0) {
                throw new RuntimeException("Notification not found with ID: " + notificationId);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Retrieve all notifications for a specific student
    public List<Notification> retrieveByStudent(String matricNo) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Notification n WHERE n.student.matricNo = :studentMatricNo";
            return session.createQuery(hql, Notification.class)
                    .setParameter("studentMatricNo", matricNo)
                    .list();
        }
    }

    // Delete a notification by its ID (for a student)
    public void delete(Long notificationId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Notification notification = session.get(Notification.class, notificationId);
            if (notification != null) {
                session.delete(notification);
            } else {
                throw new RuntimeException("Notification not found with ID: " + notificationId);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
