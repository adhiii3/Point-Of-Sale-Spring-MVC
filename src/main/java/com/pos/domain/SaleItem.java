package com.pos.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "sale_item")
public class SaleItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4926647048916021407L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_item_id")
	private int saleItemId;
	
	@Column(name="quantity", nullable=false)
	private int quantity;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;

	public SaleItem() {
	
	}

	public SaleItem(int quantity, Item item) {
		this.quantity = quantity;
		this.item = item;
	}
	
	public int getTotalPrice() {
		return this.item.getPrice() * this.getQuantity();
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return new Item(
				this.item.getId(),
				this.item.getItemCode(),
				this.item.getPrice(),
				this.item.getDescription(),
				this.item.getType(),
				this.item.isTaxable());
	}

	public int getSaleItemId() {
		return saleItemId;
	}

	public void setSaleItemId(int saleItemId) {
		this.saleItemId = saleItemId;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
}
