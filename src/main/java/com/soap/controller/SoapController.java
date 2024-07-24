/**
 * 
 */
package com.soap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soap.client.SoapClient;
import com.soap.entity.ParamFechaInit;
import com.soap.entity.Params;
import com.soap.entity.Peticiones;
import com.soap.service.PeticionService;
import com.soap.service.PeticionServiceImpl;
import com.soap.wsdl.ArrayOfVar;
import com.soap.wsdl.ArrayOfVariable;
import com.soap.wsdl.TipoCambioFechaInicialResponse;
import com.soap.wsdl.TipoCambioRangoResponse;
import com.soap.wsdl.Var;
import com.soap.wsdl.Variable;
import com.soap.wsdl.VariablesDisponiblesResponse;

/**
 * @author John
 *
 */
@RestController
@CrossOrigin("*")
public class SoapController {

	@Autowired
	private SoapClient soapClient;
	
	 @Autowired
    private PeticionService peticionService;
	 
	 @Autowired
	 private PeticionServiceImpl peticionServiceImpl;
	 
	 @GetMapping("/getVariables")
	    public ResponseEntity<?> getVariablesDisponibles() {
	        VariablesDisponiblesResponse response = soapClient.getVariablesDisponiblesResponse();
	        ArrayOfVariable arrayOfVariable = response.getVariablesDisponiblesResult().getVariables();

	        List<Variable> variables = arrayOfVariable.getVariable();

	        
	        return ResponseEntity.ok().body(variables);
	    }
	
	
	/*@PostMapping(value = "/tipoCambioRango", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> tipoCambioRango(@RequestBody Params params) {
  
		Map<String, Object> response = new HashMap<>();
	    
	    TipoCambioRangoResponse tipoCambioRangoResponse = soapClient.getTipoCambioRangoResponse(params.getFecha_ini(), params.getFecha_fin());
	    
	    ArrayOfVar arrayOfVar = tipoCambioRangoResponse.getTipoCambioRangoResult().getVars();
	    List<Var> varList = arrayOfVar.getVar();
	    
	    List<Map<String, Object>> varMapList = new ArrayList<>();
	    for (Var var : varList) {
	        Map<String, Object> varMap = new HashMap<>();
	        varMap.put("fecha", var.getFecha());
	        varMap.put("compra", var.getCompra());
	        varMap.put("venta", var.getVenta());
	        varMap.put("moneda", var.getMoneda());
	        varMapList.add(varMap);
	    }
	    
	 // Llamar a la función para guardar las peticiones
        peticionService.guardarPeticionesDesdeTipoCambioRangoResponse(tipoCambioRangoResponse);
	    
        
	    response.put("resultado", varMapList);
	    
	    return ResponseEntity.ok().body(varMapList);
    }*/
	 @PostMapping(value = "/tipoCambioRango", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> tipoCambioRango(@RequestBody Params params) {

	     Map<String, Object> response = new HashMap<>();

	     try {
	         TipoCambioRangoResponse tipoCambioRangoResponse = soapClient.getTipoCambioRangoResponse(params.getFecha_ini(), params.getFecha_fin());

	         ArrayOfVar arrayOfVar = tipoCambioRangoResponse.getTipoCambioRangoResult().getVars();
	         List<Var> varList = arrayOfVar.getVar();

	         List<Map<String, Object>> varMapList = new ArrayList<>();
	         for (Var var : varList) {
	             Map<String, Object> varMap = new HashMap<>();

	             try {
	                 // Validar y convertir los valores de compra y venta a float
	                 float compra = var.getCompra();
	                 float venta = var.getVenta();

	                 varMap.put("fecha", var.getFecha());
	                 varMap.put("compra", compra);
	                 varMap.put("venta", venta);
	                 varMap.put("moneda", var.getMoneda());
	                 varMapList.add(varMap);

	             } catch (NumberFormatException e) {
	                 // Manejar el error de conversión y registrar el problema
	                 System.err.println("Error al convertir los valores de compra/venta para la fecha " + var.getFecha() + ": " + e.getMessage());
	                 // Puedes optar por agregar lógica adicional para manejar estos errores según sea necesario
	             }
	         }

	         // Llamar a la función para guardar las peticiones
	         peticionService.guardarPeticionesDesdeTipoCambioRangoResponse(tipoCambioRangoResponse);

	         response.put("resultado", varMapList);
	         return ResponseEntity.ok().body(varMapList);

	     } catch (Exception e) {
	         e.printStackTrace();
	         response.put("mensaje", "Error interno al procesar la solicitud.");
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	     }
	 }
	//enpoint para obtener todas las peticiones guardadas en la base
	@GetMapping(value = "/getPeticiones", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Peticiones> listarTodasLasPeticiones(){
		return peticionServiceImpl.listarTodasLasPeticiones();
		
	}
	
	@PostMapping(value="/tipoCambioFechaInicial", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tipoCambioFechaInicial(@RequestBody ParamFechaInit paramFechaInit) {
	    try {
	        // Crear la solicitud SOAP con la fecha inicial proporcionada
	        TipoCambioFechaInicialResponse tipoCambioFechaInicialResponse = soapClient.getTipoCambioFechaInicialResponse(paramFechaInit.getFecha_ini());

	        // Procesar la respuesta y extraer los datos necesarios
	        ArrayOfVar arrayOfVar = tipoCambioFechaInicialResponse.getTipoCambioFechaInicialResult().getVars();
	        List<Var> varList = arrayOfVar.getVar();
	        
	     // Ordenar la lista de tasas de venta en orden descendente
	        varList.sort((var1, var2) -> Double.compare(var2.getVenta(), var1.getVenta()));

	        List<Map<String,Object>> varMapList = new ArrayList<>();
	        for(Var var : varList) {
	            Map<String, Object> varMap = new HashMap<>();
	            varMap.put("moneda", var.getMoneda());
	            varMap.put("fecha", var.getFecha());
	            varMap.put("venta", var.getVenta());
	            varMap.put("compra", var.getCompra());
	            varMapList.add(varMap);
	        }

	        // Construir la respuesta JSON
	        Map<String, Object> response = new HashMap<>();
	        response.put("resultado", varMapList);

	        // Devolver la respuesta exitosa
	        return ResponseEntity.ok().body(response);
	    } catch (Exception e) {
	        
	        e.printStackTrace(); // Imprimir la pila de errores en la consola
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al procesar la solicitud.");
	    }
	}
}
