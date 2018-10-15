package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.Dao.EventDao;
import com.sap.dtos.EventDto;
import com.sap.models.Day;
import com.sap.models.Event;
import com.sap.models.Shift;
import com.sap.models.User;
import com.sap.service.EventService;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EventServiceImp implements EventService {

    @Resource
    private EventDao eventDao;

    @Resource
    private DayDao dayDao;

    @Override
    public void createEvent(EventDto eventDto, User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date eventDate = new Date();
        Integer limit;

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

        // If the shift is already registered
        if(event.getShift().toString().toLowerCase().compareTo(eventDto.getShift()) == 0){
            return;
        }

        // Sets shifts and availability
        this.setEventShifts(eventDto, event);

        // Checks if there is allocation slots for this date
        if(event.getShift() == Shift.DAY){
            // Tests if it should use the day limit or the calendar limit (fallback)
            if(event.getDay().getDayLimit() == 0){
                limit = event.getDay().getCalendar().getDayLimit();
            }else{
                limit = event.getDay().getDayLimit();
            }

            if(limit == event.getDay().getCurrentDay()){
                throw new IllegalArgumentException("No slots left for day shift on this date");
            }
            // Allocates the day shift
            if(event.getDay().getCurrentDay() == null) event.getDay().setCurrentDay(0);
            event.getDay().setCurrentDay(event.getDay().getCurrentDay() + 1);

        }else if(event.getShift() == Shift.LATE){
            // Tests if it should use the day limit or the calendar limit (fallback)
            if(event.getDay().getDayLimit() == 0){
                limit = event.getDay().getCalendar().getLateLimit();
            }else{
                limit = event.getDay().getLateLimit();
            }

            if(limit == event.getDay().getCurrentLate()){
                throw new IllegalArgumentException("No slots left for late shift on this date");
            }
            // Allocates the late shift
            if(event.getDay().getCurrentLate() == null) event.getDay().setCurrentLate(0);
            event.getDay().setCurrentLate(event.getDay().getCurrentLate() + 1);
        }

        dayDao.updateDay(event.getDay());

        // Updates event
        eventDao.updateEvent(event);

    }

    @Override
    public void createDefaultEvent(User user, Date eventDate){
        if(user.getRole().getName().equals("ROLE_OWNER"))
            return;

        Event event = new Event();
        event.setShift(Shift.ANY);

        // Sets the day with the same date of the event
        List<Day> dayList = dayDao.getDayList(user.getTeam());
        for(Day day : dayList){
            if(day.getDayDate().compareTo(eventDate) == 0){
                event.setDay(day);
                break;
            }
        }

        event.setDayAvailability(true);
        event.setUser(user);
        eventDao.createEvent(event);
    }

    private void setEventShifts(@NotNull EventDto eventDto, Event event){
        // Deallocates previous shift
        switch(event.getShift()){
            case DAY:
                event.getDay().setCurrentDay(event.getDay().getCurrentDay() - 1);
                break;
            case LATE:
                event.getDay().setCurrentLate(event.getDay().getCurrentLate() - 1);
                break;
        }

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
        // Returns event list sorted by date
        List<Event> eventList = eventDao.getEventsByUser(username);
        eventList.sort(Comparator.comparing(Event::getEventDate));
        return eventList;
    }

}
