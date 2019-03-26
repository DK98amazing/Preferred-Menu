package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.dao.SysUserMapper;
import com.preferrd.menu.database.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysUserServiceImpl.
 *
 * @author liguoyao
 */
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getSysUser(String userId) {
        return null;
    }

    @Override
    public int addSysUser(SysUser sysUser) {
        return 0;
    }

    @Override
    public int updateSysUser(SysUser sysUser) {
        return 0;
    }

    @Override
    public int deleteSysUser(String userId) {
        return 0;
    }
}
