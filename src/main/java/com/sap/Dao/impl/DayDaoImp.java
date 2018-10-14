package com.sap.Dao.impl;

import com.sap.Dao.SpecialDayDao;
import com.sap.models.SpecialDay;
import com.sap.models.Team;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SpecialDayDaoImp extends HibernateDaoSupport implements SpecialDayDao {

    @Override
    @Transactional
    public void createSpecialDay(SpecialDay specialDay){
        getHibernateTemplate().save(specialDay);
    }

    @Override
    public List<SpecialDay> getSpecialDayList(Team team){
        return (List<SpecialDay>) getHibernateTemplate().find("from com.sap.models.SpecialDay as s where s.calendar.team.id='"+team.getId().toString()+"'");
    }

}