package com.pmenu.menu.service;

import java.util.Set;

import com.pmenu.menu.dao.bean.Dishes;

import commoncom.pmenu.comon.exception.PerferredMenuException;

public interface DishesService {
	Set<Dishes> listDishes(String searchKey);
	
	Integer removeDishes(String dishesId) throws PerferredMenuException;
	
	Integer addDishes(Dishes dishes) throws PerferredMenuException;
	
	Integer updateDishes(Dishes dishes) throws PerferredMenuException;
	
}
