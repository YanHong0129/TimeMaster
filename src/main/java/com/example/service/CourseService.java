package com.example.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Course;
import com.example.repository.CourseDao;
import com.example.repository.SectionDao;

@Service
public class CourseService {
	@Autowired
    private CourseDao courseDao;
	
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	SectionService sectionService;
    
	public Course getCourseByCourseCode(String courseCode) {
		return courseDao.findByCourseCode(courseCode);
	}
//    public List<Course> getCoursesByYear(int year){
//    	return courseDao.findCoursesByYear(year);
//    }
    public List<Course> getAvaillableCourses(){
    	return courseDao.findAvailableCourses();
    }
    
    public List<Course> getCoursesWithSectionCount() {
    	List<Course> courses = courseDao.findAvailableCourses();
        return courses.stream().map(course -> {
            int sectionCount = sectionDao.countByCourseCode(course.getCourseCode());
            course.setSectionCount(sectionCount); // Assuming `sectionCount` is a property in `Course`
            return course;
        }).collect(Collectors.toList());
    }
    
    
    public List<Course> searchCourses(String keyword) {
    	List<Course> courses = courseDao.searchCourses(keyword);

        // For each course, calculate and set the section count
        for (Course course : courses) {
            int sectionCount = sectionService.getSectionCountByCourseCode(course.getCourseCode());
            course.setSectionCount(sectionCount); // Ensure this setter exists in the Course entity
        }

        return courses;
    }
    public void addCourse(Course course) {
        if (course.getCourseCode() == null || course.getCourseCode().isEmpty()) {
            throw new IllegalArgumentException("Course Code cannot be null or empty.");
        }
        if (courseDao.existsById(course.getCourseCode())) {
            throw new IllegalArgumentException("Course ID already exists.");
        }
        courseDao.save(course);
    }
    
    
    public void updateCourse(Course course) {
        courseDao.update(course);
    }
	public void deleteCourse(String courseCode) {
		courseDao.deleteCourse(courseCode);
	}
	
	

}

