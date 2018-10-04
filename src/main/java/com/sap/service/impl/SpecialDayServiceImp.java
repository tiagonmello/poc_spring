package com.sap.service.impl;

import com.sap.Dao.TeamCalendarDao;
import com.sap.dtos.DayDto;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
import com.sap.service.SpecialDayService;
import com.sap.service.TeamCalendarService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialDayServiceImp implements SpecialDayService {

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private TeamCalendarDao teamCalendarDao;

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

                // Adds the holiday (if it is a holiday)
                if(dayDto.getDayType().equals("holiday")){
                    List<Date> holidays = new ArrayList<>();
                    holidays.add(dayDate);
                    calendar.setHolidays(holidays);
                }
                // Adds the weekend (if it is a weekend)
                if(dayDto.getDayType().equals("weekend")){
                    List<Date> weekends = new ArrayList<>();
                    weekends.add(dayDate);
                    calendar.setWeekends(weekends);
                }
                // Updates the calendar with the new special day
                teamCalendarDao.addSpecialDay(calendar);
                calendarFound = true;
            }
        }
        if(!calendarFound){
            throw new IllegalArgumentException("Special day date out of range");
        }

    }
}
