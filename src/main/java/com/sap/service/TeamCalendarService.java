package com.sap.service;

import com.sap.dtos.TeamCalendarDto;
import com.sap.models.Day;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;

import java.util.Date;
import java.util.List;

public interface TeamCalendarService {

    void createTeamCalendar(TeamCalendarDto teamCalendarDto, Team team);

    List<TeamCalendar> getTeamCalendarList(Team team);

    List<Date> getDateList(TeamCalendarDto teamCalendarDto);

    List<Day> getAllDays(Team team);

}
