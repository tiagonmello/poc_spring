package com.sap.service;

import com.sap.dtos.EventDto;
import com.sap.models.Event;
import com.sap.models.Team;
import com.sap.models.User;

import java.util.Date;
import java.util.List;

public interface EventService {

    void createEvent(EventDto event, User user);

    void createDefaultEvent(User user, Date eventDate);

    List<Event> getEventsByUser(String username);

    List<List<Event>> getEventsByTeam(Team team);

}
