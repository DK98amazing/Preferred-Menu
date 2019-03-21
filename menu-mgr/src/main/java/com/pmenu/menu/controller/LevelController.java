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
import com.pmenu.menu.dao.bean.Level;
import com.pmenu.menu.service.LevelService;

import commoncom.pmenu.comon.exception.PerferredMenuException;

@RestController
@RequestMapping("/menu")
public class LevelController {
	@Autowired
	LevelService levelService ;
	
    @GetMapping("/level") 
    Set<Level> searchLevel(@RequestParam(value = "searchKey",
            required = false) String searchKey) {
    	
		return levelService.listLevel(searchKey);
	}
    
    
    @DeleteMapping("/level")
    Integer deleteLevelByCode(@RequestParam(value = "code",
            required = false) String code) throws PerferredMenuException {
    	
		return levelService.removeLevel(code);
	}
    
    @PostMapping("/level")
    Integer addLevel(@RequestBody Level level) throws PerferredMenuException {
    	
		return levelService.addLevel(level);
	}
    
    @PutMapping("/level")
    Integer updateLevel(@RequestBody Level level) throws PerferredMenuException {
    	
		return levelService.updateLevel(level);
	}
}
