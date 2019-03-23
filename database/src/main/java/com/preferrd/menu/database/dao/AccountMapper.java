package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(@Param("accountId") String accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}
