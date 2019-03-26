package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysUserRoleService;
import com.preferrd.menu.database.dao.SysUserRoleMapper;
import com.preferrd.menu.database.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysUserRoleServiceImpl.
 *
 * @author liguoyao
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Transactional
    @Override
    public SysUserRole getSysUserRole(String userId) {
        return userRoleMapper.selectByPrimaryKey(userId);
    }

    @Transactional
    @Override
    public int addSysUserRole(SysUserRole sysUserRole) {
        return userRoleMapper.insert(sysUserRole);
    }

    @Transactional
    @Override
    public int updateSysUserRole(SysUserRole sysUserRole) {
        return userRoleMapper.updateByPrimaryKey(sysUserRole);
    }

    @Transactional
    @Override
    public int deleteSysUserRole(String userId, String roleId) {
        //TODO:写新的方法获取SysUserRole
        SysUserRole sysUserRole = null;
        return userRoleMapper.deleteByPrimaryKey(sysUserRole.getId());
    }
}
