package com.example.qzz.mvcdemo.service.impl;

import com.example.qzz.mvcdemo.dao.IUserDao;
import com.example.qzz.mvcdemo.dao.meta.User;
import com.example.qzz.mvcdemo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {


    @Autowired
    private IUserDao userDao;

    @Override
    public boolean verifyLogin(String name, String password) {

        User user = userDao.getByName(name);
        if(user != null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
