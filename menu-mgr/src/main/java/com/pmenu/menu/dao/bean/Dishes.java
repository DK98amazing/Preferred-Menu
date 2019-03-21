package com.pmenu.menu.dao.bean;

import java.math.BigDecimal;
import java.util.UUID;

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
	private BigDecimal price;
	private String typeId;
	private String ingredient;
	private String tag;
	private String storeId;

	
	public Dishes() {
		this.dishesId  = UUID.randomUUID().toString();
	}
	
	public String getDishesId() {
		return dishesId;
	}



	public Dishes setDishesId(String dishesId) {
		this.dishesId = dishesId.trim();
		return this;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public String getTypeId() {
		return typeId;
	}



	public void setTypeId(String typeId) {
		this.typeId = typeId.trim();
	}



	public String getIngredient() {
		return ingredient;
	}



	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public String getStoreId() {
		return storeId;
	}



	public void setStoreId(String storeId) {
		this.storeId = storeId.trim();
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return buffer.append("[dishesId:").append(dishesId).append(",name:").append(name).append(",price:")
				.append(price).append(",typeId:").append(typeId).append(",ingredient:").append(ingredient)
				.append(",tag:").append(tag).append(",storeId:").append(storeId).append("]").toString();
	}

}
