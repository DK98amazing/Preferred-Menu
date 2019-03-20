package com.preferrd.menu.account.handler;

//import com.preferrd.menu.account.model.Account;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@RestController @RequestMapping("/rest") public class AccountHandler {
    @Autowired private AccountService accountService;

    @GetMapping("/forward") ModelAndView home1() {
        ModelAndView mv = new ModelAndView();
        // request url不变
        mv.setViewName("forward:/rest/cities");
        return mv;
    }

    @GetMapping("/redirect") ModelAndView home2() {
        ModelAndView mv = new ModelAndView();
        // request url改变
        mv.setViewName("redirect:/rest/cities");
        return mv;
    }

    @GetMapping("/getUser") List<Account> getUserById(@RequestParam(value = "accountId",
                                                                    required = false) String accountId) {
        if (null == accountId) {
            accountId = "1";
        }
        return Arrays.asList(accountService.getAccountById(accountId));
    }
}
