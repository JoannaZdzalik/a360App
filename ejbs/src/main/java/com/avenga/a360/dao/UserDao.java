package com.avenga.a360.dao;

import com.avenga.a360.model.User;

import java.util.List;

public interface UserDao {

    boolean createUser(User user);

    User findUserByLogin(String login);

    List<User> findAllUsers();

    boolean updateUser(User user);
}
