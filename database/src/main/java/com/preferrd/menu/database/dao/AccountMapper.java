package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper public interface AccountMapper {
    int deleteByPrimaryKey(String accountid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String accountid);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}
