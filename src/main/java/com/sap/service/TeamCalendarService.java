package com.sap.service;

import com.sap.models.Team;
import com.sap.models.TeamCalendar;

import java.util.Date;
import java.util.List;

public interface TeamCalendarService {

    void createTeamCalendar(TeamCalendar teamCalendar, Team team);

    List<TeamCalendar> getTeamCalendarList(Team team);

    List<Date> getDateList(Team team);

    List<Date> getDateList(TeamCalendar teamCalendar);

}
