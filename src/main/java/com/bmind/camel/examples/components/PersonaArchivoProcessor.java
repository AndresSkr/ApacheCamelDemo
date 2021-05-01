package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.PersonDTO;
import com.bmind.camel.examples.entities.CrearPersonaEntity;
import com.bmind.camel.examples.entities.CrearPersonaResponseEntity;

@Component
public class PersonaArchivoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		PersonDTO dto = exchange.getIn().getBody(PersonDTO.class);
		
		CrearPersonaResponseEntity res = new CrearPersonaResponseEntity();
		
		res.setId(dto.getId());
		res.setApellidos(dto.getLastnames() +" " + dto.getSurnames());
		res.setNombres(dto.getFisrtnames()+" "+dto.getMiddlename());
		res.setNumeroIdentificacion(dto.getIdNumber());
		res.setTelefono(dto.getPhoneNumber());
		res.setTipoidentificacion(dto.getIdType());

		exchange.getIn().setBody(res);
		
		
	}

}
