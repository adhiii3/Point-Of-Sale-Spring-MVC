package com.pos.service;

import java.util.List;

import com.pos.domain.Item;
import com.pos.dto.ItemRequestDTO;

public interface ItemService {
	public Item findItemById(String itemCode);
	
	public List<Item> findAll();
	
	public void save(ItemRequestDTO dto);
	
	public void update(String itemCode, ItemRequestDTO dto);
	
	public void delete(String itemCode);
}
