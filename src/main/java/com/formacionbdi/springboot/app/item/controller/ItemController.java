package com.formacionbdi.springboot.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.service.impl.ItemServiceImpl;

/**
 * The clas ItemController.
 */
@RestController
public class ItemController {
	
	/**
	 * The service.
	 */
	@Autowired
	@Qualifier(value = "itemServiceImpl")
	private ItemServiceImpl itemService;
	
	@GetMapping(value = "/listar")
	public List<Item> lsitar() {
		return itemService.findAll();
	}
	
	@GetMapping(value = "/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}

}
