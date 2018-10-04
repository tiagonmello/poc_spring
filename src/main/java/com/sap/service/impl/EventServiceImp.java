package com.sap.service.impl;

import com.sap.Dao.EventDao;
import com.sap.models.*;
import com.sap.service.EventService;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventServiceImp implements EventService {

    @Resource
    private EventDao eventDao;

    @Override
    public void createEvent(EventDto eventDto, User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date eventDate = new Date();

        // Formats received date
        try {
            eventDate = formatter.parse(eventDto.getEventDate());
        }catch(ParseException e){
            e.printStackTrace();
        }

        // Tries to retrieve the event for the received date and user
        Event event = new Event();
        try {
            event = eventDao.getEventByDateAndUser(eventDate, user.getUserName());
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        // If there's a already registered event for this date
        if(event.getId() != null){
            // Sets day shift
            if(eventDto.getDayShift() == null){
                event.setDayShift(false);
            }else{
                event.setDayShift(true);
            }
            // Sets late shift
            if(eventDto.getLateShift() == null){
                event.setLateShift(false);
            }else{
                event.setLateShift(true);
            }
            eventDao.updateEvent(event);
        }else{
            // Sets day shift
            if(eventDto.getDayShift() == null){
                event.setDayShift(false);
            }else{
                event.setDayShift(true);
            }
            // Sets late shift
            if(eventDto.getLateShift() == null){
                event.setLateShift(false);
            }else{
                event.setLateShift(true);
            }
            // Sets event date
            event.setEventDate(eventDate);
            // Sets event user
            event.setUser(user);
            // Creates event
            eventDao.createEvent(event);
        }
    }

    public List<Event> getEventsByUser(String username){
        return eventDao.getEventsByUser(username);
    }

}