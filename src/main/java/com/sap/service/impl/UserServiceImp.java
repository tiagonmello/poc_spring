package com.sap.service.impl;

import com.sap.Dao.UserDao;
import com.sap.models.Role;
import com.sap.models.User;
import com.sap.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void create(User user) {
        boolean duplicatedEmail = false;
        List<User> userList = this.getAll();
        for(int i=0;i<userList.size() && !duplicatedEmail;i++){
            if(userList.get(i).getEmail().equals(user.getEmail())){
                duplicatedEmail = true;
            }
        }
        if(!duplicatedEmail){
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            user.setRole(role);
            userDao.create(user);
        }else{
            throw new IllegalArgumentException("Already registered email!");
        }
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
