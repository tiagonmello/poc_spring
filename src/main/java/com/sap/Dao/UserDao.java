package com.sap.Dao;

import com.sap.models.Role;
import com.sap.models.Team;
import com.sap.models.User;

import java.util.List;

public interface UserDao {

    void  create(User user);

    void  update(User user);

    void  delete(User user);

    List<User> getAll();

    List<User> getUsersByRole(Role role);

    List<User> getUsersByTeam(Team team);

    User getUserByUsername(String username);

}
