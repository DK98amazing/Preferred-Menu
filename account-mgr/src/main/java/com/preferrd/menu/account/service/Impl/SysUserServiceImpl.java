package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.dao.SysUserMapper;
import com.preferrd.menu.database.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysUserServiceImpl.
 *
 * @author liguoyao
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Transactional
    @Cacheable(value = "usercache",
               key = "#userId")
    @Override
    public SysUser getSysUser(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    @Override
    public int addSysUser(SysUser sysUser) {
        return userMapper.insert(sysUser);
    }

    @Transactional
    @Override
    public int updateSysUser(SysUser sysUser) {
        return userMapper.updateByPrimaryKey(sysUser);
    }

    @Transactional
    @Override
    public int deleteSysUser(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }
}
