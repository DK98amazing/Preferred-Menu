package com.pmenu.menu.mapper;

import com.pmenu.menu.dao.bean.Level;
import commoncom.pmenu.comon.api.PreferredMenuMapper;

public interface LevelMapper extends PreferredMenuMapper<Level>{
    int deleteByPrimaryKey(Short code);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Short code);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}