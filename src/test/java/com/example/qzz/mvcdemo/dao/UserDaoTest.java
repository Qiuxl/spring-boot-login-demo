package com.example.qzz.mvcdemo.dao;

import com.example.qzz.mvcdemo.BaseTest;
import com.example.qzz.mvcdemo.dao.meta.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private IUserDao userDao;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setName("qzh");
        user.setPassword("qzh502");
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setEmail("812701654@qq.com");
        long id = userDao.add(user);
        logger.info("add result: {}",id);
    }
}
