package com.preferrd.menu.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AccountConsumer.
 *
 * @author liguoyao
 */
@RestController
@RequestMapping(value = "dubbo")
public class AccountConsumer {
    @Reference
    private AccountService accountService;

    @GetMapping(value = "/getAccount/{accountId}")
    private Account getAccount(@PathVariable(value = "accountId") String accountId) {
        return accountService.getAccountById(accountId);
    }
}
