package com.pos.repository;

import java.util.List;

import com.pos.domain.Item;

public interface ItemRepository {
	public Item findItemById(String itemCode);
	
	public List<Item> findAll();
	
	public void save(Item item);
	
	public void update(String itemCode, Item item);
	
	public void delete(String itemCode);
}
