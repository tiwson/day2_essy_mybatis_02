package com.tiwson.mybatis.dao;

import com.tiwson.mybatis.bean.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findUser(Integer id);
}
