package com.pmenu.menu.dao.bean;

import commoncom.pmenu.comon.api.Entity;
import lombok.Data;

@Data
public class Store implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8101735681304799685L;
	private String storeId;
	private String location;
	private String name;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return buffer.append("[storeId:").append(storeId).append(",location:").append(location).append(",naem:")
				.append(name).append("]").toString();
	}
}
