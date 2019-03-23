package com.preferrd.menu.account.handler;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * NoAuthHandler.
 *
 * @author liguoyao
 */
@RestController
@RequestMapping("/noAuth")
public class NoAuthHandler {
    @Autowired
    private AccountService accountService;

    @PostMapping("addAccount")
    ModelAndView addAccount(@RequestBody Account account) {
        ModelAndView modelAndView = new ModelAndView();
        int result = accountService.addAccount(account);
        if (result == 1) {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("redirect:/account/getAccount?accountId=" + account.getAccountId());
            return modelAndView;
        } else {
            throw new IllegalStateException("insert failed");
        }
    }
}
