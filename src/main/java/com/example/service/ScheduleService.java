package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Classroom;
import com.example.entity.Schedule;
import com.example.repository.ScheduleDao;

@Service
public class ScheduleService {
	@Autowired
    private ScheduleDao scheduleDao;

    public Schedule getScheduleByID(String scheduleID) {
        return scheduleDao.findByID(scheduleID);
    }
    
    public List<Schedule> getAllSchedules(){
    	return scheduleDao.findAllSchedules();
    }
    
    public List<Schedule> getSchedulesByClassroom(Classroom classroom){
    	return scheduleDao.findSchedulesByClassroom(classroom);
    }
    
    public List<Schedule> getSchedulesByTimeSlotID(String timeSlotID){
    	return scheduleDao.findSchedulesByTimeSlotId(timeSlotID);
    }
    
    public Schedule getNextSchedule(String scheduleID) {
    	return scheduleDao.findNextSchedule(scheduleID);
    }
    
    public Schedule getPreviousSchedule(String scheduleID) {
    	return scheduleDao.findPreviousSchedule(scheduleID);
    }
    
    public int getScheduleCount(String timeSlotID) {
        return scheduleDao.getScheduleCount(timeSlotID); // Call the DAO layer method
    }
    
    public List<Schedule> getSchedulesWithPagination(String timeSlotID, int offset, int limit){
    	return scheduleDao.findSchedulesWithPagination(timeSlotID, offset, limit);
    }
    
	public void addSchedule(Schedule schedule) {
		scheduleDao.save(schedule);
	}

	public boolean updateSchedule(Schedule schedule) {
		return scheduleDao.update(schedule);
	}
	
	public void deleteSchedule(Classroom classroom) {
		 scheduleDao.delete(classroom);
	}
    
}
