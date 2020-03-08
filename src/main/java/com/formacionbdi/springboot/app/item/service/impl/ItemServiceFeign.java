package com.formacionbdi.springboot.app.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.service.IItemService;

/**
 * The class ItemServiceFeign. Este service esta implementado con Feign. La
 * anotacion Primary indica que esta se inyectara en el controller o que esta en
 * un nuvel primordial. Ya que hay dos clases servicios pero con diferente
 * implementación.
 */
@Service(value = "serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

	/**
	 * Constant Log.
	 */
	private static final Log LOG = LogFactory.getLog(ItemServiceFeign.class);

	@Autowired
	private ProductoClienteRest clienteFeign;

	/**
	 * Buscar todos los Items.
	 * 
	 * @return list Item
	 */
	@Override
	public List<Item> findAll() {
		LOG.info("Method finall()");
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	/**
	 * Buscar un Item.
	 * 
	 * @return Item
	 */
	@Override
	public Item findById(Long id, Integer cantidad) {
		LOG.info("Method findById()");
		return new Item(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		LOG.info("Method: save(). Param-Value: " + producto);
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		LOG.info("Method: update(). Param-Value: " + producto);
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		LOG.info("Method: delete(). Param-Value: " + id);
		clienteFeign.eliminar(id);
	}

}
