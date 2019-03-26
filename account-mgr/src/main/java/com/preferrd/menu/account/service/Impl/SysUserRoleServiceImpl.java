package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserRoleService;
import com.preferrd.menu.database.dao.SysUserRoleMapper;
import com.preferrd.menu.database.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysUserRoleServiceImpl.
 *
 * @author liguoyao
 */
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public SysUserRole getSysUserRole(String userId) {
        return null;
    }

    @Override
    public int addSysUserRole(SysUserRole sysUserRole) {
        return 0;
    }

    @Override
    public int updateSysUserRole(SysUserRole sysUserRole) {
        return 0;
    }

    @Override
    public int deleteSysUserRole(String userId, String roleId) {
        return 0;
    }
}
