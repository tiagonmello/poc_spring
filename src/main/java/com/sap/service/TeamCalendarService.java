package com.sap.service;

import com.sap.models.Team;
import com.sap.models.TeamCalendar;

public interface TeamCalendarService {

    void createTeamCalendar(TeamCalendar teamCalendar, Team team);

}
