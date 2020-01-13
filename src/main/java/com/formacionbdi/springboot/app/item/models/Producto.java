package com.formacionbdi.springboot.app.item.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class Producto.
 *
 */
@Getter
@Setter
@ToString
public class Producto {

	/**
	 * Variable id.
	 */
	private Long id;

	/**
	 * Variable nombre.
	 */
	private String nombre;

	/**
	 * Variable precio.
	 */
	private Double precio;

	/**
	 * Variable createAt.
	 */
	private Date createAt;

}
