package com.example.qzz.mvcdemo.service.impl;

import com.example.qzz.mvcdemo.dao.IUserDao;
import com.example.qzz.mvcdemo.dao.meta.User;
import com.example.qzz.mvcdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public boolean updatePassword(String name, String password) {

        User user = getByName(name);
        if(user == null){
            // 更新失败，没有该用户名
            return false;
        }
        user.setPassword(password);
        updateUserInfo(user);
        return true;
    }

    @Override
    public int updateUserInfo(User user) {
        user.setUpdateTime(System.currentTimeMillis());
        return userDao.update(user);
    }
}
