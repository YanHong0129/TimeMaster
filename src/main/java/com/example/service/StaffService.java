package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Staff;
import com.example.repository.StaffDao;

@Service
public class StaffService {
    @Autowired
    private StaffDao staffDao;
    
    public Staff getStaffByStaffID(String staffID) {
        return staffDao.findByStaffID(staffID);
    }
    
    public boolean saveStaffInfo(Staff staff) {
        return staffDao.save(staff);
    }
    
    public boolean updateStaffInfo(Staff staff) {
        return staffDao.update(staff);
    }
}
