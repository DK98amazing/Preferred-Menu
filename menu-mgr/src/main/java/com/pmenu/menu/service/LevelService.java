package com.pmenu.menu.service;

import java.util.Set;

import com.pmenu.menu.dao.bean.Level;

public interface LevelService {
	Integer addLevel(Level level);
	
	Integer removeLevel(String code);
	
	Integer updateLevel(Level level);
	
	Set<Level> listLevel(String key);
}
