package com.pos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pos.domain.CashPayment;
import com.pos.domain.Cashier;
import com.pos.domain.Item;
import com.pos.domain.Sale;
import com.pos.domain.SaleItem;
import com.pos.domain.Payment;
import com.pos.domain.QrisPayment;
import com.pos.dto.ItemRequestDTO;
import com.pos.dto.ItemResponseDTO;
import com.pos.dto.PembayaranRequestDTO;
import com.pos.dto.SaleItemRequestDTO;
import com.pos.repository.impl.PaymentRepository;
import com.pos.repository.impl.SaleItemRepository;
import com.pos.service.ItemService;
import com.pos.service.SaleService;
import com.pos.usecase.ProsesUseCaseSale;
import com.pos.usecase.ShoppingCart;

@Controller
@SessionAttributes("shoppingCart")
public class SaleController {

	private SaleService saleService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	 @ModelAttribute("shoppingCart")
	 public ShoppingCart shoppingCart() {
        return this.shoppingCart;
	 }
	
	 @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	 public String sale(Model model) {
		 	model.addAttribute("page", "home");
			return "dashboard";
	 }
	 
	 
	 @RequestMapping(value = "/sale", method = RequestMethod.GET)
	 public String sale(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart,Model model) {
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("cart",shoppingCart);
		model.addAttribute("page", "view-sale");
		return "dashboard";
	 }

	@RequestMapping(value = "/sale/form-add-to-cart/{itemCode}", method = RequestMethod.GET)
	public String viewFormSaleItem(@PathVariable("itemCode") String itemCode, final Model model) {
		Item item = itemService.findItemById(itemCode);
		SaleItemRequestDTO dto = new SaleItemRequestDTO();

		dto.setId(item.getId());
		dto.setItemCode(item.getItemCode());
		dto.setDescription(item.getDescription());
		dto.setPrice(item.getPrice());
		dto.setTaxable(item.isTaxable());
		dto.setType(item.getType());
		
		model.addAttribute("page","form-add-to-cart");
		model.addAttribute("item", dto);
		return "dashboard";
	}
	
	@RequestMapping(value = "/sale/add-cart-proses", method = RequestMethod.POST)
	public String prosesAddToCart
			(@Valid @ModelAttribute("item") SaleItemRequestDTO dto,
			 BindingResult result,
			 @ModelAttribute ShoppingCart shoppingCart,
			 final Model model) {

		if (result.hasErrors()) {
			model.addAttribute("page","form-add-to-cart");
			return "dashboard";
		}

		if (shoppingCart.getSale() != null) {
			shoppingCart.addSaleItem(dto.getItemCode(), dto.getQuantity());
			model.addAttribute("cart", shoppingCart);
		} else {
			shoppingCart.createNewSale(new Cashier("C-1","IRA"));
			shoppingCart.addSaleItem(dto.getItemCode(), dto.getQuantity());
			model.addAttribute("cart", shoppingCart);
		}
		
		return "redirect:/sale";
	}	
	
	@RequestMapping(value = "sale/delete-saleitem/{itemCode}", method = RequestMethod.GET)
	public String deleteSaleItem(@PathVariable("itemCode") String itemCode,
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
		
		shoppingCart.getSale().deleteSaleItem(itemCode);
		return "redirect:/sale";
	}
	
	@RequestMapping(value = "/pembayaran", method = RequestMethod.GET)
	public String salePembayaran(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart, Model model) {
		
		model.addAttribute("pembayaran",new PembayaranRequestDTO());
		model.addAttribute("cart", shoppingCart);
		model.addAttribute("page","pembayaran");
		return "dashboard";
	}
	
	@RequestMapping(value = "/pembayaran", method = RequestMethod.POST)
	public String salePembayaran(
			 @ModelAttribute("pembayaran") PembayaranRequestDTO dto,
			 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
			 Model model) {
		
		Payment payment;
		if("cash".equals(dto.getType())) {
			payment = new CashPayment(Integer.parseInt(dto.getCashInHand()),shoppingCart.getSale().getTotalPricePlusTotalTax());
		}else {
			payment = new QrisPayment(shoppingCart.getSale().getTotalPricePlusTotalTax());
		}
		
		shoppingCart.makePayment(payment);
		shoppingCart.finishSale();
		shoppingCart.clearSale();
		return "redirect:/sale";
	}
	

	@RequestMapping(value = "/history-transaksi", method = RequestMethod.GET)
	public String historyTransaksi(Model model) {
		List<Sale> sales = saleService.findAll();
		model.addAttribute("historyTransaksi", sales);
		model.addAttribute("page", "history-transaksi");
		return "dashboard";
	}
	
	@RequestMapping(value = "/history-transaksi/detail/{saleId}", method = RequestMethod.GET)
	public String historyTransaksiDetail(@PathVariable("saleId") String saleId, Model model) {
		
		Sale sale = saleService.findeBySaleNumber(Integer.parseInt(saleId));
		model.addAttribute("sales", sale);
		model.addAttribute("page", "history-transaksi-detail");
		return "dashboard";
	}

}
