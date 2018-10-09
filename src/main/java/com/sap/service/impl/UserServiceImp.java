package com.sap.service.impl;

import com.sap.Dao.EventDao;
import com.sap.Dao.UserDao;
import com.sap.models.*;
import com.sap.service.TeamCalendarService;
import com.sap.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private TeamCalendarService teamCalendarService;

    @Resource
    private EventDao eventDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void createMember(User user, User teamOwner) {
        // Checks if user already exists
        if(userAlreadyExists(user))
            throw new IllegalArgumentException("Already registered user!");

        // Sets role as member
        Role role = new Role();
        role.setName("ROLE_MEMBER");
        user.setRole(role);

        // Sets the owner's team
        user.setTeam(teamOwner.getTeam());

        // Encrypts the user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.createUser(user);

        for(Date eventDate : teamCalendarService.getDateList(user.getTeam())){
            if(user.getRole().getName().equals("ROLE_OWNER"))
                continue;

            Event event = new Event();
            event.setShift(Shift.ANY);
            event.setEventDate(eventDate);
            event.setDayAvailability(true);
            event.setUser(user);
            eventDao.createEvent(event);
        }
    }

    @Override
    public void updateMember(User user) {
        // Retrieves all users with the same email
        List<User> sameEmailUsers = userDao.getUsersByEmail(user.getEmail());

        // Tests for inconsistency of the database
        if(sameEmailUsers.size() > 1)
            throw new IllegalArgumentException("There are 2 users with the same email!");

        // Tests if there is already a different user registered with the desired email
        if (sameEmailUsers.size() == 1 && !sameEmailUsers.get(0).getUserName().equals(user.getUserName()))
            throw new IllegalArgumentException("Already registered email!");

        // Encrypts the user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.updateUser(user);
    }

    @Override
    public void deleteMember(User user) {
        userDao.deleteUser(user);
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

    // Sweeps all registered users looking for same username or email
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
