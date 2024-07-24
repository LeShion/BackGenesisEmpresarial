/**
 * 
 */
package com.soap.client;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.soap.wsdl.ArrayOfVar;
import com.soap.wsdl.TipoCambioFechaInicial;
import com.soap.wsdl.TipoCambioFechaInicialResponse;
import com.soap.wsdl.TipoCambioRango;
import com.soap.wsdl.TipoCambioRangoResponse;
import com.soap.wsdl.Var;
import com.soap.wsdl.VariablesDisponibles;
import com.soap.wsdl.VariablesDisponiblesResponse;

/**
 * @author John
 *
 */
public class SoapClient extends WebServiceGatewaySupport{
	
	/**
	 * Método que se encarga de traer la data de Variables disponibles
	 */
	
	public VariablesDisponiblesResponse getVariablesDisponiblesResponse() {
		VariablesDisponibles variablesDisponiblesRequest = new VariablesDisponibles();
		
		SoapActionCallback soapActionCallback = new SoapActionCallback("http://www.banguat.gob.gt/variables/ws/VariablesDisponibles");
		
		return (VariablesDisponiblesResponse) getWebServiceTemplate()
				.marshalSendAndReceive("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx", variablesDisponiblesRequest, soapActionCallback);
	}
	
	/*
	 *  Metodo que se encarga de traer tipo de cambio en un rango de fechas
	 * */
	public TipoCambioRangoResponse getTipoCambioRangoResponse(String fecha_ini, String fecha_fin) {

	    TipoCambioRango tipoCambioRangoRequest = new TipoCambioRango();
	    tipoCambioRangoRequest.setFechainit(fecha_ini);
	    tipoCambioRangoRequest.setFechafin(fecha_fin);

	    SoapActionCallback soapActionCallback = new SoapActionCallback("http://www.banguat.gob.gt/variables/ws/TipoCambioRango");

	    TipoCambioRangoResponse response = (TipoCambioRangoResponse) getWebServiceTemplate()
	            .marshalSendAndReceive("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx", tipoCambioRangoRequest, soapActionCallback);

	    // Verificar y registrar la respuesta
	    if (response != null && response.getTipoCambioRangoResult() != null) {
	        ArrayOfVar vars = response.getTipoCambioRangoResult().getVars();
	        if (vars != null) {
	            for (Var var : vars.getVar()) {
	                System.out.println("Fecha: " + var.getFecha() + ", Compra: " + var.getCompra() + ", Venta: " + var.getVenta());
	            }
	        }
	    } else {
	        System.out.println("Respuesta SOAP no válida o nula.");
	    }

	    return response;
	}
	
	/**
	 * Metodo que se encarga de traer la data de Tipo de cambio de una fecha
	 */
	public TipoCambioFechaInicialResponse getTipoCambioFechaInicialResponse(String fecha) {
	    TipoCambioFechaInicial request = new TipoCambioFechaInicial();
	    request.setFechainit(fecha);
	    
	    SoapActionCallback soapActionCallback = new SoapActionCallback("http://www.banguat.gob.gt/variables/ws/TipoCambioFechaInicial");
	    
	 // Obtener la respuesta del servicio web
	    TipoCambioFechaInicialResponse response = (TipoCambioFechaInicialResponse) getWebServiceTemplate()
	            .marshalSendAndReceive("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx", request, soapActionCallback);
	    
	    // Imprimir la respuesta en consola
	    System.out.println("Respuesta del servicio web:");
	    if (response != null) {
	        ArrayOfVar vars = response.getTipoCambioFechaInicialResult().getVars();
	        if (vars != null) {
	            // Imprimir encabezados de la tabla
	            System.out.println("Fecha\tCompra\tVenta\tMoneda");
	            
	            // Imprimir datos de cada Var en formato de tabla
	            for (Var var : vars.getVar()) {
	                System.out.println(var.getFecha() + "\t" +
	                                   var.getCompra() + "\t" +
	                                   var.getVenta() + "\t" +
	                                   var.getMoneda());
	            }
	        } else {
	            System.out.println("No se encontraron datos en la respuesta.");
	        }
	    } else {
	        System.out.println("Respuesta SOAP no válida o nula.");
	    }
	    
	    // Retornar la respuesta
	    return response;
	}
	
	

}
