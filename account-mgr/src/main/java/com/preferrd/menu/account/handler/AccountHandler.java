package com.preferrd.menu.account.handler;

//import com.preferrd.menu.account.model.Account;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@RestController
@RequestMapping("/rest")
public class AccountHandler {
    @Autowired
    private AccountService accountService;

    @GetMapping("/forward")
    ModelAndView home1() {
        ModelAndView mv = new ModelAndView();
        // request url不变
        mv.setViewName("forward:/rest/cities");
        return mv;
    }

    @GetMapping("/redirect")
    ModelAndView home2() {
        ModelAndView mv = new ModelAndView();
        // request url改变
        mv.setViewName("redirect:/rest/cities");
        return mv;
    }

    @GetMapping("/getAccount")
    List<Account> getUserById(@RequestParam(value = "accountId",
            required = false) String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/getAccount/{accountId}")
    List<Account> getUserById2(@PathVariable(value = "accountId",
            required = false) String accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping("addAccount")
    Integer addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @DeleteMapping("deleteAccount/{accountId}")
    Integer deleteAccount(@PathVariable(value = "accountId", required = false) String accountId) {
        return accountService.deleteAccount(accountId);
    }
}
