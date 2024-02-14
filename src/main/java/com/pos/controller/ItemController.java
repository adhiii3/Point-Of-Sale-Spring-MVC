package com.pos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pos.domain.Item;
import com.pos.dto.ItemRequestDTO;
import com.pos.dto.ItemResponseDTO;
import com.pos.service.ItemService;

@Controller
public class ItemController {

	private ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String listItems(Model model) {
		model.addAttribute("item",new ItemResponseDTO());
		model.addAttribute("items",itemService.findAll());
		model.addAttribute("page", "item-list");
		return "dashboard";
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute("item") ItemRequestDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			return "item-list";
		}
		itemService.save(dto);		
		return "redirect:/items";
	}
	
	@RequestMapping(value = "/items/edit/{itemCode}", method = RequestMethod.GET)
	public String formEditItem(@PathVariable("itemCode") String itemCode, Model model) {
		model.addAttribute("item",itemService.findItemById(itemCode));
		model.addAttribute("page", "item-edit");
		return "dashboard";
	}
	
	@RequestMapping(value = "/items/edit/{itemCode}", method = RequestMethod.POST)
	public String EditItem(@PathVariable("itemCode") String itemCode, @Valid @ModelAttribute("item") ItemRequestDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			return "item-list";
		}
		itemService.update(itemCode, dto);
		return "redirect:/items";
	}
	
	@RequestMapping(value = "/item/delete/{itemCode}", method = RequestMethod.GET)
	public String EditItem(@PathVariable("itemCode") String itemCode) {
		itemService.delete(itemCode);
		return "redirect:/items";
	}
	
}
