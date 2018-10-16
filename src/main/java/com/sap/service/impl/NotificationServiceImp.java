package com.sap.service.impl;

import com.sap.Dao.NotificationDao;
import com.sap.models.*;
import com.sap.service.NotificationService;

import javax.annotation.Resource;
import java.util.List;

public class NotificationServiceImp implements NotificationService {

    @Resource
    private NotificationDao notificationDao;

    @Override
    public void createNotification(Notification notification){
        notificationDao.createNotification(notification);
    }

    @Override
    public void deleteNotification(Notification notification){
        notificationDao.deleteNotification(notification);
    }

    @Override
    public List<Notification> getAllNotifications(){
        return notificationDao.getAllNotifications();
    }

    @Override
    public List<Notification> getNotificationsByTeam(Team team){
        return notificationDao.getNotificationsByTeam(team);
    }
}
