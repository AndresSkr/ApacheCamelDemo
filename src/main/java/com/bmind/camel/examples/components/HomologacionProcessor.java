package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CrearEmpleadoRequestDto;
import com.bmind.camel.examples.dto.CrearPersonaRequestDto;

@Component
public class HomologacionProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		CrearPersonaRequestDto request = exchange.getIn().getBody(CrearPersonaRequestDto.class);
		exchange.setProperty("idTipoDocumento", request.getTipoidentificacion());
		exchange.setProperty("persona", request);
		exchange.getIn().setBody(null);
		
	}

}
