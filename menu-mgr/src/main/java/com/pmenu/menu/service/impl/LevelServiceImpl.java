package com.pmenu.menu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmenu.menu.dao.bean.Level;
import com.pmenu.menu.mapper.LevelMapper;
import com.pmenu.menu.service.LevelService;
@Service
public class LevelServiceImpl implements LevelService{

	@Autowired
	LevelMapper levelMapper;
	
	@Override
	public Integer addLevel(Level level) {
		return levelMapper.add(level);
	}

	@Override
	public Integer removeLevel(String code) {
		return levelMapper.remove(new Level().setCode(Short.valueOf(code)));
	}

	@Override
	public Integer updateLevel(Level level) {
		return levelMapper.update(level);
	}


	@Override
	public Set<Level> listLevel(String key) {
		if(key!=null&&!key.equals("")){
			levelMapper.selectAll();
		}
		Set<Level> levels = new HashSet<Level>();
		levels.add(levelMapper.selectByPrimaryKey(Short.valueOf(key)));
		
		return levels;
	}

}
