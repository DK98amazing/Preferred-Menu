package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysUser;

public interface SysUserService {
    SysUser getSysUser(String userId);

    int addSysUser(SysUser sysUser);

    int updateSysUser(SysUser sysUser);

    int deleteSysUser(String userId);
}
