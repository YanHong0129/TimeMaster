package com.example.service;

import com.example.repository.NotificationDao;
import com.example.entity.Notification;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
    private final NotificationDao notificationDao;
	

    public NotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    // Insert a new notification
    public void createNotification(Notification notification) {
        notificationDao.insert(notification);
    }
    
    // Set a notification as read
    public void markAsRead(Long notificationId) {
        notificationDao.setReadStatus(notificationId);
    }

    // Retrieve all notifications for a student
    public List<Notification> retrieveByStudent(String matricNo) {
    	return notificationDao.retrieveByStudent(matricNo);
    }

    // Delete a notification
    public void deleteNotification(Long notificationId) {
        notificationDao.delete(notificationId);
    }
}
