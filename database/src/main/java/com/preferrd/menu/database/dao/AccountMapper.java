package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByPrimaryKey(@Param("accountId") String accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}