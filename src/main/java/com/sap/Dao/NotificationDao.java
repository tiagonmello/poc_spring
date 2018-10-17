package com.sap.Dao;

import com.sap.models.*;

import java.util.List;

public interface NotificationDao {

    void createNotification(Notification notification);

    void deleteNotification(Notification notification);

    List<Notification> getShiftNotificationsByTeam(Team team);

    List<Notification> getTextNotificationsByTeam(Team team);

}
