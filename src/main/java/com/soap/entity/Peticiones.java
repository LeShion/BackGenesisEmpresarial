/**
 *  
 */
package com.soap.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author John
 *
 */
@Entity
@Table(name = "peticion")
public class Peticiones {

	/**
	 * cosos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "peticion")
	private Integer peticion;
	
	@Column(name = "fecha_peticion")
	private String fecha_peticion;
	
	@Column(name = "tc_venta")
	private Float tc_venta;
	
	@Column(name = "tc_compra")
	private Float tc_compra;
	
	@Column(name = "fecha_tipo_cambio")
	private String fecha_tipo_cambio;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}






	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}












	/**
	 * @return the peticion
	 */
	public Integer getPeticion() {
		return peticion;
	}












	/**
	 * @param peticion the peticion to set
	 */
	public void setPeticion(Integer peticion) {
		this.peticion = peticion;
	}












	/**
	 * @return the fecha_peticion
	 */
	public String getFecha_peticion() {
		return fecha_peticion;
	}












	/**
	 * @param string the fecha_peticion to set
	 */
	public void setFecha_peticion(String string) {
		this.fecha_peticion = string;
	}












	/**
	 * @return the tc_venta
	 */
	public Float getTc_venta() {
		return tc_venta;
	}












	/**
	 * @param tc_venta the tc_venta to set
	 */
	public void setTc_venta(Float tc_venta) {
		this.tc_venta = tc_venta;
	}












	/**
	 * @return the tc_compra
	 */
	public Float getTc_compra() {
		return tc_compra;
	}












	/**
	 * @param tc_compra the tc_compra to set
	 */
	public void setTc_compra(Float tc_compra) {
		this.tc_compra = tc_compra;
	}












	/**
	 * @return the fecha_tipo_cambio
	 */
	public String getFecha_tipo_cambio() {
		return fecha_tipo_cambio;
	}












	/**
	 * @param fecha_tipo_cambio the fecha_tipo_cambio to set
	 */
	public void setFecha_tipo_cambio(String fecha_tipo_cambio) {
		this.fecha_tipo_cambio = fecha_tipo_cambio;
	}












	//constructor vacio
	public Peticiones() {
		
	}

}
