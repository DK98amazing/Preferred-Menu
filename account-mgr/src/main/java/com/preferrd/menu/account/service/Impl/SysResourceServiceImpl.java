package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysResourceService;
import com.preferrd.menu.database.dao.SysResourceMapper;
import com.preferrd.menu.database.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysResourceServiceImpl.
 *
 * @author liguoyao
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper resourceMapper;

    @Transactional
    @Override
    public SysResource getSysResource(String resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Transactional
    @Override
    public int addSysResource(SysResource sysResource) {
        return resourceMapper.insert(sysResource);
    }

    @Transactional
    @Override
    public int updateSysResource(SysResource sysResource) {
        return resourceMapper.updateByPrimaryKey(sysResource);
    }

    @Transactional
    @Override
    public int deleteSysResource(String resourceId) {
        return resourceMapper.deleteByPrimaryKey(resourceId);
    }
}
