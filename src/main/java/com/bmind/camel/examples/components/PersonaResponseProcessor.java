package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.PersonDTO;

@Component
public class PersonaResponseProcessor implements Processor {

	@Value("${empleado.default.salary}")
	private String salario;	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		PersonDTO persona = exchange.getIn().getBody(PersonDTO.class);
		exchange.setProperty("persona", persona);
		exchange.getIn().setBody(null);
	}

}
