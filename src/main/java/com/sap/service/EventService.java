package com.sap.service;

import com.sap.models.Event;
import com.sap.dtos.EventDto;
import com.sap.models.User;

import java.util.Date;
import java.util.List;

public interface EventService {

    void createEvent(EventDto event, User user);

    void createDefaultEvent(User user, Date eventDate);

    public List<Event> getEventsByUser(String username);

}
