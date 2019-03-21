package com.preferrd.menu.account.handler;

//import com.preferrd.menu.account.model.Account;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.database.model.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
    String deleteAccount(@PathVariable(value = "accountId",
            required = false) String accountId) {
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
        if (null == account.getUserName() && null == account.getCardId() && null == account.getDescription()
                && null == account.getEmail() && null == account.getPassword() && null == account.getPhone()
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

    //退出的时候是get请求，主要是用于退出
    @GetMapping(value = "/login")
    public String login() {
        return "please login!";
    }

    //post登录
    @PostMapping(value = "/login")
    public String login(@RequestBody Map map) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(map.get("username").toString(), map.get("password").toString());
        usernamePasswordToken.setRememberMe(true);
        Session session = subject.getSession();
        //设置过期时间为10小时
        session.setTimeout(36000000);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "login success!";
    }

    //首页
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    //登出
    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }
}
