package com.example.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Staff;

@Repository
public class StaffDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StaffDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Staff findByStaffID(String staffID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Staff.class, staffID);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(Staff staff) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(staff);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    
    public boolean update(Staff staff) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(staff);
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
}
