package com.sap.Dao;

import com.sap.models.Day;
import com.sap.models.Team;

import java.util.List;

public interface DayDao {

    void  createDay(Day day);

    void  updateDay(Day day);

    Day getDayById(Integer id);

    List<Day> getDayList(Team team);
}
