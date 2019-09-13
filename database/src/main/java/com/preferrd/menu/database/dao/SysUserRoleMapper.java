package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}