package com.sap.service;

import com.sap.dtos.DayDto;
import com.sap.models.SpecialDay;
import com.sap.models.Team;

import java.util.List;

public interface SpecialDayService {

    void addSpecialDay(DayDto dayDto, Team team);

    List<SpecialDay> getSpecialDayList(Team team);

}
