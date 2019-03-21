package com.pmenu.menu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pmenu.menu.dao.bean.DishesType;
import com.pmenu.menu.service.DishesTypeService;

import commoncom.pmenu.comon.api.PreferredMenuHandler;
import commoncom.pmenu.comon.exception.PerferredMenuException;
@RestController
@RequestMapping("/menu")
public class DishesTypeController  implements PreferredMenuHandler{
	@Autowired
	DishesTypeService dishesTypeService ;
	
    @GetMapping("/dishesType") 
    Set<DishesType> searchDishesType(@RequestParam(value = "searchKey",
            required = false) String searchKey) {
    	
		return dishesTypeService.listDishesType(searchKey);
	}
    
    
    @DeleteMapping("/dishesType") 
    Integer deleteDishesById(@RequestParam(value = "typeId",
            required = false) String typeId) throws PerferredMenuException {
    	
		return dishesTypeService.removeDishesType(typeId);
	}
    
    @PostMapping("/dishesType") 
    Integer addDishes(@RequestBody DishesType dishesType) throws PerferredMenuException {
    	
		return dishesTypeService.addDishesType(dishesType);
	}
    
    @PutMapping("/dishesType") 
    Integer updateDishesType(@RequestBody DishesType dishesType) throws PerferredMenuException {
    	
		return dishesTypeService.updateDishesType(dishesType);
	}
}
