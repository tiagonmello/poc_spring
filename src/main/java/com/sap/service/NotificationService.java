package com.sap.service;

import com.sap.dtos.EventDto;
import com.sap.models.Notification;
import com.sap.models.Team;
import com.sap.models.TextNote;

import java.util.List;

public interface NotificationService {

    void createTextNote(TextNote notification);

    void createShiftNote(EventDto notification, Team team);

    void deleteNotification(Notification notification);

    List<Notification> getShiftNotificationsByTeam(Team team);

    List<Notification> getTextNotificationsByTeam(Team team);

}
