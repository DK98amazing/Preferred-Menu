package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.dao.AccountMapper;
import com.preferrd.menu.database.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@com.alibaba.dubbo.config.annotation.Service(interfaceClass = AccountService.class,
//                                             version = "1.0.1",
//                                             application = "${dubbo.application.id}",
//                                             protocol = "${dubbo.protocol.id}",
//                                             registry = "${dubbo.registry.id}")
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    @Override
    public Account getAccountById(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Integer addAccount(Account account) {
        return accountMapper.insert(account);
    }

    @Transactional
    @Override
    public Integer deleteAccount(String id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Integer updateAccount(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }
}
