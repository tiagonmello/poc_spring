package com.sap.Dao;

import com.sap.models.Event;
import com.sap.models.Team;

import java.util.Date;
import java.util.List;

public interface EventDao {

    void createEvent(Event event);

    void updateEvent(Event event);

    public List<Event> getEventsByUser(String username);

    public Event getEventByDateAndUser(Date date, String username);

    public List<Event> getEventsByDateAndTeam(Date date, Team team);
}
