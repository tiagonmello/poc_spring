package com.sap.Dao;

import com.sap.models.Event;

import java.util.Date;
import java.util.List;

public interface EventDao {

    void createEvent(Event event);

    void updateEvent(Event event);

    public List<Event> getEventsByUser(String username);

    public Event getEventByDateAndUser(Date date, String username);

}
