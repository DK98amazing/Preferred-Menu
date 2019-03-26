package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.SysResourceService;
import com.preferrd.menu.database.dao.SysResourceMapper;
import com.preferrd.menu.database.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SysResourceServiceImpl.
 *
 * @author liguoyao
 */
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper resourceMapper;

    @Override
    public SysResource getSysResource(String resourceId) {
        return null;
    }

    @Override
    public int addSysResource(SysResource sysResource) {
        return 0;
    }

    @Override
    public int updateSysResource(SysResource sysResource) {
        return 0;
    }

    @Override
    public int deleteSysResource(String resourceId) {
        return 0;
    }
}
