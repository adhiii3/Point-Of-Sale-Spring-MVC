package com.pos.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ItemRequestDTO {
	
	private int id;
	
	@NotEmpty(message = "item code cannot be empty")
	private String itemCode;
	
	@Min(value=500, message="Price minimal 500")  
	private Integer price;
	
	@NotEmpty(message = "description cannot be empty")
	private String description;
	
	@NotEmpty(message = "type cannot be empty")
	private String type;

	private boolean isTaxable;

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isTaxable() {
		return isTaxable;
	}

	public void setTaxable(boolean isTaxable) {
		this.isTaxable = isTaxable;
	}
	
}
