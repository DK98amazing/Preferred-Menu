package com.pmenu.menu.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pmenu.menu.dao.bean.Dishes;

import commoncom.pmenu.comon.api.PreferredMenuMapper;
@Mapper
public interface DishesMapper extends PreferredMenuMapper<Dishes>{

}
