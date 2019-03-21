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
import com.pmenu.menu.dao.bean.Store;
import com.pmenu.menu.service.StoreService;

import commoncom.pmenu.comon.exception.PerferredMenuException;

@RestController
@RequestMapping("/menu")
public class StoreController {
	@Autowired
	StoreService storeService ;
	
    @GetMapping("/store") 
    Set<Store> searchStore(@RequestParam(value = "searchKey",
            required = false) String searchKey) {
    	
		return storeService.listStore(searchKey);
	}
    
    
    @DeleteMapping("/store") 
    Integer deleteStoreById(@RequestParam(value = "storeId",
            required = false) String storeId) throws PerferredMenuException {
    	
		return storeService.removeStore(storeId);
	}
    
    @PostMapping("/store") 
    Integer addStore(@RequestBody Store store) throws PerferredMenuException {
    	
		return storeService.addStore(store);
	}
    
    @PutMapping("/store") 
    Integer updateStore(@RequestBody Store store) throws PerferredMenuException {
    	
		return storeService.updateStore(store);
	}
}
