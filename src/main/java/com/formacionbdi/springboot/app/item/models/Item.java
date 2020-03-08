package com.formacionbdi.springboot.app.item.models;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class Item.
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	/**
	 * Variable Object producto.
	 */
	private Producto producto;

	/**
	 * Variable cantidad.
	 */
	private Integer cantidad;

	/**
	 * Method getTotal. doubleValue() es un metodo para combertir un valor entero a
	 * double.
	 * 
	 * @return double Precio total.
	 */
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
