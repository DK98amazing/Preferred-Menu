package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserService;
import com.preferrd.menu.aop.log.annotation.Log;
import com.preferrd.menu.database.dao.SysUserMapper;
import com.preferrd.menu.database.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate accountRedisTemplate;

    @Override
    @Log(value = "getSysUser")
    @Transactional
    @Cacheable(cacheNames = "userCache",
            unless = "#result == null",
            key = "#userId",
            cacheManager = "dbCacheManager")
    public SysUser getSysUser(String userId) {
        SysUser sysUser = userMapper.selectByPrimaryKey(userId);
        //        accountRedisTemplate.opsForValue().set("sysuser: " + userId, sysUser, 1, TimeUnit.HOURS);
        return sysUser;
    }

    @Transactional
    @Override
    @CachePut(cacheNames = "userCache",
            key = "#userId",
            cacheManager = "dbCacheManager")
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
    @CacheEvict(cacheNames = "userCache",
            key = "#userId",
            cacheManager = "dbCacheManager",
            beforeInvocation = false)
    public int deleteSysUser(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }
}
