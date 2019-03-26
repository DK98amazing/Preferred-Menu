package com.preferrd.menu.account.service;

import com.preferrd.menu.database.model.SysResource;

public interface SysResourceService {
    SysResource getSysResource(String resourceId);

    int addSysResource(SysResource sysResource);

    int updateSysResource(SysResource sysResource);

    int deleteSysResource(String resourceId);
}
