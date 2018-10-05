package com.sap.Dao;

import com.sap.models.SpecialDay;
import com.sap.models.Team;

import java.util.List;

public interface SpecialDayDao {

    void  createSpecialDay(SpecialDay specialDay);

    List<SpecialDay> getSpecialDayList(Team team);
}
