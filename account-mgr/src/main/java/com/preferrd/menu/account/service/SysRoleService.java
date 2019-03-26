package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysRole;

public interface SysRoleService {
    SysRole getSysRole(String roleId);

    int addSysRole(SysRole sysRole);

    int updateSysRole(SysRole sysRole);

    int deleteSysRole(String roleId);
}
