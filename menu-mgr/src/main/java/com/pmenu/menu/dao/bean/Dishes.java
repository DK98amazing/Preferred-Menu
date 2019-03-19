package com.pmenu.menu.dao.bean;

import java.math.BigDecimal;

import commoncom.pmenu.comon.api.Entity;
import lombok.Data;

@Data
public class Dishes implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799170166892341596L;
	private String dishesId;
	private String name;
	private BigDecimal pricel;
	private String typeId;
	private String ingredient;
	private String tag;
	private String storeId;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return buffer.append("[dishesId:").append(dishesId).append(",name:").append(name).append(",pricel:")
				.append(pricel).append(",typeId:").append(typeId).append(",ingredient:").append(ingredient)
				.append(",tag:").append(tag).append(",storeId:").append(storeId).append("]").toString();
	}

}
