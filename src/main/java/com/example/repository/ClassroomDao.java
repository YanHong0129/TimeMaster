package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Classroom;
import com.example.entity.Course;

@Repository
public class ClassroomDao {
	private final SessionFactory sessionFactory;
	
	@Autowired
    public ClassroomDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public Classroom findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Classroom.class, id);
        }
    }
	public List<Classroom> getAllClassrooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Classroom", Classroom.class)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public List<Classroom> searchClassrooms(String keyword) {
	    try (Session session = sessionFactory.openSession()) {
	        String query = "FROM Classroom c WHERE " +
	                       "c.classroomName LIKE :keyword OR " +
	                       "c.abbreviation LIKE :keyword OR " +
	                       "c.block LIKE :keyword OR " +
	                       "c.faculty LIKE :keyword";
	        return session.createQuery(query, Classroom.class)
	                      .setParameter("keyword", "%" + keyword + "%")
	                      .list();
	    }
	}
	
	public int getCount() {
	    try (Session session = sessionFactory.openSession()) {
	        String hql = "SELECT COUNT(*) FROM Classroom";
	        Long count = (Long) session.createQuery(hql).uniqueResult();
	        return count.intValue();
	    }
	}

	public void save(Classroom classroom) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(classroom);
            session.getTransaction().commit();
        }
    }
	
	public void update(Classroom classroom) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(classroom);
            session.getTransaction().commit();
        }
    }
	
	public void deleteById(String classroomId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Classroom classroom = session.get(Classroom.class, classroomId);
            if (classroom != null) {
                session.delete(classroom);
            }
            session.getTransaction().commit();
        }
    }
	
	public boolean existsById(String classroomID) {
		try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(l) FROM Classroom c WHERE l.classroomID = :classroomID";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("classroomID", classroomID)
                                .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
}
