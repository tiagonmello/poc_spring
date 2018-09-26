package com.sap.Dao.impl;

import com.sap.Dao.UserDao;
import com.sap.models.Team;
import com.sap.models.User;
import com.sap.models.Role;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImp extends HibernateDaoSupport implements UserDao {

    @Override
    @Transactional
    public void create(User user){
        getHibernateTemplate().save(user);
    }

    @Override
    @Transactional
    public void update(User user){
        getHibernateTemplate().update(user);
    }

    @Override
    @Transactional
    public void delete(User user){
        getHibernateTemplate().delete(user);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) getHibernateTemplate().find("from com.sap.models.User");
    }

    @Override
    public List<User> getUsersByRole(Role role){
        return (List<User>) getHibernateTemplate().find("from com.sap.models.User as u where u.role.id='"+role.getId().toString()+"'");
    }

    @Override
    public List<User> getUsersByTeam(Team team){
        return (List<User>) getHibernateTemplate().find("from com.sap.models.User as u where u.team.id='"+team.getId().toString()+"'");
    }

    @Override
    public User getUserByUsername(String username){
        return (User) getHibernateTemplate().find("from com.sap.models.User as u where u.userName='" + username + "'").get(0);
    }

    @Override
    public List<User> getUsersByEmail(String email){
        return (List<User>) getHibernateTemplate().find("from com.sap.models.User as u where u.email='" + email + "'");
    }
}