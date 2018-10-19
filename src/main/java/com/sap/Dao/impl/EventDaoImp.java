package com.sap.Dao.impl;

import com.sap.Dao.EventDao;
import com.sap.models.Event;
import com.sap.models.Team;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EventDaoImp extends HibernateDaoSupport implements EventDao {

    @Override
    @Transactional
    public void createEvent(Event event){
        getHibernateTemplate().save(event);
    }

    @Override
    @Transactional
    public void updateEvent(Event event){
        getHibernateTemplate().update(event);
    }

    @Override
    @Transactional
    public void deleteEvent(Event event){
        getHibernateTemplate().delete(event);
    }

    @Override
    public List<Event> getEventsByUser(String username) {
        List<Event> eventList = (List<Event>) getHibernateTemplate().find("from com.sap.models.Event as e where e.user.userName='"+username+"'");
        eventList.sort(Comparator.comparing(Event::getEventDate));
        return eventList;
    }

    @Override
    public Event getEventByDateAndUser(Date date, String username) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return (Event) getHibernateTemplate().find("from com.sap.models.Event as e where e.user.userName='"+username+"' and e.day.dayDate like '"+formattedDate+"%'").get(0);
    }

    @Override
    public List<Event> getEventsByDateAndTeam(Date date, Team team) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return (List<Event>) getHibernateTemplate().find("from com.sap.models.Event as e where e.user.team.id='"+team.getId().toString()+"' and e.day.dayDate like '"+formattedDate+"%'");
    }
}