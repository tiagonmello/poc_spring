package com.sap.service;

import com.sap.models.Role;
import com.sap.models.Team;
import com.sap.models.User;

import java.util.List;

public interface UserService {

    void createOwner(User user);

    void createMember(User user, User teamOwner);

    void updateMember(User user);

    void deleteMember(User user);

    List<User> getAll();

    List<User> getUsersByTeam (Team team);

    List<User> getUsersByRole(Role role);

    User getUserByUsername(String username);

}
