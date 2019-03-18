package com.preferrd.menu.service;

import java.util.Set;

import com.preferrd.menu.dao.bean.Account;

public interface AccountService {
	
	Account findAccountByCardId(String cardId);
	
	Account findAccountById(String accountId);
	
	Set<Account> collecteAccount();
	
	Account registerAccount(Account account);
	
	Account unregisterAccount(Account account);
	
	Account updateAccount(Account account);
}
