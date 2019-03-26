package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysUserRole;

public interface SysUserRoleService {
    SysUserRole getSysUserRole(String userId);

    int addSysUserRole(SysUserRole sysUserRole);

    int updateSysUserRole(SysUserRole sysUserRole);

    int deleteSysUserRole(String userId, String roleId);
}
