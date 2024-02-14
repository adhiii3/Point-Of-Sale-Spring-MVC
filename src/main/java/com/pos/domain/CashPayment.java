package com.pos.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cash")
public class CashPayment extends Payment{
	
	@Column(name="cash_in_hand", columnDefinition = "integer default 0")
	private int cashInHand;
	
	public CashPayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CashPayment(int cashInhand, int amount) {
		this.setGeneratePaymentId();
		this.cashInHand = cashInhand;
		this.setAmount(amount);
		this.setType("cash");
	}
	
	public int change() {
		return this.cashInHand - getAmount();
	}

	public int getCashInHand() {
		return cashInHand;
	}

	public void setCashInHand(int cashInHand) {
		this.cashInHand = cashInHand;
	}
	
	
}
