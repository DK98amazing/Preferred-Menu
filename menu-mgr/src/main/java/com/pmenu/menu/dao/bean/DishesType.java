package com.pmenu.menu.dao.bean;

import commoncom.pmenu.comon.api.Entity;
import lombok.Data;

@Data
public class DishesType implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7527929844507584185L;
	private String typeId;
	private String typeName;
	private String description;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return buffer.append("[typeId:").append(typeId).append(",typeName:").append(typeName).append(",description:")
				.append(description).append("]").toString();
	}
}
