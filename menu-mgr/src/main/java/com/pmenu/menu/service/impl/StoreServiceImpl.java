package com.pmenu.menu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmenu.menu.dao.bean.Store;
import com.pmenu.menu.mapper.StoreMapper;
import com.pmenu.menu.service.StoreService;
@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreMapper storeMapper ;
	
	@Override
	public Integer addStore(Store store) {
		return storeMapper.add(store);
	}

	@Override
	public Integer removeStore(String storeId) {
		return storeMapper.remove(new Store().setStoreId(storeId));
	}

	@Override
	public Integer updateStore(Store store) {
		return storeMapper.update(store);
	}

	@Override
	public Set<Store> listStore(String key) {
		if(key!=null&&!key.equals("")){
			return storeMapper.selectAll();
		}
		Set<Store> stores  = new HashSet<Store>();
		stores.add(storeMapper.selectByPrimaryKey(key));
		return stores;
	}


}
