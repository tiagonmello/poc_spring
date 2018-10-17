package com.sap.service.impl;

import com.sap.Dao.NotificationDao;
import com.sap.dtos.EventDto;
import com.sap.models.*;
import com.sap.service.NotificationService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationServiceImp implements NotificationService {

    @Resource
    private NotificationDao notificationDao;

    @Override
    public void createTextNote(TextNote notification){
        notificationDao.createNotification(notification);
    }

    @Override
    public void createShiftNote(EventDto notification, Team team){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        ShiftNote shiftNote = new ShiftNote();

        // Sets date
        try {
            date = formatter.parse(notification.getEventDate());
            shiftNote.setDayDate(date);
        }catch(ParseException e){
            e.printStackTrace();
        }

        // Sets shift
        switch(notification.getShift()){
            case "day":
                shiftNote.setShift(Shift.DAY);
                break;
            case "late":
                shiftNote.setShift(Shift.LATE);
                break;
        }

        // Sets team
        shiftNote.setTeam(team);

        notificationDao.createNotification(shiftNote);
    }

    @Override
    public void deleteNotification(Notification notification){
        notificationDao.deleteNotification(notification);
    }

    @Override
    public List<Notification> getShiftNotificationsByTeam(Team team){
        return notificationDao.getShiftNotificationsByTeam(team);
    }

    @Override
    public List<Notification> getTextNotificationsByTeam(Team team){
        return notificationDao.getTextNotificationsByTeam(team);
    }
}
