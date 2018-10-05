package com.sap.service.impl;

import com.sap.Dao.TeamCalendarDao;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
import com.sap.service.TeamCalendarService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeamCalendarServiceImp implements TeamCalendarService {

    @Resource
    private TeamCalendarDao teamCalendarDao;

    @Override
    public void createTeamCalendar(TeamCalendar teamCalendar, Team team){
        teamCalendar.setTeam(team);
        teamCalendarDao.createTeamCalendar(teamCalendar);
    }

    @Override
    public List<TeamCalendar> getTeamCalendarList(Team team){
        return teamCalendarDao.getTeamCalendarList(team);
    }

    @Override
    public List<Date> getDateList(Team team){
        Calendar c = Calendar.getInstance();
        List<TeamCalendar> calendarList = this.getTeamCalendarList(team);
        List<Date> dateList = new ArrayList<>();

        // For every calendar registered
        for(TeamCalendar calendar : calendarList){

            // Initialize loop date
            Date iterationDate = calendar.getStartDate();
            dateList.add(iterationDate);

            // While the loop date doesn't reach the end date
            while(calendar.getEndDate().compareTo(iterationDate) != 0){

                // Increments loop date
                c.setTime(iterationDate);
                c.add(Calendar.DATE,1);
                iterationDate = c.getTime();

                // Adds loop date to the date list
                dateList.add(iterationDate);
            }
        }
        return dateList;
    }

}
