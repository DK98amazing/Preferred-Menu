package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.RoleAuthorityKeyService;
import com.preferrd.menu.database.dao.RoleAuthorityMapper;
import com.preferrd.menu.database.model.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorityKeyServiceImpl implements RoleAuthorityKeyService {
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public RoleAuthority getRoleAuthorityKey(String roleId) {
        return roleAuthorityMapper.selectByPrimaryKey(roleId);
    }
}
