package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.Dao.EventDao;
import com.sap.models.Day;
import com.sap.models.Event;
import com.sap.models.User;
import com.sap.service.DayService;
import com.sap.service.EventService;
import com.sap.service.UserService;

import javax.annotation.Resource;

public class DayServiceImp implements DayService {

    @Resource
    private DayDao dayDao;

    @Resource
    private EventDao eventDao;

    @Resource
    private EventDao eventDeleteDao;

    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Override
    public Day getDayById(Integer id){
        return dayDao.getDayById(id);
    }

    @Override
    public void updateDayLimits(Integer id, Integer dayLimit, Integer lateLimit){
        Day day = this.getDayById(id);

        // If it would alter the people limit of the day
        if(day.getCalendar().getDayLimit() + day.getCalendar().getLateLimit() != dayLimit + lateLimit){
            return;
        }

        // Updates day limits
        day.setDayLimit(dayLimit);
        day.setLateLimit(lateLimit);
        day.setCurrentDay(0);
        day.setCurrentLate(0);
        dayDao.updateDay(day);

        // Deletes already registered events of this day
        for(Event registeredEvent : eventDao.getEventsByDateAndTeam(day.getDayDate(),day.getCalendar().getTeam())){
            Event deleteEvent = new Event();
            deleteEvent.setId(registeredEvent.getId());
            eventDeleteDao.deleteEvent(deleteEvent);
        }

        // Recreates events for the day
        for(User user : userService.getUsersByTeam(day.getCalendar().getTeam())){
            eventService.createDefaultEvent(user, day.getDayDate());
        }

    }
}
