package com.preferrd.menu.account.service;


import com.preferrd.menu.database.model.Account;

public interface AccountService {

    Account getAccountById(String id);

    Integer addAccount(Account account);

//    List<Account> getAllAccount();

}
