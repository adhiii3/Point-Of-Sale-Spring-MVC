package com.pos.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pos.domain.Payment;
import com.pos.domain.SaleItem;

@Repository
public class PaymentRepository {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(Payment payment) {
		sessionFactory.getCurrentSession().save(payment);
	}
}
