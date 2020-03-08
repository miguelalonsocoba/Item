package com.formacionbdi.springboot.app.item.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.item.models.Item;

/**
 * The interface ItemService.
 */
public interface IItemService {

	/**
	 * Gets the list items.
	 * 
	 * @return list items
	 */
	public List<Item> findAll();

	/**
	 * Gets the Item.
	 * 
	 * @return Item
	 */
	public Item findById(Long id, Integer cantidad);

	/**
	 * Save the producto
	 * 
	 * @param producto
	 * @return Producto
	 */
	public Producto save(Producto producto);

	/**
	 * Update Producto.
	 * 
	 * @param producto
	 * @param idLong
	 * @return Producto
	 */
	public Producto update(Producto producto, Long id);

	/**
	 * Delete Producto
	 * 
	 * @param id
	 */
	public void delete(Long id);

}
