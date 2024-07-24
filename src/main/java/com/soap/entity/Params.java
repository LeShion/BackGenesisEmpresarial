package com.soap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "params")
public class Params {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "fecha_ini")
	private String fecha_ini;
	
	@Column(name = "fecha_fin")
	private String fecha_fin;

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
	 * @return the fecha_ini
	 */
	public String getFecha_ini() {
		return fecha_ini;
	}

	/**
	 * @param fecha_ini the fecha_ini to set
	 */
	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	/**
	 * @return the fecha_fin
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * @param fecha_fin the fecha_fin to set
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	
	

}
