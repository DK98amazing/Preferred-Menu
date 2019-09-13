package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.SysResource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysResourceMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(String resourceId);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}