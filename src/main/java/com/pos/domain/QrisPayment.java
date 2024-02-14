package com.pos.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Qris")
public class QrisPayment extends Payment{

	
	
	public QrisPayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QrisPayment(int amount) {
		this.setGeneratePaymentId();
		this.setAmount(amount);
		this.setType("qris");
	}
	
}
