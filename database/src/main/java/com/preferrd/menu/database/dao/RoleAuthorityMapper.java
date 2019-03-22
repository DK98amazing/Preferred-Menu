package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.RoleAuthority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);
}