package com.preferrd.menu.dao;


import com.preferrd.menu.model.Account;
import org.apache.ibatis.annotations.Mapper;


@Mapper public interface AccountDao extends PreferredMenuDao<Account> {
}
