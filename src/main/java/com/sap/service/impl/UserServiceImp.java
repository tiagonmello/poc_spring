package com.sap.service.impl;

import com.sap.Dao.UserDao;
import com.sap.models.Role;
import com.sap.models.Team;
import com.sap.models.User;
import com.sap.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void createOwner(User user) {
        if(emailAlreadyExists(user))
            throw new IllegalArgumentException("Already registered email!");

        Role role = new Role();
        role.setName("ROLE_OWNER");
        user.setRole(role);

        Team team = new Team();
        team.setOwner(user);
        user.setTeam(team);

        userDao.create(user);
    }

    @Override
    public void createMember(User user, User teamOwner) {
        if(emailAlreadyExists(user))
            throw new IllegalArgumentException("Already registered email!");

        Role role = new Role();
        role.setName("ROLE_MEMBER");
        user.setRole(role);

        user.setTeam(teamOwner.getTeam());

        userDao.create(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    private boolean emailAlreadyExists(User user){
        boolean duplicatedEmail = false;
        List<User> userList = this.getAll();
        for(int i=0;i<userList.size() && !duplicatedEmail;i++){
            if(userList.get(i).getEmail().equals(user.getEmail())){
                duplicatedEmail = true;
            }
        }
        return duplicatedEmail;
    }
}
