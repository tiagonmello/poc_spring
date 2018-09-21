package com.sap.service;

import com.sap.models.User;

import java.util.List;

public interface UserService {

    void createOwner(User user);

    void createMember(User user, User teamOwner);

    List<User> getAll();

}
