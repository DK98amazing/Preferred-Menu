package com.preferrd.menu.service.Impl;

import com.preferrd.menu.dao.AccountDao;
import com.preferrd.menu.model.Account;
import com.preferrd.menu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Transactional
	@Override
	public Account getNameById(String id) {
		return accountDao.select(id);
	}

	@Transactional
	@Override
	public Integer addUser(Account account) {
		return accountDao.add(account);
	}
}
