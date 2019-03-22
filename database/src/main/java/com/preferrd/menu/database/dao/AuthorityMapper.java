package com.preferrd.menu.database.dao;

import com.preferrd.menu.database.model.Authority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}