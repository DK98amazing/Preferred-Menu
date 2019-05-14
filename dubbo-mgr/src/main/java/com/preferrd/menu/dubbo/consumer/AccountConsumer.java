package com.preferrd.menu.dubbo.consumer;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    //    @Reference(interfaceClass = AccountService.class,
    //               version = "1.0.1",
    //               application = "${dubbo.application.id}",
    //               url = "dubbo://localhost:12345")
    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/getUser/{userId}")
    private SysUser getUser(@PathVariable(value = "userId") String userId) {
        return sysUserService.getSysUser(userId);
    }
}
