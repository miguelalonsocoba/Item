package com.formacionbdi.springboot.app.item.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * The clas ItemController.
 */
@RestController
public class ItemController {
	
	/**
	 * Constant LOG.
	 */
	private static final Log LOG = LogFactory.getLog(ItemController.class);

	/**
	 * The service.
	 */
//	@Qualifier(value = "serviceRestTemplate")
	@Autowired
	@Qualifier(value = "serviceFeign")
	private IItemService itemService;

	@HystrixCommand(fallbackMethod = "methodAlternativoListar")
	@GetMapping(value = "/listar")
	public List<Item> lsitar() {
		LOG.info("Method: listar()");
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping(value = "/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		LOG.info("Method: detalle()");
		return itemService.findById(id, cantidad);
	}

	/**
	 * Metodo de configuracion de Hystrix para que procese alguna exception en caso de error.
	 * @param id
	 * @param cantidad
	 * @return Object Item
	 */
	public Item metodoAlternativo(Long id, Integer cantidad) {
		LOG.info("Method: metodoAlternativo()");
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		
		return item;
	}
	
	/**
	 * Metodo de configuracion de Hystrix para que procese alguna exception en caso de error.
	 * 
	 * @return list Item
	 */
	public List<Item> methodAlternativoListar() {
		LOG.info("Method: methodAlternativoListas.");
		List<Item> itemList = new ArrayList<>();
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(1);
		producto.setId(2L);
		producto.setNombre("Skull");
		producto.setPrecio(1500.00);
		item.setProducto(producto);
		itemList.add(item);
		
		return itemList;
	}

}
