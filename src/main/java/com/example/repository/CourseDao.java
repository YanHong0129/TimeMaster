package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.entity.Course;


@Repository
public class CourseDao {
	
    private final SessionFactory sessionFactory;

    @Autowired
    public CourseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Course findByCourseCode(String courseCode) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseCode);
        }
    }
    public void save(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        }
    }
    public void update(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        }
    }
    
    
    public List<Course> findAvailableCourses() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Course", Course.class)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int countSectionsByCourseCode(String courseCode) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(s.sectionID) FROM Section s WHERE s.course.courseCode = :courseCode";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("courseCode", courseCode)
                                .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }
    
    public List<Course> searchCourses(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Course c WHERE c.courseName LIKE :keyword OR c.courseCode LIKE :keyword";
            return session.createQuery(hql, Course.class)
                          .setParameter("keyword", "%" + keyword + "%")
                          .list();
        }
    }
    
    public void deleteCourse(String courseCode) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, courseCode);
            if (course != null) {
                session.delete(course);
            }
            session.getTransaction().commit();
        }
    }

	public boolean existsById(String courseCode) {
		try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(l) FROM Course c WHERE c.courseCode = :courseCode";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("courseCode", courseCode)
                                .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
