package com.sap.service.impl;

import com.sap.Dao.EventDao;
import com.sap.dtos.EventDto;
import com.sap.models.*;
import com.sap.service.EventService;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
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
            // Sets shifts and availability
            setEventShifts(eventDto, event);
            // Updates event
            eventDao.updateEvent(event);
        }else{
            // Sets shifts and availability
            setEventShifts(eventDto, event);
            // Sets event date
            event.setEventDate(eventDate);
            // Sets event user
            event.setUser(user);
            // Creates event
            eventDao.createEvent(event);
        }
    }

    private void setEventShifts(@NotNull EventDto eventDto, Event event){
        // Sets shift
        switch(eventDto.getShift()){
            case "day":
                event.setShift(Shift.DAY);
                break;
            case "late":
                event.setShift(Shift.LATE);
                break;
            case "any":
                event.setShift(Shift.ANY);
                break;
        }
        // Sets day availability
        if(eventDto.getDayAvailability() == null){
            event.setDayAvailability(false);
        }else{
            event.setDayAvailability(true);
        }
    }

    public List<Event> getEventsByUser(String username){
        return eventDao.getEventsByUser(username);
    }

}
