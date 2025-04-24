package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Lecturer;
import com.example.repository.LecturerDao;

@Service
public class LecturerService {
	@Autowired
	private LecturerDao lecturerDao;
	
	public Lecturer getLecturerByID(String lecturerID) {
		return lecturerDao.findByLecturerID(lecturerID);
	}
    public List<Lecturer> getAvailableLecturers(String timeSlotID) {
        return lecturerDao.findAvailableLecturers(timeSlotID);
    }
    public List<Lecturer> getAllLecturers() {
    	return lecturerDao.getAllLecturers();
    }
    public List<Lecturer> searchLecturer(String keyword) {
        return lecturerDao.searchLecturer(keyword);
    }
	
	public void addLecturer(Lecturer lecturer) {
		if (lecturerDao.existsById(lecturer.getLecturerID())) {
	        throw new IllegalArgumentException("Lecturer with the same ID already exists.");
	    }
        lecturerDao.save(lecturer);
    }
	
	
	public void updateLecturer(Lecturer lecturer) {
		lecturerDao.update(lecturer);
    }
	
	public void deleteLecturerById(String lecturerId) {
        lecturerDao.deleteById(lecturerId);
    }
}
