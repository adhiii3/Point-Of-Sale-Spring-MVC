package com.pos.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6343532452290153588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="item_code", length=15, nullable=false)
	private String itemCode;
	
	@Column(name="price", length=11, nullable=false)
	private int price;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="is_taxable", nullable=false) 
	private boolean isTaxable;
	
	public Item() {
	
	}
	
	public Item(int id,String itemCode, int price, String description, String type, boolean isTaxable) {
		this.id = id;
		this.itemCode = itemCode;
		this.price = price;
		this.description = description;
		this.type = type;
		this.isTaxable = isTaxable;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}
	public int getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(itemCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(itemCode, other.itemCode);
	}
	
	
	
}
