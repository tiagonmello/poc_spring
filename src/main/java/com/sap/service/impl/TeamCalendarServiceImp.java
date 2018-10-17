package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.Dao.TeamCalendarDao;
import com.sap.dtos.TeamCalendarDto;
import com.sap.models.*;
import com.sap.service.EventService;
import com.sap.service.TeamCalendarService;
import com.sap.service.UserService;

import javax.annotation.Resource;
import java.util.*;

public class TeamCalendarServiceImp implements TeamCalendarService {

    @Resource
    private TeamCalendarDao teamCalendarDao;

    @Resource
    private DayDao dayDao;

    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Override
    public void createTeamCalendar(TeamCalendarDto teamCalendarDto, Team team){

        // Checks if the new calendar will not overlap any already registered calendar
        for(TeamCalendar registeredCalendar : this.getTeamCalendarList(team)){
            if(!(teamCalendarDto.getStartDate().before(registeredCalendar.getStartDate()) || teamCalendarDto.getStartDate().after(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
            if(!(teamCalendarDto.getEndDate().before(registeredCalendar.getStartDate()) || teamCalendarDto.getEndDate().after(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
            if(!(teamCalendarDto.getStartDate().after(registeredCalendar.getStartDate()) || teamCalendarDto.getEndDate().before(registeredCalendar.getEndDate())))
                throw new IllegalArgumentException("Calendar dates overlapping with another calendar!");
        }

        // Day people limit plus late people limit must equal the team members quantity
        if(teamCalendarDto.getDayLimit() + teamCalendarDto.getLateLimit() != userService.getUsersByTeam(team).size() - 1){
            throw new IllegalArgumentException("People limits don't match team members quantity");
        }

        // Special day people limit must not be greater than team members quantity
        if(teamCalendarDto.getSpecialDayLimit() + teamCalendarDto.getSpecialLateLimit() > userService.getUsersByTeam(team).size() - 1){
            throw new IllegalArgumentException("Special days limits don't match team members quantity");
        }

        // Sets team calendar data and creates the calendar
        TeamCalendar teamCalendar = new TeamCalendar(teamCalendarDto.getDayLimit(), teamCalendarDto.getLateLimit(),
                                                     teamCalendarDto.getSpecialDayLimit(), teamCalendarDto.getSpecialLateLimit(), team);
        teamCalendarDao.createTeamCalendar(teamCalendar);

        // Creates normal days for every date of the calendar
        for(Date dayDate : this.getDateList(teamCalendarDto)){
            Day day = new Day(dayDate, SpecialType.NORMAL, teamCalendar, 0, 0, 0, 0);
            dayDao.createDay(day);
        }

        // Creates default event for every date of the calendar, for every member of the team
        for(Date eventDate : this.getDateList(teamCalendarDto))
            for(User user : userService.getUsersByTeam(team))
                eventService.createDefaultEvent(user, eventDate);
    }

    @Override
    public List<Date> getDateList(TeamCalendarDto teamCalendarDto){
        Calendar c = Calendar.getInstance();
        List<Date> dateList = new ArrayList<>();

        // Initialize loop date
        Date iterationDate = teamCalendarDto.getStartDate();
        dateList.add(iterationDate);

        // While the loop date doesn't reach the end date
        while(teamCalendarDto.getEndDate().compareTo(iterationDate) != 0){

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
    public List<Day> getAllDays(Team team){
        // Returns all dates of the days of all calendars of the received team
        List<Day> dayList = dayDao.getDayList(team);
        dayList.sort(Comparator.comparing(Day::getDayDate));
        return dayList;
    }

    @Override
    public TeamCalendar getCalendarById(Integer calendarId){
        TeamCalendar teamCalendar = teamCalendarDao.getCalendarById(calendarId);

        // Returns the calendar sorted by day date
        teamCalendar.getDays().sort(Comparator.comparing(Day::getDayDate));
        return teamCalendar;
    }

    @Override
    public List<TeamCalendar> getTeamCalendarList(Team team){
        return teamCalendarDao.getTeamCalendarList(team);
    }
}
