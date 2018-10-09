package com.sap.service.impl;

import com.sap.Dao.EventDao;
import com.sap.Dao.TeamCalendarDao;
import com.sap.models.*;
import com.sap.service.EventService;
import com.sap.service.TeamCalendarService;
import com.sap.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeamCalendarServiceImp implements TeamCalendarService {

    @Resource
    private TeamCalendarDao teamCalendarDao;

    @Resource
    private EventDao eventDao;

    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Override
    public void createTeamCalendar(TeamCalendar teamCalendar, Team team){
        // Checks if the new calendar will not overlap any already registered calendar
        for(TeamCalendar registeredCalendar : this.getTeamCalendarList(team)){
            if(!(teamCalendar.getStartDate().before(registeredCalendar.getStartDate()) || teamCalendar.getStartDate().after(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
            if(!(teamCalendar.getEndDate().before(registeredCalendar.getStartDate()) || teamCalendar.getEndDate().after(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
            if(!(teamCalendar.getStartDate().after(registeredCalendar.getStartDate()) || teamCalendar.getEndDate().before(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
        }
        // Sets the team and creates the calendar
        teamCalendar.setTeam(team);
        teamCalendarDao.createTeamCalendar(teamCalendar);

        // Creates default event for every date of the calendar, for every member of the team
        for(Date eventDate : this.getDateList(teamCalendar))
            for(User user : userService.getUsersByTeam(team))
                eventService.createDefaultEvent(user, eventDate);
    }

    @Override
    public List<Date> getDateList(Team team){
        List<Date> dateList = new ArrayList<>();

        // For every calendar registered, adds the respective list of dates
        for(TeamCalendar calendar : this.getTeamCalendarList(team)){
            dateList.addAll(getDateList(calendar));
        }

        return dateList;
    }

    @Override
    public List<Date> getDateList(TeamCalendar teamCalendar){
        Calendar c = Calendar.getInstance();
        List<Date> dateList = new ArrayList<>();

        // Initialize loop date
        Date iterationDate = teamCalendar.getStartDate();
        dateList.add(iterationDate);

        // While the loop date doesn't reach the end date
        while(teamCalendar.getEndDate().compareTo(iterationDate) != 0){

            // Increments loop date
            c.setTime(iterationDate);
            c.add(Calendar.DATE,1);
            iterationDate = c.getTime();

            // Adds loop date to the date list
            dateList.add(iterationDate);
        }
        return dateList;
    }

    @Override
    public List<TeamCalendar> getTeamCalendarList(Team team){
        return teamCalendarDao.getTeamCalendarList(team);
    }
}
