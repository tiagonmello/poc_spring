package com.sap.Dao.impl;

import com.sap.Dao.UserDao;
import com.sap.models.User;
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
    public User getUserByEmail(String email){
        User user = new User();
        user.setEmail(email);
        return (User) getHibernateTemplate().find("from com.sap.models.User as u where u.email=" + email).get(0);
    }

    @Override
    public User getUserByUsername(String username){
        List<User> userList = this.getAll();
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getUserName().equals(username)){
                return userList.get(i);
            }
        }
        return null;

//        User user = new User();
//        user.setUserName(username);
//        return (User) getHibernateTemplate().find("from com.sap.models.User as u where u.userName=" + username).get(0);
    }

}
