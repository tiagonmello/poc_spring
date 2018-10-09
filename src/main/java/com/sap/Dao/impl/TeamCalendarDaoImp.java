package com.sap.Dao.impl;

import com.sap.Dao.TeamCalendarDao;
import com.sap.models.Team;
import com.sap.models.TeamCalendar;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TeamCalendarDaoImp extends HibernateDaoSupport implements TeamCalendarDao {

    @Override
    @Transactional
    public void createTeamCalendar(TeamCalendar teamCalendar){
        getHibernateTemplate().save(teamCalendar);
    }

    @Override
    @Transactional
    public void addSpecialDay(TeamCalendar teamCalendar){
        getHibernateTemplate().update(teamCalendar);
    }

    @Override
    public List<TeamCalendar> getTeamCalendarList(Team team){
        return (List<TeamCalendar>) getHibernateTemplate().find("from com.sap.models.TeamCalendar as t where t.team.id='"+team.getId()+"'");
    }

}