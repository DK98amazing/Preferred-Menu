package com.pmenu.menu.mapper;

import com.pmenu.menu.dao.bean.DishesType;
import commoncom.pmenu.comon.api.PreferredMenuMapper;

public interface DishesTypeMapper extends PreferredMenuMapper<DishesType>{
    int deleteByPrimaryKey(String typeId);

    int insert(DishesType record);

    int insertSelective(DishesType record);

    DishesType selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(DishesType record);

    int updateByPrimaryKey(DishesType record);
}