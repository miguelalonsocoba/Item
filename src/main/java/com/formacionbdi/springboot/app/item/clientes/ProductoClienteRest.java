package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.models.Producto;

/**
 * The Interface ProductoClienteRest. Con la anotacion se define que esta
 * interface es un clietne Feign. En el parametro name: indica el nombre del
 * microservicio que se va a conectar o el que consumira.
 * (springboot-servicio-producto). En el parametro url: idica la url del cual se
 * consumira la aplicacion Rest.
 */
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	/**
	 * Obtener el listado productos. El parametro en la anotacion es el valor o
	 * EndPoint del microservicio que queremos consumir.
	 * 
	 * @return lis of producto
	 */
	@GetMapping(value = "/listar")
	public List<Producto> listar();
	
	
	/**
	 * Obtener el listado productos. El parametro en la anotacion es el valor o
	 * EndPoint del microservicio que queremos consumir.
	 * 
	 * @return Produco
	 */
	@GetMapping(value = "/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	/**
	 * Crear un Producto.
	 * @param producto
	 * @return Producto
	 */
	@PostMapping(value = "/crear")
	public Producto crear(@RequestBody Producto producto);
	 
	/**
	 * Actualiza un producto.
	 * @param producto
	 * @param id
	 * @return Producto
	 */
	@PutMapping(value = "/editar/{id}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	/**
	 * Elimina un producto.
	 * @param id
	 */
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminar(@PathVariable Long id);

}
