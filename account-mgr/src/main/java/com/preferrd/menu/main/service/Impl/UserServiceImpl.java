package com.preferrd.menu.main.service.Impl;

import com.preferrd.menu.main.dao.UserDao;
import com.preferrd.menu.main.model.User;
import com.preferrd.menu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;

    @Override public User getNameById(User user) {
        return userDao.getNameById(user);
    }



}
