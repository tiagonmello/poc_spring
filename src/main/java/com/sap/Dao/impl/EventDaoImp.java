package com.sap.Dao.impl;

import com.sap.Dao.EventDao;
import com.sap.models.Event;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
    public List<Event> getEventsByUser(String username) {
        return (List<Event>) getHibernateTemplate().find("from com.sap.models.Event as e where e.user.userName='"+username+"'");
    }

    @Override
    public Event getEventByDateAndUser(Date date, String username) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return (Event) getHibernateTemplate().find("from com.sap.models.Event as e where e.user.userName='"+username+"' and e.eventDate like '"+formattedDate+"%'").get(0);
    }
}