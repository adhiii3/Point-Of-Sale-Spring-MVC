package com.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.domain.Item;
import com.pos.dto.ItemRequestDTO;
import com.pos.repository.ItemRepository;
import com.pos.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	@Override
	@Transactional
	public Item findItemById(String itemCode) {
		return itemRepository.findItemById(itemCode);
	}

	@Override
	@Transactional
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	@Transactional
	public void save(ItemRequestDTO dto) {
		Item item = new Item();
		
		item.setItemCode(dto.getItemCode());
		item.setPrice(dto.getPrice());
		item.setDescription(dto.getDescription());
		item.setType(dto.getType());
		item.setTaxable(dto.isTaxable());
		
		itemRepository.save(item);
	}

	@Override
	@Transactional
	public void update(String itemCode, ItemRequestDTO dto) {
		
		Item item = new Item();
		item.setId(dto.getId());
		item.setItemCode(dto.getItemCode());
		item.setPrice(dto.getPrice());
		item.setDescription(dto.getDescription());
		item.setType(dto.getType());
		item.setTaxable(dto.isTaxable());
		
		itemRepository.update(itemCode, item);
	}

	@Override
	@Transactional
	public void delete(String itemCode) {
		
		itemRepository.delete(itemCode);
	}

}
