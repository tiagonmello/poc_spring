package com.sap.service.impl;

import com.sap.Dao.TeamCalendarDao;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
import com.sap.service.TeamCalendarService;
import javax.annotation.Resource;
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
}
