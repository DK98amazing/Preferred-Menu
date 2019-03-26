package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysRoleResource;

public interface SysRoleResourceService {
    SysRoleResource getSysRoleResource(String roleId);

    int addSysRoleResource(SysRoleResource sysRoleResource);

    int updateSysRoleResource(SysRoleResource sysRoleResource);

    int deleteSysRoleResource(String roleId, String resourceId);
}
