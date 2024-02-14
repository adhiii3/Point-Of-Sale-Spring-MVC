package com.pos.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pos.domain.Sale;
import com.pos.domain.SaleItem;

@Repository
public class SaleItemRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(SaleItem saleItem) {
		sessionFactory.getCurrentSession().save(saleItem);
	}
}
