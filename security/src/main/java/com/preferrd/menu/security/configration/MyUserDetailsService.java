package com.preferrd.menu.security.configration;

import com.preferrd.menu.account.service.SysResourceService;
import com.preferrd.menu.account.service.SysRoleResourceService;
import com.preferrd.menu.account.service.SysRoleService;
import com.preferrd.menu.account.service.SysUserRoleService;
import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.model.SysRole;
import com.preferrd.menu.database.model.SysUser;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
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
    private PasswordEncoder passwordEncoder;

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = sysUserService.getSysUser(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        String roleId = sysUserRoleService.getSysUserRole(sysUser.getUserId()).getRoleId();
        String roleName = sysRoleService.getSysRole(roleId).getRole();
        simpleAuthorizationInfo.addRole(roleName);
        String resourceId = sysRoleResourceService.getSysRoleResource(roleId).getResourceId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(sysResourceService.getSysResource(resourceId).getPermission()));

        return new User(sysUser.getUserName(), passwordEncoder.encode(sysUser.getPassword()), authorities);
    }
}
