package com.sap.service;

import com.sap.models.User;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User getUserByEmail(String email);

}
