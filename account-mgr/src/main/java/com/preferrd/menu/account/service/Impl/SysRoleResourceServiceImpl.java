package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysRoleResourceService;
import com.preferrd.menu.database.dao.SysRoleResourceMapper;
import com.preferrd.menu.database.model.SysRoleResource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysRoleResourceServiceImpl.
 *
 * @author liguoyao
 */
public class SysRoleResourceServiceImpl implements SysRoleResourceService {
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    public SysRoleResource getSysRoleResource(String roleId) {
        return null;
    }

    @Override
    public int addSysRoleResource(SysRoleResource sysRoleResource) {
        return 0;
    }

    @Override
    public int updateSysRoleResource(SysRoleResource sysRoleResource) {
        return 0;
    }

    @Override
    public int deleteSysRoleResource(String roleId, String resourceId) {
        return 0;
    }
}
