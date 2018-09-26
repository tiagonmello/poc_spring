package com.sap.Dao.impl;

import com.sap.Dao.OwnerDao;
import com.sap.models.Owner;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class OwnerDaoImp extends HibernateDaoSupport implements OwnerDao {

    @Override
    @Transactional
    public void createOwner(Owner owner){
        getHibernateTemplate().save(owner);
    }

}