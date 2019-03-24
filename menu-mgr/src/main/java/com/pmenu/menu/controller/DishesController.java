package com.pmenu.menu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmenu.menu.dao.bean.Dishes;
import com.pmenu.menu.service.DishesService;

import commoncom.pmenu.comon.api.PreferredMenuHandler;
import commoncom.pmenu.comon.exception.PerferredMenuException;

@RestController
@RequestMapping("/menu")
public class DishesController implements PreferredMenuHandler{

	@Autowired
	DishesService dishesService ;
	
    @GetMapping("/dishes/{searchKey}") 
    Set<Dishes> searchDishesByKey(@PathVariable(value="searchKey")  String searchKey) {
    	
		return dishesService.listDishes(searchKey);
	}
    
    @GetMapping("/list_dishes") 
    Set<Dishes> searchAllDishes() {
    	
		return dishesService.listDishes("");
	}
    
    @DeleteMapping("/dishes/{dishesId}") 
    Integer deleteDishesById(@PathVariable  String dishesId) throws PerferredMenuException {
    	
		return dishesService.removeDishes(dishesId);
	}
    
    @PostMapping("/dishes") 
    Integer addDishes(@RequestBody Dishes dishes) throws PerferredMenuException {
    	
		return dishesService.addDishes(dishes);
	}
    
    @PutMapping("/dishes") 
    Integer updateDishes(@RequestBody Dishes dishes) throws PerferredMenuException {
    	
		return dishesService.updateDishes(dishes);
	}
}
