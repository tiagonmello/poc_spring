package com.sap.service;

import com.sap.models.Event;
import com.sap.models.EventDto;
import com.sap.models.User;

import java.util.List;

public interface EventService {

    void createEvent(EventDto event, User user);

    public List<Event> getEventsByUser(String username);

}
