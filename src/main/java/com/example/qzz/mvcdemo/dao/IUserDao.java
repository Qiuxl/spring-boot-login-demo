package com.example.qzz.mvcdemo.dao;

import com.example.qzz.mvcdemo.dao.meta.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    long add(User user);

    User get(long id);

    int delete(User user);

    int update(User user);

    List<User> findAll();

    User getByName(String name);
}
