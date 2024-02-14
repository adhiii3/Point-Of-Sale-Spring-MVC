package com.pos.repository;

import java.util.List;

import com.pos.domain.Sale;


public interface SaleRepository {
	public void save(Sale sale);
	public Sale findeBySaleNumber(int saleId);
	public List<Sale> findAll();
}
