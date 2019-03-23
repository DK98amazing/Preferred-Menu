package com.preferrd.menu.account.service;


import com.preferrd.menu.database.model.Account;

import java.util.List;

public interface AccountService {

    Account getAccountById(String id);

    Integer addAccount(Account account);

    Integer deleteAccount(String id);

    Integer updateAccount(Account account);
}
