package com.example.repository;

import com.example.entity.Student;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    
    public Student findByMatricNo(String matricNo) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, matricNo);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean update(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
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
    
    // Retrieve all student matric numbers
    public List<String> findAllMatricNos() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT s.matricNo FROM Student s";
            return session.createQuery(hql, String.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list in case of exception
        }
    }


}