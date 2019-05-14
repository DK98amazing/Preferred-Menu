package com.preferrd.menu.account.handler;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.model.SysUser;
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
    private SysUserService sysUserService;

    @PostMapping("addUser")
    ModelAndView addUser(@RequestBody SysUser sysUser) {
        ModelAndView modelAndView = new ModelAndView();
        int result = sysUserService.addSysUser(sysUser);
        if (result == 1) {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("redirect:/account/getAccount?accountId=" + sysUser.getUserId());
            return modelAndView;
        } else {
            throw new IllegalStateException("insert failed");
        }
    }
}
