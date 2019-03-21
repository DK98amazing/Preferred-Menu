package com.preferrd.menu.account.handler;

//import com.preferrd.menu.account.model.Account;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
    private static final Logger LOG = LoggerFactory.getLogger(AccountHandler.class);
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
        LOG.info(accountId);
        return accountService.getAccountById(accountId);
    }

    @PostMapping("addAccount")
    ModelAndView addAccount(@RequestBody Account account) {
        ModelAndView modelAndView = new ModelAndView();
        int result = accountService.addAccount(account);
        if (result == 1) {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("redirect:/rest/getAccount?accountId=" + account.getAccountId());
            return modelAndView;
        } else {
            throw new IllegalStateException("insert failed");
        }
    }

    @DeleteMapping("deleteAccount/{accountId}")
    String deleteAccount(@PathVariable(value = "accountId", required = false) String accountId) {
        int result = accountService.deleteAccount(accountId);
        if (result == 1) {
            return "accountId: " + accountId;
        } else {
            throw new IllegalStateException("delete failed");
        }
    }

    @PostMapping("updateAccount")
    ModelAndView updateAccount(@RequestBody Account account) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == account.getUserName() && null == account.getCardId()
                && null == account.getDescription() && null == account.getEmail()
                && null == account.getPassword() && null == account.getPhone()
                && null == account.getRealName()) {
            modelAndView.addObject("result", 1);
            modelAndView.setViewName("redirect:/rest/getAccount?accountId=" + account.getAccountId());
            return modelAndView;
        } else {
            int result = accountService.updateAccount(account);
            if (result == 1) {
                modelAndView.addObject("result", result);
                modelAndView.setViewName("redirect:/rest/getAccount?accountId=" + account.getAccountId());
                return modelAndView;
            } else {
                throw new IllegalStateException("update failed");
            }
        }
    }
}
