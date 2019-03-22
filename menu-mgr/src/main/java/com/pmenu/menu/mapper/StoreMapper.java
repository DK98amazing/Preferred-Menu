package com.pmenu.menu.mapper;

import com.pmenu.menu.dao.bean.Store;
import commoncom.pmenu.comon.api.PreferredMenuMapper;

public interface StoreMapper extends PreferredMenuMapper<Store>{
    int deleteByPrimaryKey(String storeId);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String storeId);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}