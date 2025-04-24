package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.TimeSlot;
import com.example.repository.TimeSlotDao;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotDao timeSlotDao;

    public List<TimeSlot> getTimeSlots(){
    	return timeSlotDao.findAllTimeSlots();
    }
    public TimeSlot getTimeSlotByID(String timeSlotID) {
        return timeSlotDao.findByID(timeSlotID);
    }
    
    public TimeSlot getNextTimeSlot(String timeSlotID) {
        return timeSlotDao.findNextTimeSlot(timeSlotID);
    }
    
    public TimeSlot getPreviousTimeSlot(String timeSlotID) {
        return timeSlotDao.findPreviousTimeSlot(timeSlotID);
    }
}
