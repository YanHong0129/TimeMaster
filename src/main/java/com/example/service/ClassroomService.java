package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Classroom;
import com.example.repository.ClassroomDao;

@Service
public class ClassroomService {
	@Autowired
	private ClassroomDao classroomDao;
	
	public Classroom getClassroomById(String id) {
		return classroomDao.findById(id);
	}
	
	public List<Classroom> getAllClassrooms(){
		return classroomDao.getAllClassrooms();
	}
	
	public List<Classroom> searchClassrooms(String keyword) {
        return classroomDao.searchClassrooms(keyword);
    }
	
	public int getClassroomCount() {
		return classroomDao.getCount();
	}
	
	public void addClassroom(Classroom classroom) {
		if (classroomDao.existsById(classroom.getClassroomID())) {
            throw new IllegalArgumentException("Classroom ID already exists.");
        }
        classroomDao.save(classroom);
    }
	
	
	public void updateClassroom(Classroom classroom) {
        classroomDao.update(classroom);
    }
	
	public void deleteClassroomById(String classroomId) {
        classroomDao.deleteById(classroomId);
    }

}

