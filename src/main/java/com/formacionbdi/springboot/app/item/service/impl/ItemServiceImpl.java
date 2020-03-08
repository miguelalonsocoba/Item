package com.formacionbdi.springboot.app.item.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.service.IItemService;

/**
 * The class ItemServiceImpl. Este service esta implementado con RestTemplate.
 */
@Service(value = "serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

	/**
	 * The variable Log.
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
		List<Producto> productos = Arrays
				.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
		LOG.info("Salida del method: finAll()");
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	/**
	 * Gets the item.
	 */
	@Override
	public Item findById(Long id, Integer cantidad) {
		LOG.info("Method: findById(). Params-Value: " + id + ", " + cantidad);
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class,
				pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		LOG.info("Method: save(). Param-Value: " + producto);

		HttpEntity<Producto> body = new HttpEntity<>(producto);
		LOG.info("Se creo la entity de Producto. Se procede a consumir al microservicio.");
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST,
				body, Producto.class);

		LOG.info("Se consumio correctamente el microservicio. Value: " + response);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		LOG.info("Method: update(). Param-Value: Producto " + producto + " ID: " + id);

		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		HttpEntity<Producto> bodyEntity = new HttpEntity<>(producto);
		LOG.info("Se crea la entit de Producto. Se procede a consumir al microservicio.");
		ResponseEntity<Producto> responseEntity = clienteRest.exchange("http://servicio-productos/editar/{id}",
				HttpMethod.PUT, bodyEntity, Producto.class, pathVariables);

		LOG.info("Se consumio correctamente el microservicio. Value: " + responseEntity);
		return responseEntity.getBody();
	}

	@Override
	public void delete(Long id) {
		LOG.info("Method: delete(). Param-Value: " + id);

		HashMap<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());

		clienteRest.delete("http://servicio-productos/eliminar/{id}", pathVariables);

	}

}
