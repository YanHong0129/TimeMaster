package com.example.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.entity.Section;


@Repository
public class SectionDao {
	private final SessionFactory sessionFactory;

    @Autowired
    public SectionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @PersistenceContext
    private EntityManager entityManager; //to count the sections
    
    public Section findBySectionID(String sectionID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Section.class, sectionID);
        }
    }
    
    public List<Section> findUnassignedSections(String courseCode) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("Starting to find unassigned sections for course: " + courseCode);  // Log to check if method is being invoked.
            
            String hql = 
            	    "SELECT s " +
            	    "FROM Section s " +
            	    "JOIN s.course c " +
            	    "WHERE c.courseCode = :courseCode " +
            	    "AND s.sectionID NOT IN (" +
            	    "    SELECT sc.section.sectionID " +
            	    "    FROM Schedule sc " +
            	    "    GROUP BY sc.section.sectionID " +
            	    "    HAVING COUNT(sc.section.sectionID) >= 4" +
            	    ")";

  
            	List<Section> sections = session.createQuery(hql, Section.class)
            	                                .setParameter("courseCode", courseCode)
            	                                .list();

            if (sections.isEmpty()) {
                System.out.println("No unassigned sections found for Course " + courseCode); // Log if no sections found
            } else {
                System.out.println("Found unassigned sections: " + sections.size());
            }
            return sections;
        } catch (Exception e) {
            System.out.println("Error occurred while fetching unassigned sections.");
            e.printStackTrace();
            return null;
        }
    }
    
    public int countByCourseCode(String courseCode) {
        try {
            String jpql = "SELECT COUNT(s) FROM Section s WHERE s.course.courseCode = :courseCode";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("courseCode", courseCode);
            Long count = (Long) query.getSingleResult();
            return count.intValue(); // Return as an integer
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Return 0 if an exception occurs
        }
    }

    public void save(Section section) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(section);
            session.getTransaction().commit();
        }
    }

	
    public boolean deleteSection(String sectionID) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Section section = session.get(Section.class, sectionID);
            if (section != null) {
                session.delete(section);
                session.getTransaction().commit();
                return true;
            }
            session.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            System.err.println("Error deleting section: " + e.getMessage());
            return false;
        }
    }
    
    public void updateSection(Section section) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(section);
            session.getTransaction().commit();
        }
    }
    public List<Section> findSectionsByCourseCode(String courseCode) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Section s WHERE s.course.courseCode = :courseCode";
            return session.createQuery(hql, Section.class)
                          .setParameter("courseCode", courseCode)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
	public boolean existsById(String sectionID) {
		try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(l) FROM Section s WHERE s.sectionID = :sectionID";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("sectionID", sectionID)
                                .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	public boolean existsBySectionNameAndProgram(String sectionName, String program) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(s) FROM Section s WHERE s.sectionName = :sectionName AND s.program = :program";
            Long count = session.createQuery(hql, Long.class)
                                .setParameter("sectionName", sectionName)
                                .setParameter("program", program)
                                .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

