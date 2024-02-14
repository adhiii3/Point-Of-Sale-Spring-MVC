package com.pos.service;

import java.util.List;

import com.pos.domain.Payment;
import com.pos.domain.Sale;

public interface SaleService {
	public void save(Sale sale);
	public Sale findeBySaleNumber(int saleId);
	public List<Sale> findAll();
}
