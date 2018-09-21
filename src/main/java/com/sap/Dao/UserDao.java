package com.sap.Dao;

import com.sap.models.User;

import java.util.List;

public interface UserDao {

    void  create(User user);

    void  update(User user);

    void  delete(User user);

    List<User> getAll();

    User getUserByEmail(String email);

    User getUserByUsername(String username);

}
