package com.sap.Dao.impl;

import com.sap.Dao.EventDao;
import com.sap.models.Event;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class EventDaoImp extends HibernateDaoSupport implements EventDao {

    @Override
    @Transactional
    public void createEvent(Event event){
        getHibernateTemplate().save(event);
    }

}