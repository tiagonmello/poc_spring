package com.sap.service;

import com.sap.dtos.DayDto;
import com.sap.models.Team;

public interface SpecialDayService {

    void addSpecialDay(DayDto dayDto, Team team);

}
