package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);

    SysRoleResource selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRoleResource record);

    int updateByPrimaryKey(SysRoleResource record);
}
