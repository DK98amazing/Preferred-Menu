package com.preferrd.menu.main.dao;


import com.preferrd.menu.main.model.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper public interface UserDao {

    User getNameById(String Id);

    void addUser(User user);


}
