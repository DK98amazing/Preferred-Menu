package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysRoleResourceService;
import com.preferrd.menu.database.dao.SysRoleResourceMapper;
import com.preferrd.menu.database.model.SysRoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysRoleResourceServiceImpl.
 *
 * @author liguoyao
 */
@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Transactional
    @Override
    public SysRoleResource getSysRoleResource(String roleId) {
        return roleResourceMapper.selectByPrimaryKey(roleId);
    }

    @Transactional
    @Override
    public int addSysRoleResource(SysRoleResource sysRoleResource) {
        return roleResourceMapper.insert(sysRoleResource);
    }

    @Transactional
    @Override
    public int updateSysRoleResource(SysRoleResource sysRoleResource) {
        return roleResourceMapper.updateByPrimaryKey(sysRoleResource);
    }

    @Transactional
    @Override
    public int deleteSysRoleResource(String roleId, String resourceId) {
        //TODO:写新的方法获取到id.
        SysRoleResource sysRoleResource = null;
        return roleResourceMapper.deleteByPrimaryKey(sysRoleResource.getId());
    }
}
