package com.sap.service.impl;

import com.sap.Dao.OwnerDao;
import com.sap.Dao.UserDao;
import com.sap.models.Owner;
import com.sap.models.Role;
import com.sap.models.Team;
import com.sap.models.User;
import com.sap.service.OwnerService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

public class OwnerServiceImp implements OwnerService {

    @Resource
    private OwnerDao ownerDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void createOwner(Owner owner) {
        // Checks if user already exists
        if(userAlreadyExists(owner))
            throw new IllegalArgumentException("Already registered user!");

        // Set role as owner
        Role role = new Role();
        role.setName("ROLE_OWNER");
        owner.setRole(role);

        // Creates new team for the user to own
        Team team = new Team();
        team.setOwner(owner);
        owner.setTeam(team);
        owner.setTeamOwned(team);

        // Encrypts the user password
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        ownerDao.createOwner(owner);
    }

    // Sweeps all registered users looking for same username or email
    private boolean userAlreadyExists(Owner owner){
        boolean duplicatedUser = false;
        List<User> userList = userDao.getAll();
        for(int i=0;i<userList.size() && !duplicatedUser;i++){
            if(userList.get(i).getEmail().equals(owner.getEmail()) || userList.get(i).getUserName().equals(owner.getUserName())){
                duplicatedUser = true;
            }
        }
        return duplicatedUser;
    }

}
