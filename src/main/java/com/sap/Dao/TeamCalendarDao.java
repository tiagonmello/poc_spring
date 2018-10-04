package com.sap.Dao;

import com.sap.models.Team;
import com.sap.models.TeamCalendar;

import java.util.List;

public interface TeamCalendarDao {

    void  createTeamCalendar(TeamCalendar teamCalendar);

    void  addSpecialDay(TeamCalendar teamCalendar);

    List<TeamCalendar> getTeamCalendarList(Team team);

}
