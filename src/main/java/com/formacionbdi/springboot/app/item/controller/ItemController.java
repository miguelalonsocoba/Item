package com.formacionbdi.springboot.app.item.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * The clas ItemController.
 * 
 * @RefreshScope permite actualizar los componentes (controladores, clases,
 *               atc).
 */
@RefreshScope
@RestController
public class ItemController {

	/**
	 * Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

	/**
	 * Se inyecta el Bean de Spring para acceder a su configuracion.
	 */
	@Autowired
	private Environment env;

	/**
	 * The service.
	 */
//	@Qualifier(value = "serviceRestTemplate")
	@Autowired
	@Qualifier(value = "serviceFeign")
	private IItemService itemService;

	/**
	 * Se inyecta con la etiquete @value la información en esta vairable. Esta
	 * variable contendara los parametros de configuración.
	 */
	@Value("${configuracion.texto}")
	private String texto;

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

	@PostMapping(value = "/crear")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		LOG.info("Method: crear(). Param-Value: ");
		return itemService.save(producto);
	}

	@PutMapping(value = "/editar/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		LOG.info("Method: editar().");
		return itemService.update(producto, id);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		LOG.info("Method: eliminar()");
		itemService.delete(id);
	}

	/**
	 * Metodo de configuracion de Hystrix para que procese alguna exception en caso
	 * de error.
	 * 
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
	 * Metodo de configuracion de Hystrix para que procese alguna exception en caso
	 * de error.
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

	/**
	 * Esta metodo devuelve toda la configuracion del servicio en un archivo JSON.
	 * 
	 * @param String puerto
	 * @return Response Entity
	 */
	@GetMapping(value = "/obtener-config", produces = "application/json")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
		LOG.info("Method: obtenerConfig()");
		LOG.info("Valor de la varibale texto: " + texto);
		LOG.info("Valor del Puerto: " + puerto);
		Map<String, String> json = new HashMap<>();
		json.put("text", texto);
		json.put("puerto", puerto);

		// Valida que la configuracion sea del ambiente dev, para agregar los atributos
		// propios del ambiente.
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

}
