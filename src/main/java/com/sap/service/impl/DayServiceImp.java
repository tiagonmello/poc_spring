package com.sap.service.impl;

import com.sap.Dao.DayDao;
import com.sap.models.Day;
import com.sap.service.DayService;

import javax.annotation.Resource;

public class DayServiceImp implements DayService {

    @Resource
    private DayDao dayDao;

    @Override
    public Day getDayById(Integer id){
        return dayDao.getDayById(id);
    }

    @Override
    public void updateDayLimits(Integer id, Integer dayLimit, Integer lateLimit){
        Day day = this.getDayById(id);
        day.setDayLimit(dayLimit);
        day.setLateLimit(lateLimit);
        dayDao.updateDay(day);
    }

}
