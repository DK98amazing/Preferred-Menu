package com.preferrd.menu.account.service.Impl;

import com.preferrd.menu.account.service.AuthorityService;
import com.preferrd.menu.database.dao.AuthorityMapper;
import com.preferrd.menu.database.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public Authority getAuthority(String authorityId) {
        return authorityMapper.selectByPrimaryKey(authorityId);
    }
}
