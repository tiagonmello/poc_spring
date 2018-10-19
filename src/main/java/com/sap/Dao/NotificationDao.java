package com.sap.Dao;

import com.sap.models.Notification;
import com.sap.models.ShiftNote;
import com.sap.models.Team;

import java.util.List;

public interface NotificationDao {

    void createNotification(Notification notification);

    void deleteNotification(Notification notification);

    List<ShiftNote> getShiftNotificationsByTeam(Team team);

    List<Notification> getTextNotificationsByTeam(Team team);

}
