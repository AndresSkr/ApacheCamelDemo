package com.bmind.camel.examples.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.components.CrearPersonaRequestProcessor;
import com.bmind.camel.examples.components.FaultProcessor;
import com.bmind.camel.examples.components.HomologacionProcessor;
import com.bmind.camel.examples.dto.CrearPersonaRequestDto;
import com.bmind.camel.examples.dto.EmployeeDTO;
import com.bmind.camel.examples.dto.PersonDTO;
import com.bmind.camel.examples.util.exception.NotFoundException;

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

	@Autowired
	private FaultProcessor faultProcessor;
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
		
		//onException(HttpOperationFailedException.class)
		onException(NotFoundException.class)
		.handled(true)
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader("Content-type","text/plain");
				exchange.getIn().setHeader("Error description", "La persona ya existe");
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_CONFLICT);
			}
		})
		.stop();
		
		onException(Exception.class)
		.handled(true)
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader("Content-type","text/plain");
				exchange.getIn().setHeader("Error description", "Revisar los datos");
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_NO_CONTENT);
			}
		})
		.stop();
		
		
		from("direct:homologacion")
		.process(homologacionProcessor)
		.toD(urlHomologacion + "/${exchangeProperty.idTipoDocumento}?bridgeEndpoint=true&httpMethod=GET")
		.end();
		
		from("direct:crearPersona")
		.process(crearPersonaRequestProcessor)
		.marshal()
		.json(JsonLibrary.Jackson)
		.toD(urlCrearPersona + "?bridgeEndpoint=true&httpMethod=POST")
		.choice()
		//.when(body().isNotNull())
		.when(header(Exchange.HTTP_RESPONSE_CODE).isLessThan(300))
		.unmarshal(new JacksonDataFormat(PersonDTO.class))
		.otherwise()
		.process(faultProcessor)
		.end();
	}

}
