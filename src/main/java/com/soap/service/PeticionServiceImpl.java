/**
 * 
 */
package com.soap.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soap.entity.Peticiones;
import com.soap.repository.PeticionesRepository;
import com.soap.wsdl.ArrayOfVar;
import com.soap.wsdl.TipoCambioRangoResponse;
import com.soap.wsdl.Var;

/**
 * @author John
 *
 */
@Service
public class PeticionServiceImpl implements PeticionService{
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Autowired
	private PeticionesRepository peticionesRepository;

	@Override
	public List<Peticiones> newPeticion(List<Peticiones> listaPeticiones) {
		// Iterar sobre la lista de peticiones y guardar cada una en la base de datos
        for (Peticiones peticion : listaPeticiones) {
            peticionesRepository.save(peticion);
        }
        // Retornar la lista de peticiones guardadas (puedes modificar esta parte si necesitas hacer algo diferente)
        return listaPeticiones;
	}

	@Override
	public void guardarPeticionesDesdeTipoCambioRangoResponse(TipoCambioRangoResponse tipoCambioRangoResponse) {
		ArrayOfVar arrayOfVar = tipoCambioRangoResponse.getTipoCambioRangoResult().getVars();
        List<Var> varList = arrayOfVar.getVar();

        // Obtener la última petición ordenada por ID de forma descendente
        Optional<Peticiones> ultimaPeticionOptional = peticionesRepository.findTopByOrderByIdDesc();

        // Verificar si se encontró una última petición
        if (ultimaPeticionOptional.isPresent()) {
            // Si se encontró, obtener su número de petición y sumarle 1 para la nueva petición
            Peticiones ultimaPeticion = ultimaPeticionOptional.get();
            int numeroPeticion = ultimaPeticion.getPeticion() + 1;

            // Crear y guardar la nueva petición
            guardarNuevaPeticion(varList, numeroPeticion);
        } else {
            // Si no se encontró ninguna petición, empezar con el número de petición 1
            guardarNuevaPeticion(varList, 1);
        }
		
	}
	
	private void guardarNuevaPeticion(List<Var> varList, int numeroPeticion) {
		try {
			for (Var var : varList) {
	            Peticiones peticion = new Peticiones();
	            peticion.setPeticion(numeroPeticion);
	            peticion.setFecha_peticion(formato.format(new Date()));
	            peticion.setFecha_tipo_cambio(var.getFecha());
	            peticion.setTc_compra(var.getCompra());
	            peticion.setTc_venta(var.getVenta());

	            // Guardar la entidad en la base de datos
	            peticionesRepository.save(peticion);
	        }
		} catch (Exception e) {
			System.out.println(e);
		}
        
    }
	//Retorna la lista de todas las peticiones realizadas
	@Override
	public List<Peticiones> listarTodasLasPeticiones() {
		
		return peticionesRepository.findAll();
	}
	


}
