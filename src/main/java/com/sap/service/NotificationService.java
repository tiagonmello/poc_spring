package com.sap.service;

import com.sap.models.Notification;
import com.sap.models.Team;
import com.sap.models.TextNote;

import java.util.List;

public interface NotificationService {

    void createTextNote(TextNote notification);

    void deleteTextNote(TextNote notification);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByTeam(Team team);

}
