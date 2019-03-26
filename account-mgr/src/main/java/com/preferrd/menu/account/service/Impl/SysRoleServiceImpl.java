package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysRoleService;
import com.preferrd.menu.database.dao.SysRoleMapper;
import com.preferrd.menu.database.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysRoleServiceImpl.
 *
 * @author liguoyao
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Transactional
    @Override
    public SysRole getSysRole(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Transactional
    @Override
    public int addSysRole(SysRole sysRole) {
        return roleMapper.insert(sysRole);
    }

    @Transactional
    @Override
    public int updateSysRole(SysRole sysRole) {
        return roleMapper.updateByPrimaryKey(sysRole);
    }

    @Transactional
    @Override
    public int deleteSysRole(String roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }
}
