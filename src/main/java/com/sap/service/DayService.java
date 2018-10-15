package com.sap.service;

import com.sap.models.Day;

public interface DayService {

    Day getDayById(Integer id);

    void updateDayLimits(Integer id, Integer dayLimit, Integer lateLimit);
}
