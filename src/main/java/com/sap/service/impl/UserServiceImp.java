package com.sap.service.impl;

import com.sap.Dao.UserDao;
import com.sap.models.Role;
import com.sap.models.Team;
import com.sap.models.User;
import com.sap.service.UserService;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void createOwner(User user) {
        if(userAlreadyExists(user))
            throw new IllegalArgumentException("Already registered user!");

        Role role = new Role();
        role.setName("ROLE_OWNER");
        user.setRole(role);

        Team team = new Team();
        team.setOwner(user);
        user.setTeam(team);
        user.setTeamOwned(team);

        userDao.create(user);
    }

    @Override
    public void createMember(User user, User teamOwner) {
        if(userAlreadyExists(user))
            throw new IllegalArgumentException("Already registered user!");

        Role role = new Role();
        role.setName("ROLE_MEMBER");
        user.setRole(role);

        user.setTeam(teamOwner.getTeam());

        userDao.create(user);
    }

    @Override
    public void updateMember(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteMember(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userDao.getUsersByRole(role);
    }

    @Override
    public List<User> getUsersByTeam(Team team) {
        return userDao.getUsersByTeam(team);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    private boolean userAlreadyExists(User user){
        boolean duplicatedUser = false;
        List<User> userList = this.getAll();
        for(int i=0;i<userList.size() && !duplicatedUser;i++){
            if(userList.get(i).getEmail().equals(user.getEmail()) || userList.get(i).getUserName().equals(user.getUserName())){
                duplicatedUser = true;
            }
        }
        return duplicatedUser;
    }
}
