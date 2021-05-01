package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CrearEmpleadoRequestDto;

@Component
public class InitialProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		CrearEmpleadoRequestDto request =  exchange.getIn().getBody(CrearEmpleadoRequestDto.class);
		
		exchange.setProperty("position", request.getCargo());
		exchange.setProperty("deparment", request.getArea());
		exchange.setProperty("idPerson", request.getIdPersona());
		
		exchange.getIn().setBody(null);
		
	}

}
