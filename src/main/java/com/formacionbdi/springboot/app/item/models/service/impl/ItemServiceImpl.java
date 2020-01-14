package com.formacionbdi.springboot.app.item.models.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.service.IItemService;

/**
 * The class ItemServiceImpl.
 */
@Service(value = "itemServiceImpl")
public class ItemServiceImpl implements IItemService {

	/**
	 * The variable LOG.
	 */
	private static final Log LOG = LogFactory.getLog(ItemServiceImpl.class);

	/**
	 * The clienteRest.
	 */
	@Autowired
	private RestTemplate clienteRest;

	/**
	 * Gets the list items.
	 */
	@Override
	public List<Item> findAll() {
		LOG.info("Method: findAll().");
		return null;
	}

	/**
	 * Gets the item.
	 */
	@Override
	public Item findById(Long id, Integer cantidad) {
		LOG.info("Method: findById(). Params-Value: " + id + ", " + cantidad);
		return null;
	}

}
