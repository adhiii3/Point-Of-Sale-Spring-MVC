package com.pos.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.domain.Cashier;
import com.pos.domain.Item;
import com.pos.domain.Payment;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.service.ItemService;
import com.pos.service.SaleService;

@Component
public class ProsesUseCaseSale {
	
	private ItemService itemService;
	
	private SaleService saleService;
	
	private Sale sale;
	
	@Autowired
	public ProsesUseCaseSale(ItemService itemService, SaleService saleService) {
		this.itemService = itemService;
		this.saleService = saleService;
	}
	
	public void createNewSale(Cashier cashier) {
		this.sale = new Sale(cashier);
	}
	
	public void addSaleItem(String itemCode,int quantity){
		Item item = itemService.findItemById(itemCode);
		SaleItem saleItem1 = new SaleItem(quantity,item);
//		saleItem1.setSale(sale);
		this.sale.addSaleItem(saleItem1);
	}
	
	public void makePayment(Payment payment) {
		this.sale.setPayment(payment);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public void finishSale() {
			saleService.save(this.getSale());
	}
	
}
