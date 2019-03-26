package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.RoleAuthorityKeyService;
import com.preferrd.menu.database.dao.RoleAuthorityMapper;
import com.preferrd.menu.database.model.RoleAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleAuthorityKeyServiceImpl implements RoleAuthorityKeyService {
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Transactional
    @Override
    public RoleAuthority getRoleAuthorityKey(String roleId) {
        return roleAuthorityMapper.selectByPrimaryKey(roleId);
    }
}
