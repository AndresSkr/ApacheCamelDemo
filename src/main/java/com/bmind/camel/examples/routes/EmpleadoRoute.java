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

import com.bmind.camel.examples.components.CrearEmpleadoRequestProcessor;
import com.bmind.camel.examples.components.FaultProcessor;
import com.bmind.camel.examples.components.FinalProcessor;
import com.bmind.camel.examples.components.InitialProcessor;
import com.bmind.camel.examples.components.PersonaResponseProcessor;
import com.bmind.camel.examples.components.SalaryResponseProcessor;
import com.bmind.camel.examples.dto.CrearEmpleadoRequestDto;
import com.bmind.camel.examples.dto.CrearEmpleadoResponseDto;
import com.bmind.camel.examples.dto.EmployeeDTO;
import com.bmind.camel.examples.dto.PersonDTO;
import com.bmind.camel.examples.util.exception.NotFoundException;

@Component
public class EmpleadoRoute extends RouteBuilder {
	
	@Value("${persona.rs.url.buscar.por.id}")
	private String urlBuscarPersona;
	
	@Value("${empleado.rs.url.crear}")
	private String urlCrearEmpleado;
	
	@Value("${salario.rs.url.consultar}")
	private String urlBuscarSalario;
	
	@Autowired
	private PersonaResponseProcessor personaResponseProcessor;
	
	@Autowired
	private InitialProcessor initialProcessor;
	
	@Autowired 
	private FinalProcessor finalProcessor;
	
	@Autowired
	private SalaryResponseProcessor salaryResponseProcessor;
	
	@Autowired
	private CrearEmpleadoRequestProcessor crearEmpleadoRequestProcessor;
	
	@Autowired
	private FaultProcessor faultProcessor;

	@Override
	public void configure() throws Exception {
		
		
		restConfiguration() //Configuracion del servicio Rest
		.component("servlet")
		.bindingMode(RestBindingMode.json); //Formato en que vienen los argumentos del API
		
		//Definicion del API
		
		rest("/empleado")
		.id("creacion-empleado-route") //Identificando la ruta dentro del contexto camel
		.post("/crear")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.type(CrearEmpleadoRequestDto.class)
		.outType(CrearEmpleadoResponseDto.class)
		.route()
		
		//CARGAR INFORMACION EN MEMORIA
		.process(initialProcessor)
		
		.to("direct:consultarPersonaById")
		.to("direct:consultarSalario")
		.to("direct:crearEmpleado")
		
		.process(finalProcessor)
		
		.endRest();
		
		onException(NotFoundException.class)
		.handled(true)
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader("Content-type","text/plain");
				exchange.getIn().setHeader("Error description", "La persona no existe");
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_NO_CONTENT);
			}
		})
		.stop();
		
		onException(Exception.class)
		.handled(true)
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text-plain" );
				exchange.getIn().setHeader("error descripccion","Verifique los datos");
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_NO_CONTENT);
			}
		})
		.stop();
				
		
		
		from("direct:consultarPersonaById")
		.toD(urlBuscarPersona + "?id=${exchangeProperty.idPerson}&bridgeEndpoint=true&httpMethod=GET")
		.choice().when(body().isNotNull())
		.unmarshal(new JacksonDataFormat(PersonDTO.class))
		.process(personaResponseProcessor)
		.otherwise()
		.process(faultProcessor)
		.end();
		
		from("direct:consultarSalario")
		.toD(urlBuscarSalario+ "?deparment=${exchangeProperty.deparment}&position=${exchangeProperty.position}&bridgeEndpoint=true&httpMethod=GET")
		.choice().when(body().isNotNull())
		.unmarshal(new JacksonDataFormat(Double.class))
		.process(salaryResponseProcessor)
		.otherwise()
		.process(faultProcessor)
		.end();
		
		from("direct:crearEmpleado")
		.process(crearEmpleadoRequestProcessor)
		.marshal().json(JsonLibrary.Jackson)
		.toD(urlCrearEmpleado + "?bridgeEndpoint=true&httpMethod=POST")
		.unmarshal(new JacksonDataFormat(EmployeeDTO.class))
		.end();		
		
		
	}

}
