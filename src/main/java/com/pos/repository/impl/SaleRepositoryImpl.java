package com.pos.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.domain.Sale;
import com.pos.repository.SaleRepository;

@Repository
public class SaleRepositoryImpl implements SaleRepository{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Sale sale) {
		sessionFactory.getCurrentSession().save(sale);
	}

	@Override
	public Sale findeBySaleNumber(int saleId) {
		Sale s = sessionFactory.getCurrentSession().find(Sale.class, saleId); 
		return s;
	}

	@Override
	public List<Sale> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Sale").getResultList();
	}  
	
}
