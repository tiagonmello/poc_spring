package com.sap.service;

import com.sap.models.EventDto;
import com.sap.models.User;

public interface EventService {

    void createEvent(EventDto event, User user);

}
