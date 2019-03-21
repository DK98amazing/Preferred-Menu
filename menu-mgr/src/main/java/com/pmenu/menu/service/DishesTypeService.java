package com.pmenu.menu.service;

import java.util.Set;

import com.pmenu.menu.dao.bean.DishesType;

public interface DishesTypeService {
	Integer addDishesType(DishesType dishesType);
	
	Integer removeDishesType(String key);
	
	Integer updateDishesType(DishesType dishesType);
	
	Set<DishesType> listDishesType(String key);
}
