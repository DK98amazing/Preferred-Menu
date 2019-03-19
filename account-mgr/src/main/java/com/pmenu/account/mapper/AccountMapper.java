package com.pmenu.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pmenu.account.dao.bean.Account;

import commoncom.pmenu.comon.api.PreferredMenuMapper;

@Mapper
public interface AccountMapper extends PreferredMenuMapper<Account>{

}	
