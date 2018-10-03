package com.sap.service.impl;

import com.sap.Dao.EventDao;
import com.sap.models.*;
import com.sap.service.EventService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EventServiceImp implements EventService {

    @Resource
    private EventDao eventDao;

    @Override
    public void createEvent(EventDto eventDto, User user) {
        Event event = new Event();

        // Sets event user
        event.setUser(user);

        // Sets event date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            event.setEventDate(formatter.parse(eventDto.getEventDate()));
        }catch (ParseException e){
            e.printStackTrace();
        }

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

        // Creates event
        eventDao.createEvent(event);
    }

    public List<Event> getEventsByUser(String username){
        /*List<Event> eventList = new ArrayList<>();
        try{
            eventList = eventDao.getEventsByUser(username);
        }catch(PersistenceException e){
            return null;
        }
        return eventList;
        */
        return eventDao.getEventsByUser(username);
    }

}
