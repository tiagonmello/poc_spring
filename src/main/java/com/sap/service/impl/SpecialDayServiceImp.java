package com.sap.service.impl;

import com.sap.Dao.SpecialDayDao;
import com.sap.dtos.DayDto;
import com.sap.models.SpecialDay;
import com.sap.models.SpecialType;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
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
    private SpecialDayDao specialDayDao;

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

                // Checks if there's already a special day registered to the received date
                for(SpecialDay specialDay : calendar.getSpecialDays()){
                    if(specialDay.getDayDate().compareTo(dayDate) == 0){
                        throw new IllegalArgumentException("Already registered special day on this date");
                    }
                }

                SpecialDay specialDay = new SpecialDay();
                // Sets day's calendar
                specialDay.setCalendar(calendar);

                // Sets day's date
                specialDay.setDayDate(dayDate);

                // Sets day's type
                if(dayDto.getDayType().equals("holiday")){
                    specialDay.setType(SpecialType.HOLIDAY);
                }else{
                    specialDay.setType(SpecialType.WEEKEND);
                }
                specialDayDao.createSpecialDay(specialDay);
            }
        }
        if(!calendarFound){
            throw new IllegalArgumentException("Special day date out of range");
        }

    }

    @Override
    public List<SpecialDay> getSpecialDayList(Team team){
        return specialDayDao.getSpecialDayList(team);
    }

}
