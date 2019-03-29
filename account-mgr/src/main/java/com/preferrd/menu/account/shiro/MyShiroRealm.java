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
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger LOG = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityKeyService roleAuthorityKeyService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

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
            SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(account.getAccountId(), account.getPassword(), getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("account", account);
            return info;
        }
    }

    public void clearAuthorizationByUserId(List<String> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            realm.clearCachedAuthorizationInfo(collection);
        }
        LOG.info("[用户权限缓存更新成功]");
    }

    private List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds) {
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户id
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof Account) {
                    Account user = (Account) obj;
                    if (null != user && userIds.contains(user.getAccountId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }
}
