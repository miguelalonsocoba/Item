package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;

/**
 * The Interface ProductoClienteRest. Con la anotacion se define que esta
 * interface es un clietne Feign. En el parametro name: indica el nombre del
 * microservicio que se va a conectar o el que consumira.
 * (springboot-servicio-producto). En el parametro url: idica la url del cual se
 * consumira la aplicacion Rest.
 */
@FeignClient(name = "servicio-productos", url = "http://localhost:8001")
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

}
