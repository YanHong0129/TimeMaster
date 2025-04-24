package com.example.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.entity.Section;
import com.example.repository.SectionDao;


@Service
public class SectionService {
	@Autowired
	private SectionDao sectionDao;
	
	@Autowired
	CourseService courseService;
	
	public Section getSectionByID(String sectionID) {
		return sectionDao.findBySectionID(sectionID);
	}
	
    public List<Section> getAvailableSections(String courseCode) {
        return sectionDao.findUnassignedSections(courseCode);
    }
    // Retrieve a list of sections for a course
    public List<Section> getSectionsByCourseCode(String courseCode) {
        return sectionDao.findSectionsByCourseCode(courseCode);
    }
    public int getSectionCountByCourseCode(String courseCode) {
    	return sectionDao.countByCourseCode(courseCode);
    }

	public boolean deleteSection(String sectionID) {
		return sectionDao.deleteSection(sectionID);
	}

	public void updateSection(Section section) {
		sectionDao.updateSection(section);
	}

	public void saveSection(String courseCode, Section section) {
	    // Validate that the course exists
	    Course course = courseService.getCourseByCourseCode(courseCode);
	    if (course == null) {
	        throw new IllegalArgumentException("Course with code " + courseCode + " does not exist.");
	    }

	    // Parse and format sectionName as a two-digit integer
	    int sectionNumber;
	    try {
	        sectionNumber = Integer.parseInt(section.getSectionName());
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Section name must be a numeric value.");
	    }
	    String formattedSectionNumber = String.format("%02d", sectionNumber);

	    // Count existing sections for the course
	    int sectionCount = sectionDao.countByCourseCode(courseCode);

	    // Generate the SectionID in the format courseCode-0index
	    String sectionID = courseCode + "-" + formattedSectionNumber;
	    if (sectionDao.existsById(sectionID)) {
	        throw new IllegalArgumentException("Section ID " + sectionID + " already exists.");
	    }

	    // Set the generated sectionID and course
	    section.setSectionID(sectionID);
	    section.setCourse(course);

	    // Save the section to the database
	    sectionDao.save(section);
	}

	public boolean existsBySectionNameAndProgram(String sectionName, String program) {
		return sectionDao.existsBySectionNameAndProgram(sectionName, program);
	}

    
}
