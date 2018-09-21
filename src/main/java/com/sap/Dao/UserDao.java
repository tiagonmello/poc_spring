package com.sap.Dao;

import com.sap.models.User;

import java.util.List;

public interface UserDao {

    void  create(User user);

    List<User> getAll();

    User getUserByUsername(String username);

}
