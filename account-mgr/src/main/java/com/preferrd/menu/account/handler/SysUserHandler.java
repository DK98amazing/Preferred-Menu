package com.preferrd.menu.account.handler;

//import com.preferrd.menu.account.model.Account;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.account.shiro.MyShiroRealm;
import com.preferrd.menu.database.model.SysUser;
import com.prefrred.exception.SelectException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SysUserHandler.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate accountRedisTemplate;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/myindex")
    public ModelAndView toindex(ModelAndView modelAndView) {
        modelAndView.addObject("name", "李国瑶");
        modelAndView.addObject("sex", "男");
        modelAndView.addObject("book", new String[]{"语文", "数学", "英语"});
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/forward")
    ModelAndView home1() {
        ModelAndView mv = new ModelAndView();
        // request url不变
        mv.setViewName("forward:/sysuser/cities");
        return mv;
    }

    @GetMapping("/redirect")
    ModelAndView home2() {
        ModelAndView mv = new ModelAndView();
        // request url改变
        mv.setViewName("redirect:/sysuser/cities");
        return mv;
    }

    @GetMapping("/getSysUser")
    SysUser getUserById(@RequestParam(value = "userId",
            required = false) String userId) {
        return sysUserService.getSysUser(userId);
    }

    @GetMapping("/getSysUser/{userId}")
    String getUserById2(@PathVariable(value = "userId",
            required = false) String userId) {
        LOG.info("sessionId: " + httpServletRequest.getSession().getId());
        SysUser sysUser = null;
//        if (SecurityUtils.getSubject().hasRole("admin") & SecurityUtils.getSubject().isPermitted("all")) {
        LOG.info(userId + " 认证成功");
        if (accountRedisTemplate.hasKey("sysuser: " + userId)) {
            sysUser = (SysUser) accountRedisTemplate.opsForValue().get("sysuser: " + userId);
        } else {
            sysUser = sysUserService.getSysUser(userId);
        }
        if (null == sysUser) {
            throw new SelectException("用户未找到");
        }
        return sysUser.toString();
    }

    @DeleteMapping("deleteSysUser/{userId}")
    String deleteSysUser(@PathVariable(value = "userId",
            required = false) String userId) {
        int result = sysUserService.deleteSysUser(userId);
        if (result == 1) {
            return "userId: " + userId;
        } else {
            throw new IllegalStateException("delete failed");
        }
    }

    @PostMapping("updateSysUser")
    ModelAndView updateSysUser(@RequestBody SysUser sysUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == sysUser.getUserName() && null == sysUser.getCreateTime() && null == sysUser.getUpdateTime()
                && null == sysUser.getEmail() && null == sysUser.getPassword() && null == sysUser.getPhone()
                && null == sysUser.getLastLoginTime() && null == sysUser.getSex() && null == sysUser.getStatus()
                && null == sysUser.getUserId()) {
            modelAndView.addObject("result", 1);
            modelAndView.setViewName("redirect:/sysuser/getSysUser?userId=" + sysUser.getUserId());
        } else {
            int result = sysUserService.updateSysUser(sysUser);
            if (result == 1) {
                modelAndView.addObject("result", result);
                modelAndView.setViewName("redirect:/sysuser/getSysUser?userId=" + sysUser.getUserId());
                DefaultWebSecurityManager securityManager =
                        (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
                MyShiroRealm myShiroRealm = (MyShiroRealm) securityManager.getRealms().iterator().next();
                myShiroRealm.clearAuthorizationByUserId(SecurityUtils.getSubject().getPrincipals());
            } else {
                throw new IllegalStateException("update failed");
            }
        }
        return modelAndView;
    }

    //退出的时候是get请求，主要是用于退出
    @GetMapping(value = "/login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login Page";
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
        session.setTimeout(1000 * 60);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "login success!";
    }

    //首页
    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    //登出
    @GetMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }

    //登出
    @GetMapping(value = "/error")
    public String error() {
        return "error";
    }
}
