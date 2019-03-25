package com.preferrd.menu.account.shiro;

import com.preferrd.menu.account.service.AccountService;
import com.preferrd.menu.account.service.AuthorityService;
import com.preferrd.menu.account.service.RoleAuthorityKeyService;
import com.preferrd.menu.account.service.RoleService;
import com.preferrd.menu.database.model.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityKeyService roleAuthorityKeyService;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String accountId = (String) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Account account = accountService.getAccountById(accountId);
        String roleId = account.getRoleId();
        String roleName = roleService.getRoleName(roleId).getRoleName();
        simpleAuthorizationInfo.addRole(roleName);
        String authorityId = roleAuthorityKeyService.getRoleAuthorityKey(roleId).getAuthorityId();
        simpleAuthorizationInfo.addStringPermission(authorityService.getAuthority(authorityId).getPermission());
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后再到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        char[] pwd = (char[]) authenticationToken.getCredentials();
        String accountId = authenticationToken.getPrincipal().toString();
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            //这里返回后会报出对应异常
            throw new AccountException("User does not exist!");
        } else if (!Arrays.equals(pwd, account.getPassword().toCharArray())) {
            throw new AccountException("The username or password is incorrect!");
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account.getAccountId(), account.getPassword(), getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("account", account);
            return info;
        }
    }
}
