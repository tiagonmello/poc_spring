package com.sap.Dao.impl;

import com.sap.Dao.TeamCalendarDao;
import com.sap.models.TeamCalendar;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class TeamCalendarDaoImp extends HibernateDaoSupport implements TeamCalendarDao {

    @Override
    @Transactional
    public void createTeamCalendar(TeamCalendar teamCalendar){
        getHibernateTemplate().save(teamCalendar);
    }

}