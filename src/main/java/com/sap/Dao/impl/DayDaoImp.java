package com.sap.Dao.impl;

import com.sap.Dao.DayDao;
import com.sap.models.Day;
import com.sap.models.Team;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DayDaoImp extends HibernateDaoSupport implements DayDao {

    @Override
    @Transactional
    public void createDay(Day day){
        getHibernateTemplate().save(day);
    }

    @Override
    @Transactional
    public void updateDay(Day day){
        getHibernateTemplate().update(day);
    }

    @Override
    public List<Day> getDayList(Team team){
        return (List<Day>) getHibernateTemplate().find("from com.sap.models.Day as day where day.calendar.team.id='"+team.getId().toString()+"'");
    }

    @Override
    public Day getDayById(Integer id){
        return (Day) getHibernateTemplate().find("from com.sap.models.Day as day where day.id='"+id+"'").get(0);
    }
}