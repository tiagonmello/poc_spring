package com.sap.Dao;

import com.sap.models.Notification;
import com.sap.models.Team;

import java.util.List;

public interface NotificationDao {

    void createNotification(Notification notification);

    void deleteNotification(Notification notification);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByTeam(Team team);

}
