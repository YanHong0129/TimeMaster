package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.PublishedSchedule;
import com.example.repository.PublishedScheduleDao;


@Service
public class PublishedScheduleService {
	@Autowired
    private PublishedScheduleDao publishedScheduleDao;
	
	public List<PublishedSchedule> getAllSchedules(){
    	return publishedScheduleDao.findAllSchedules();
    }
	
	public int getSchedulesCount(String facultyTimetableID) {
		return publishedScheduleDao.getSchdeuleCount(facultyTimetableID);
	}
	
	public List<PublishedSchedule> getSchedulesByYear(String facultyTimetableID){
    	return publishedScheduleDao.findSchedulesByYear(facultyTimetableID);
    }
	
	public boolean updatePublishedSchedule(List<PublishedSchedule> schedules) {
		return publishedScheduleDao.update(schedules);
	}
    
}
