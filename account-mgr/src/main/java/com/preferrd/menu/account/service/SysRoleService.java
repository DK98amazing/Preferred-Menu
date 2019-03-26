package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysRole;
import com.preferrd.menu.database.model.SysRoleResource;

public interface SysRoleService {
    SysRole getSysRole(String roleId);

    int addSysRole(SysRoleResource sysRoleResource);

    int updateSysRole(SysRoleResource sysRoleResource);

    int deleteSysRole(String roleId);
}
