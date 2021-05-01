package com.bmind.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CrearEmpleadoResponseDto;
import com.bmind.camel.examples.dto.PersonDTO;
import com.bmind.camel.examples.entities.CrearEmpleadoEntity;

//@Component
public class EmpleadosMasivosRoute extends RouteBuilder {
	
	@Value("${empleado.camel.rs.url.crear}")
	private String urlCrearEmpleado;
	@Override
	public void configure() throws Exception {
		
		from("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?delay=300000&initialDelay=1000&fileName=empleados.csv&noop=true")
		.log("Procesando Archivo: ${file:name}")
		.unmarshal().bindy(BindyType.Csv, CrearEmpleadoEntity.class)
		.log("Body: ${body}")
		.split()
		.body()
		.log("CArgo : ${body.cargo}")
		.log("${body.idPersona}")
		.log("Area: ${body.area}")
		.to("direct:crearEmpleadoCamel")
		.end();
		
		
		from("direct:crearEmpleadoCamel")
		.log("Body:${body}")
		.marshal().json(JsonLibrary.Jackson)
		.log("Body:${body}")
		.toD(urlCrearEmpleado + "?bridgeEndpoint=true&httpMethod=POST")
		.end();
	}

}
