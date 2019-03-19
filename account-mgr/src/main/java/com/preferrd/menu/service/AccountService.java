package com.preferrd.menu.service;

import com.preferrd.menu.model.Account;

public interface AccountService {

    Account getNameById(String id);

    void addUser(Account account);

}
