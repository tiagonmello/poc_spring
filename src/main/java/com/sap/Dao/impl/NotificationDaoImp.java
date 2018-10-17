package com.sap.Dao.impl;

import com.sap.Dao.NotificationDao;
import com.sap.models.Notification;
import com.sap.models.Team;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class NotificationDaoImp extends HibernateDaoSupport implements NotificationDao {

    @Override
    @Transactional
    public void createNotification(Notification notification){
        getHibernateTemplate().save(notification);
    }

    @Override
    @Transactional
    public void deleteNotification(Notification notification){
        getHibernateTemplate().delete(notification);
    }

    @Override
    public List<Notification> getTextNotificationsByTeam(Team team){
        return (List<Notification>) getHibernateTemplate().find("from com.sap.models.Notification as n where n.team.id='"+team.getId().toString()+"' and n.text != null");
    }

    @Override
    public List<Notification> getShiftNotificationsByTeam(Team team){
        return (List<Notification>) getHibernateTemplate().find("from com.sap.models.Notification as n where n.team.id='"+team.getId().toString()+"' and n.text = null");
    }
}