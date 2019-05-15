package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.database.dao.SysUserMapper;
import com.preferrd.menu.database.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    private StringRedisTemplate accountRedisTemplate;

    @Transactional
    //    @Cacheable(value = "userCache",
    //               key = "#userId")
    @Override
    public SysUser getSysUser(String userId) {
        SysUser sysUser = userMapper.selectByPrimaryKey(userId);
        accountRedisTemplate.opsForValue().set(userId, sysUser.toString());
        return sysUser;
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
