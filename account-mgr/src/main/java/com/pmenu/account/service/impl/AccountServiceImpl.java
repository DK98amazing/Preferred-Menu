package com.pmenu.account.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmenu.account.dao.bean.Account;
import com.pmenu.account.mapper.AccountMapper;
import com.pmenu.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Set<Account> collecteAccount() {
		Set<Account> accounts = accountMapper.selectAll();
		return accounts == null ? new HashSet<Account>() : accounts;
	}

	@Transactional
	@Override
	public Account registerAccount(Account account) {
		return accountMapper.add(account);
	}

	@Transactional
	@Override
	public Account unregisterAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByCardId(String cardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountById(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

}
