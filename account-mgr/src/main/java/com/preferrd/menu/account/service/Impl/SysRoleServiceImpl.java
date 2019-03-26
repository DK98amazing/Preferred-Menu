package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysRoleService;
import com.preferrd.menu.database.dao.SysRoleMapper;
import com.preferrd.menu.database.model.SysRole;
import com.preferrd.menu.database.model.SysRoleResource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysRoleServiceImpl.
 *
 * @author liguoyao
 */
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public SysRole getSysRole(String roleId) {
        return null;
    }

    @Override
    public int addSysRole(SysRoleResource sysRoleResource) {
        return 0;
    }

    @Override
    public int updateSysRole(SysRoleResource sysRoleResource) {
        return 0;
    }

    @Override
    public int deleteSysRole(String roleId) {
        return 0;
    }
}
