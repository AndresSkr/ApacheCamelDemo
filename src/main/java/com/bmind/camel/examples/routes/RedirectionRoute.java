package com.bmind.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RedirectionRoute extends RouteBuilder {
	
	public void configure() throws Exception {
		
		/*restConfiguration().component("servlet").port(9191).host("localhost").bindingMode(RestBindingMode.off);
		
		rest().get("/listarEmpleados").to("direct:empleadoService");
		
		
		from("direct:empleadoService")
		.toD("http://localhost:8282/empleados?bridgeEndpoint=true&?httpMethod=GET");*/
	}

}
 