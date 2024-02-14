package com.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.domain.Cashier;
import com.pos.domain.Item;
import com.pos.domain.Payment;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.repository.ItemRepository;
import com.pos.repository.SaleRepository;
import com.pos.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{
	
	private Sale sale;
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Override
	@Transactional
	public void save(Sale sale) {
		List<SaleItem> s = sale.getSaleItem();
		saleRepository.save(sale);
	}

	@Override
	@Transactional
	public Sale findeBySaleNumber(int saleId) {
		// TODO Auto-generated method stub
		return saleRepository.findeBySaleNumber(saleId);
	}

	@Override
	@Transactional
	public List<Sale> findAll() {
		return saleRepository.findAll();
	}
	
}
