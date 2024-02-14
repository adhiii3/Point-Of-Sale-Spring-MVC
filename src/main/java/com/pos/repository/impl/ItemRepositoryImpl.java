package com.pos.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.domain.Item;
import com.pos.dto.ItemResponseDTO;
import com.pos.repository.ItemRepository;

import org.hibernate.Session;
@Repository
public class ItemRepositoryImpl implements ItemRepository{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public Item findItemById(String itemCode) {
		Session session = sessionFactory.getCurrentSession();
		Item item = null;
		String hqlQuery = "from Item where itemCode= :itemCode";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(hqlQuery);
		query.setParameter("itemCode", itemCode);
		item = (Item) query.getSingleResult();
		return item;	
	}

	@Override
	public List<Item> findAll() {
		List<Item> items = sessionFactory.getCurrentSession()
				.createQuery("from Item")
				.getResultList();
		return items;
	}

	@Override
	public void save(Item item) {
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void update(String itemCode, Item item) {
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	public void delete(String itemCode) {
		sessionFactory.getCurrentSession().createQuery("delete from Item where itemCode = :itemCode")
		  .setParameter("itemCode", itemCode)
		  .executeUpdate();
	}

}
