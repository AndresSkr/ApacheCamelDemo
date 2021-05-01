package com.bmind.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.components.PersonaResponseProcessor;
import com.bmind.camel.examples.dto.Empleado;
import com.bmind.camel.examples.errors.SalaryError;
import com.bmind.camel.examples.services.EmpleadoService;

//@Component
public class Sesion1Route /*extends RouteBuilder*/{
	
	/*@Autowired
	private EmpleadoService service;
	
	@Autowired
	private EmpleadoProcesor processor;

	@Override
	public void configure() throws Exception {
		
		
		restConfiguration().component("servlet").port(9191).host("localhost").bindingMode(RestBindingMode.json);		
		
		//rest("/empleado").get("/obtenerTodos").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Hola a todos!!!!"));		

		rest("/empleado").get("/obtenerTodos").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody().constant(service.getEmpleados());
		
		
		rest("/empleado")
		.post("/agregar")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.type(Empleado.class)
		.outType(Empleado.class)
		.route()
		.choice()
		.when()
		.simple("${body.salario} < 500000")
		.bean(new SalaryError(), "minusSalary")
		.otherwise()
		.process(processor)
		.endRest();
	
		
	}*/

}
