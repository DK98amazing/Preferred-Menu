package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.RoleService;
import com.preferrd.menu.database.dao.AccountMapper;
import com.preferrd.menu.database.dao.RoleMapper;
import com.preferrd.menu.database.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleName(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
