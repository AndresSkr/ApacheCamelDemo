package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CrearPersonaRequestDto;
import com.bmind.camel.examples.dto.PersonDTO;

@Component
public class CrearPersonaRequestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String tipoDeCc = exchange.getIn().getBody(String.class);

		PersonDTO dto = new PersonDTO();
		CrearPersonaRequestDto request = (CrearPersonaRequestDto) exchange.getProperties().get("persona");
		dto.setAddress("calle");
		
		dto.setId(request.getId());
		dto.setIdNumber(request.getNumeroIdentificacion());
		dto.setIdType(tipoDeCc);
		
		dto.setPhoneNumber(request.getTelefono());
		
		String nombresRequest = request.getNombres();
		String apellidosRequest = request.getApellidos();
		
		String[] nombres = nombresRequest.split(" ");
		String firstName = nombres[0]; 
		String middleName = nombres[1];
		
		String[] apellidos = apellidosRequest.split(" ");
		String lastName = apellidos[0]; 
		String surname = apellidos[1];
		
		dto.setFisrtnames(firstName);
		dto.setMiddlename(middleName);
		dto.setLastnames(lastName);
		dto.setSurnames(surname);
		
		
		exchange.getIn().setBody(dto);
	}

}
