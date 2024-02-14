package com.pos.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "sale")
public class Sale implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6977939598110218414L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="sale_number", nullable=false)
	private String salesNumber;
	
	@Column(name = "transaksi_date", nullable=false)
	private LocalDate transDate;
	
	@OneToOne
	@JoinColumn(name="cashier_id", referencedColumnName = "nip")
//	@Transient
	private Cashier cashier;
	
//	@Column(name = "total_tax", nullable=false)
//	private int totalTax;
	
//	@OneToMany
//	@Cascade(CascadeType.ALL)
//	@JoinColumn(name="sale_id", referencedColumnName = "sales_number")

	@OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SaleItem> saleItem = new ArrayList<>();

	@OneToOne
	@JoinColumn(name="payment_id", referencedColumnName="id")
	@Cascade(CascadeType.ALL)
//	@Transient
	private Payment payment;
	
	public Sale() {
	}
	
	public Sale(Cashier cashier) {
		super();
		this.salesNumber = UUID.randomUUID().toString();
		this.transDate = transDate.now();
		this.cashier = cashier;
	}

	public void setSalesNumber(String salesNumber) {
		this.salesNumber = salesNumber;
	}
	
	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}
	
	public void deleteSaleItem(String itemCode) {
		if(!this.saleItem.isEmpty()) {
			for(int i = 0; i < this.saleItem.size(); i++) {
				if(this.saleItem.get(i).getItem().getItemCode().equals(itemCode)) {
					this.saleItem.remove(i);
				}
			}
		}
	}
	
	public void deleteSaleItemsAll() {
		this.saleItem.clear();
	}
	
	
	public void addSaleItem(SaleItem saleItem) {
		boolean isGetting = true;
		if(!this.saleItem.isEmpty()) {
			for(int i = 0; i < this.saleItem.size(); i++) {
				if(saleItem.getItem().getItemCode().equals(this.saleItem.get(i).getItem().getItemCode())) {
					this.saleItem.get(i).setQuantity((saleItem.getQuantity() + this.saleItem.get(i).getQuantity()));
					isGetting = false;
				}
			}
				if(isGetting) {
					this.saleItem.add(saleItem);
					return;
				}
		}else {
			this.saleItem.add(saleItem);
		}
	}
	
	public List<SaleItem> getSaleItem() {
		List<SaleItem> saleItems = this.saleItem.stream().collect(Collectors.toList());
		return saleItems;
	}
	
	public int getTotalPrice() {
		int result = 0;
		for(SaleItem saleItem : this.saleItem) {
			result += saleItem.getTotalPrice();
		}
		return result;
	}
	
	public int getTotalTax() {
		int result = 0;
		for(SaleItem s: this.saleItem) {
			if(s.getItem().isTaxable()) {
				result += s.getItem().getPrice() * Tax.TAX * s.getQuantity();
			}
		}
		return result;
	}
	
	public int getTotalPricePlusTotalTax() {
		return getTotalTax() + getTotalPrice();
	}

	public String getSalesNumber() {
		return salesNumber;
	}

	public LocalDate getTransDate() {
		return transDate;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Cashier getCashier() {
		return new Cashier(
				this.cashier.getNip(),
				this.cashier.getName());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

//	public void setTotalTax(int totalTax) {
//		this.totalTax = totalTax;
//	}

	public void setSaleItem(List<SaleItem> saleItem) {
		this.saleItem = saleItem;
	}
	
	
	
}
