package com.preferrd.menu.main.service.Impl;

import com.preferrd.menu.main.dao.UserDao;
import com.preferrd.menu.main.model.User;
import com.preferrd.menu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;

    @Transactional @Override public User getNameById(String id) {
        return userDao.getNameById(id);
    }

    @Transactional @Override public void addUser(User user) {
        userDao.addUser(user);
    }
}
