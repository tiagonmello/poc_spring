package com.sap.Dao;

import com.sap.models.Event;

import java.util.List;

public interface EventDao {

    void  createEvent(Event event);

    public List<Event> getEventsByUser(String username);

}
