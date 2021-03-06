package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.Dao.EventDao;
import com.sap.dtos.DayDto;
import com.sap.models.*;
import com.sap.service.SpecialDayService;
import com.sap.service.TeamCalendarService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SpecialDayServiceImp implements SpecialDayService {

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private DayDao dayDao;

    @Resource
    private EventDao eventDao;

    @Resource
    private EventDao eventDeleteDao;

    @Override
    public void addSpecialDay(DayDto dayDto, Team team){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dayDate = new Date();

        boolean calendarFound = false;

        // Formats received date
        try {
            dayDate = formatter.parse(dayDto.getDayDate());
        }catch(ParseException e){
            e.printStackTrace();
        }

        // Checks if there's a calendar for this date
        List<TeamCalendar> calendarList = teamCalendarService.getTeamCalendarList(team);
        for(TeamCalendar calendar : calendarList){

            // If it is the correct calendar
            if(!(calendar.getStartDate().after(dayDate) || calendar.getEndDate().before(dayDate))){
                calendarFound = true;

                // Checks if there's already a not normal day registered at the received date
                for(Day day : calendar.getDays()){
                    if(day.getDayDate().compareTo(dayDate) == 0){

                        if(!day.getType().equals(SpecialType.NORMAL))
                            throw new IllegalArgumentException("Already registered special day on this date");

                        // Updates day
                        if(dayDto.getDayType().equals("holiday")){
                            day.setType(SpecialType.HOLIDAY);
                        }else{
                            day.setType(SpecialType.WEEKEND);
                        }
                        day.setCurrentDay(0);
                        day.setCurrentLate(0);
                        dayDao.updateDay(day);

                        // Delete already registered events of this date
                        for(Event registeredEvent : eventDao.getEventsByDateAndTeam(day.getDayDate(),team)){
                            Event deleteEvent = new Event();
                            deleteEvent.setId(registeredEvent.getId());
                            eventDeleteDao.deleteEvent(deleteEvent);
                        }

                        break;
                    }
                }
            }
        }
        if(!calendarFound){
            throw new IllegalArgumentException("Special day date out of range");
        }

    }
}
