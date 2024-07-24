/**
 * 
 */
package com.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.soap.client.SoapClient;

/**
 * @author John 
 *
 */
@Configuration
public class SoapConfig {

    @Bean
    Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.soap.wsdl");
		return marshaller;
	}

    @Bean
    SoapClient getSoapClient(Jaxb2Marshaller marshaller) {
		SoapClient soapClient= new SoapClient();
		soapClient.setDefaultUri("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx");
		soapClient.setMarshaller(marshaller);
		soapClient.setUnmarshaller(marshaller);
		
		return soapClient;
	}

}
