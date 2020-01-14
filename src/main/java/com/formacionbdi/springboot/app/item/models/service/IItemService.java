package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.item.models.Item;

/**
 * The interface ItemService.
 */
public interface IItemService {
	
	/**
	 * Gets the list items.
	 * @return list items
	 */
	public List<Item> findAll();
	
	/**
	 * Gets the Item.
	 * @return Item
	 */
	public Item findById(Long id, Integer cantidad);

}
