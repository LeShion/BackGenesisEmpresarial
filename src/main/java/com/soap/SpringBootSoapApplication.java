package com.soap;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soap.client.SoapClient;
import com.soap.wsdl.TipoCambioRangoResponse;
import com.soap.wsdl.Var;

@SpringBootApplication
public class SpringBootSoapApplication {

	// private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSoapApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner init(SoapClient soapClient) {
		return args -> {
			TipoCambioRangoResponse tipoCambioRangoResponse = soapClient.getTipoCambioRangoResponse("01/01/2024", "31/01/2024");
	        List<Var> varList = tipoCambioRangoResponse.getTipoCambioRangoResult().getVars().getVar();

	        LOGGER.info("El tipo de cambio de los rangos 01/01/2024 y 31/01/2024 es:");
	        for (Var var : varList) {
	            LOGGER.info("Moneda: {}, Fecha: {}, Venta: {}, Compra: {}", var.getMoneda(), var.getFecha(), var.getVenta(), var.getCompra());
	        }
		};
	}*/

}
