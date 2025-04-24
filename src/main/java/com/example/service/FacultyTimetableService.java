package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.FacultyTimetable;
import com.example.repository.FacultyTimetableDao;


@Service
public class FacultyTimetableService {
	@Autowired
	private FacultyTimetableDao facultyTimetableDao;
	
	public FacultyTimetable getFacultyTimetableByID(String facultyTimetableID) {
		return facultyTimetableDao.findByFacultyTimetableID(facultyTimetableID);
	}
	
	
}
