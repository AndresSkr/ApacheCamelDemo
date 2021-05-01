package com.bmind.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.components.CrearPersonaRequestProcessor;
import com.bmind.camel.examples.components.HomologacionProcessor;
import com.bmind.camel.examples.dto.CrearPersonaRequestDto;
import com.bmind.camel.examples.dto.EmployeeDTO;
import com.bmind.camel.examples.dto.PersonDTO;

@Component
public class PersonRoute extends RouteBuilder {
	
	@Value("${persona.rs.url.crear}")
	private String urlCrearPersona;
	
	@Value("${homologacion.rs.url.consultar}")
	private String urlHomologacion;
	
	@Autowired
	private CrearPersonaRequestProcessor crearPersonaRequestProcessor;
	
	@Autowired
	private HomologacionProcessor homologacionProcessor ;

	@Override
	public void configure() throws Exception {
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);
		
		rest("persona")
		.id("creacion-persona-router")
		.post("crear")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.type(CrearPersonaRequestDto.class)
		.route()
		.to("direct:homologacion")
		.to("direct:crearPersona")
		.log("body 3 ${body}")
		.endRest();
		
		from("direct:homologacion")
		.process(homologacionProcessor)
		.toD(urlHomologacion + "/${exchangeProperty.idTipoDocumento}?bridgeEndpoint=true&httpMethod=GET")
		.end();
		
		from("direct:crearPersona")
		.process(crearPersonaRequestProcessor)
		.marshal()
		.json(JsonLibrary.Jackson)
		.toD(urlCrearPersona + "?bridgeEndpoint=true&httpMethod=POST")
		.unmarshal(new JacksonDataFormat(PersonDTO.class))
		.end();
	}

}
