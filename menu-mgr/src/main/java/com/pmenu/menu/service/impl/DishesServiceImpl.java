package com.pmenu.menu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmenu.menu.dao.bean.Dishes;
import com.pmenu.menu.mapper.DishesMapper;
import com.pmenu.menu.service.DishesService;

import commoncom.pmenu.comon.constant.ErrorCode;
import commoncom.pmenu.comon.exception.PerferredMenuDaoException;
import commoncom.pmenu.comon.exception.PerferredMenuException;

@Service
public class DishesServiceImpl implements DishesService {

	@Autowired
	DishesMapper dishesMapper;

	@Override
	public Set<Dishes> listDishes(String searchKey) {
		if (searchKey == null || searchKey.equals("")) {
			return dishesMapper.selectAll();
		}
		Dishes dishes = dishesMapper.select(searchKey);
		Set<Dishes> dishesSet = new HashSet<Dishes>();
		dishesSet.add(dishes);
		return dishesSet;
	}
	
	@Transactional
	@Override
	public Integer removeDishes(String dishesId)
			throws PerferredMenuDaoException {
		if (dishesId != null && !dishesId.equals("")) {
			return dishesMapper.remove(new Dishes().setDishesId(dishesId));
		}
		throw new PerferredMenuDaoException(ErrorCode.FAILURE.getCode(),
				ErrorCode.FAILURE.getEn(), ErrorCode.FAILURE.getCn());
	}

	@Transactional
	@Override
	public Integer addDishes(Dishes dishes) throws PerferredMenuDaoException {
		if (dishes != null) {
			return dishesMapper.add(dishes);
		}
		throw new PerferredMenuDaoException(ErrorCode.FAILURE.getCode(),
				ErrorCode.FAILURE.getEn(), ErrorCode.FAILURE.getCn());
	}

	@Transactional
	@Override
	public Integer updateDishes(Dishes dishes) throws PerferredMenuException {
		return dishesMapper.update(dishes);
	}

}
