package com.example.qzz.mvcdemo.service;

import com.example.qzz.mvcdemo.dao.meta.User;

public interface IUserService {

    User getByName(String name);

    boolean updatePassword(String name,String password);

    int updateUserInfo(User user);
}
