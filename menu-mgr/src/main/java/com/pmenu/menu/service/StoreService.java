package com.pmenu.menu.service;

import java.util.Set;

import com.pmenu.menu.dao.bean.Store;

public interface StoreService {
	Integer addStore(Store store);
	
	Integer removeStore(String storeId);
	
	Integer updateStore(Store store);
	
	Set<Store> listStore(String key);
}
