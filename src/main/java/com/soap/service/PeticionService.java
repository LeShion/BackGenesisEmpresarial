/**
 * 
 */
package com.soap.service;

import java.util.List;

import com.soap.entity.Peticiones;
import com.soap.wsdl.TipoCambioRangoResponse;

/**
 * @author John
 *
 */
public interface PeticionService {
	List<Peticiones> newPeticion(List<Peticiones> listaPeticiones);
	
	void guardarPeticionesDesdeTipoCambioRangoResponse(TipoCambioRangoResponse tipoCambioRangoResponse);
	
	public abstract List<Peticiones> listarTodasLasPeticiones();
}
