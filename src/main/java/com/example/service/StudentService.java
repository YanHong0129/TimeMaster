package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentDao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student getStudentByMatricNo(String matricNo) {
        return studentDao.findByMatricNo(matricNo);
    }
    
    public boolean saveStudentInfo(Student student) {
    	return studentDao.save(student);
    }
    
    public boolean updateStudentInfo(Student student) {
        return studentDao.update(student);
    }
    
 // Retrieve all student matric numbers
    public List<String> findAllMatricNos() {
        return studentDao.findAllMatricNos();
    }
    
}
