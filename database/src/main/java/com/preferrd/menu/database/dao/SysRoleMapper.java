package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}