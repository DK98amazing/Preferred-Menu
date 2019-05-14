package com.preferrd.menu.account.shiro;

import com.preferrd.menu.account.service.SysResourceService;
import com.preferrd.menu.account.service.SysRoleResourceService;
import com.preferrd.menu.account.service.SysRoleService;
import com.preferrd.menu.account.service.SysUserRoleService;
import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.model.SysUser;
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
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleResourceService sysRoleResourceService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userId = (String) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = sysUserService.getSysUser(userId);
        String roleId = sysUserRoleService.getSysUserRole(sysUser.getUserId()).getRoleId();
        String roleName = sysRoleService.getSysRole(roleId).getRole();
        simpleAuthorizationInfo.addRole(roleName);
        String resourceId = sysRoleResourceService.getSysRoleResource(roleId).getResourceId();
        simpleAuthorizationInfo.addStringPermission(sysResourceService.getSysResource(resourceId).getPermission());
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
        String userId = authenticationToken.getPrincipal().toString();
        SysUser sysUser = sysUserService.getSysUser(userId);
        if (sysUser == null) {
            //这里返回后会报出对应异常
            throw new AccountException("User does not exist!");
        } else if (!Arrays.equals(pwd, sysUser.getPassword().toCharArray())) {
            throw new AccountException("The username or password is incorrect!");
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(sysUser.getUserId(), sysUser.getPassword(), getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("account", sysUser);
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
                if (null != obj && obj instanceof SysUser) {
                    SysUser user = (SysUser) obj;
                    if (null != user && userIds.contains(user.getUserId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }
}
