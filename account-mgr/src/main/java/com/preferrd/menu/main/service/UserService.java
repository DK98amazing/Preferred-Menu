package com.preferrd.menu.main.service;

import com.preferrd.menu.main.model.User;

public interface UserService {

    User getNameById(String id);

    void addUser(User user);

}
