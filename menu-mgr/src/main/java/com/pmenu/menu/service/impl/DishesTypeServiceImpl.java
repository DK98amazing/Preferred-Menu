package com.pmenu.menu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmenu.menu.dao.bean.DishesType;
import com.pmenu.menu.mapper.DishesTypeMapper;
import com.pmenu.menu.service.DishesTypeService;
@Service
public class DishesTypeServiceImpl implements DishesTypeService{

	@Autowired
	DishesTypeMapper dishesTypeMapper ;
	
	@Override
	public Integer addDishesType(DishesType dishesType) {
		return dishesTypeMapper.add(dishesType);
	}

	@Override
	public Integer removeDishesType(String key) {
		
		return dishesTypeMapper.remove(new DishesType().setTypeId(key));
	}

	@Override
	public Integer updateDishesType(DishesType dishesType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<DishesType> listDishesType(String key) {
		if(key!=null&&!key.equals("")){
			return null;
		}
		DishesType dishesType = dishesTypeMapper.selectByPrimaryKey(key);
		Set<DishesType> dishesTypes = new HashSet<DishesType>();
		dishesTypes.add(dishesType);
		return dishesTypes;
	}

}
