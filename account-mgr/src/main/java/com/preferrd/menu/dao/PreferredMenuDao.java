package com.preferrd.menu.dao;

import java.util.List;
import java.util.Set;

public interface PreferredMenuDao<T> {

    T add(T t);

    Integer addList(List<T> tList);

    T remove(T t);

    Integer removeList(List<T> tList);

    T update(String key);

    Integer updateAll();

    T select(String key);

    Set<T> selectAll();
}
