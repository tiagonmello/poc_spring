package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.Dao.EventDao;
import com.sap.dtos.EventDto;
import com.sap.models.*;
import com.sap.service.EventService;
import com.sap.service.UserService;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EventServiceImp implements EventService {

    @Resource
    private EventDao eventDao;

    @Resource
    private EventDao eventAllocationDao;

    @Resource
    private DayDao dayDao;

    @Resource
    private UserService userService;

    @Override
    public void createEvent(EventDto eventDto, User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date eventDate = new Date();
        boolean reallocatedAnyShift = false;

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

        // If it is a new event, creates the event before updating
        if(event.getId() == null){
            this.createDefaultEvent(user, eventDate);
            try {
                event = eventDao.getEventByDateAndUser(eventDate, user.getUserName());
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
                throw new IllegalArgumentException("Holiday full");
            }
        }

        // If the shift is already registered
        if(event.getShift().toString().toLowerCase().compareTo(eventDto.getShift()) == 0){
            return;
        }

        // Sets shifts and availability
        this.setEventShifts(eventDto, event);

        // Checks if there is allocation slots for this date
        if(event.getShift() == Shift.DAY || event.getShift() == Shift.ANY_DAY){

            if(getDayLimit(event) == event.getDay().getCurrentDay()){

                // Checks if it is possible to change one "ANY_DAY" to "ANY_LATE" of other user, then it would be possible to allocate this DAY shift
                for(Event allocationEvent : eventDao.getEventsByDateAndTeam(eventDate, event.getUser().getTeam())){
                    // If its shift is ANY_DAY, changes to ANY_LATE
                    if(allocationEvent.getShift().compareTo(Shift.ANY_DAY) == 0){

                        this.setLateShift(allocationEvent);

                        event.getDay().setCurrentDay(event.getDay().getCurrentDay() - 1);
                        event.getDay().setCurrentLate(event.getDay().getCurrentLate() + 1);
                        reallocatedAnyShift = true;
                        break;
                    }
                }
                // If could not reallocate, throws exception
                if(!reallocatedAnyShift){
                    throw new IllegalArgumentException("No slots left for day shift on this date");
                }
            }

            // Allocates the day shift
            event.getDay().setCurrentDay(event.getDay().getCurrentDay() + 1);

        }else if(event.getShift() == Shift.LATE || event.getShift() == Shift.ANY_LATE){

            if(getLateLimit(event) == event.getDay().getCurrentLate()) {

                // Checks if it is possible to change one "ANY_LATE" to "ANY_DAY" of other user, then it would be possible to allocate this LATE shift
                for (Event allocationEvent : eventDao.getEventsByDateAndTeam(eventDate, event.getUser().getTeam())) {
                    // If its shift is ANY_LATE, changes to ANY_DAY
                    if (allocationEvent.getShift().compareTo(Shift.ANY_LATE) == 0) {

                        this.setDayShift(allocationEvent);

                        event.getDay().setCurrentLate(event.getDay().getCurrentLate() - 1);
                        event.getDay().setCurrentDay(event.getDay().getCurrentDay() + 1);

                        reallocatedAnyShift = true;
                        break;
                    }
                }
                // If could not reallocate, throws exception
                if (!reallocatedAnyShift) {
                    throw new IllegalArgumentException("No slots left for late shift on this date");
                }
            }

            // Allocates the late shift
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

        // Sets the day with the same date of the event
        List<Day> dayList = dayDao.getDayList(user.getTeam());
        for(Day day : dayList){
            if(day.getDayDate().compareTo(eventDate) == 0){
                event.setDay(day);

                // Sets the better shift, the one with most slots available
                if(getDayLimit(event) - day.getCurrentDay() > getLateLimit(event) - day.getCurrentLate()){
                    if(getDayLimit(event) == day.getCurrentDay()){
                        return;
                    }
                    event.setShift(Shift.ANY_DAY);
                    day.setCurrentDay(day.getCurrentDay() + 1);
                }else{
                    if(getLateLimit(event) == day.getCurrentLate()){
                        return;
                    }
                    event.setShift(Shift.ANY_LATE);
                    day.setCurrentLate(day.getCurrentLate() + 1);
                }
                // Updates shift allocation
                dayDao.updateDay(day);
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
            case ANY_DAY:
                event.getDay().setCurrentDay(event.getDay().getCurrentDay() - 1);
                break;
            case LATE:
            case ANY_LATE:
                event.getDay().setCurrentLate(event.getDay().getCurrentLate() - 1);
                break;
        }

        // Sets shift
        switch(eventDto.getShift()){
            case "day":
            case "DAY":
                event.setShift(Shift.DAY);
                break;
            case "late":
            case "LATE":
                event.setShift(Shift.LATE);
                break;
            case "any":
            case "ANY":
                // Sets the better shift
                if(getDayLimit(event) - event.getDay().getCurrentDay() > getLateLimit(event) - event.getDay().getCurrentLate()){
                    event.setShift(Shift.ANY_DAY);
                }else{
                    event.setShift(Shift.ANY_LATE);
                }
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

    public List<List<Event>> getEventsByTeam(Team team) {
        // A list containing all event listed by user
        List<List<Event>> eventList = new ArrayList<>();

        for(User user : userService.getUsersByTeam(team)){
            if(user.getRole().getName().equals("ROLE_OWNER")) continue;

            eventList.add(eventDao.getEventsByUser(user.getUserName()));
        }

        return eventList;
    }

    public Integer getDayLimit(Event event){
        // If it is a special day
        if(!event.getDay().getType().equals(SpecialType.NORMAL)){
            return event.getDay().getCalendar().getSpecialDayLimit();
        }
        // Tests if it should use the day limit or the calendar limit (fallback)
        if(event.getDay().getDayLimit() == 0){
            return event.getDay().getCalendar().getDayLimit();
        }
        return event.getDay().getDayLimit();
    }

    public Integer getLateLimit(Event event){
        // If it is a special day
        if(!event.getDay().getType().equals(SpecialType.NORMAL)){
            return event.getDay().getCalendar().getSpecialLateLimit();
        }
        // Tests if it should use the day limit or the calendar limit (fallback)
        if(event.getDay().getLateLimit() == 0){
            return event.getDay().getCalendar().getLateLimit();
        }
        return event.getDay().getLateLimit();
    }

    public void setLateShift(Event event){
        event.setShift(Shift.ANY_LATE);
        eventAllocationDao.updateEvent(event);
    }

    public void setDayShift(Event event){
        event.setShift(Shift.ANY_DAY);
        eventAllocationDao.updateEvent(event);
    }
}
