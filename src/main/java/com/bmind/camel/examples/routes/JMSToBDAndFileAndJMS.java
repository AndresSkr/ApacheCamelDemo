package com.bmind.camel.examples.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.apache.camel.language.xpath.XPathBuilder;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.agregattion.PersonAggregation;
import com.bmind.camel.examples.entities.PersonaEntity;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
public class JMSToBDAndFileAndJMS extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		XPathBuilder xPathBuilder = new XPathBuilder("//persons/person");
		JacksonXMLDataFormat jacksonFormat = new JacksonXMLDataFormat();
		
		jacksonFormat.setUnmarshalType(PersonaEntity.class);
		jacksonFormat.setInclude(Include.NON_NULL.name());
		
		from("activemq:queue:bmindQueueInTest?testConnectionOnStartup=true")
		.tracing()
			.log(LoggingLevel.INFO, "Mensaje recibido desde la cola ${body}")
		.split(xPathBuilder, new PersonAggregation())
			.log(LoggingLevel.INFO, "Persona XML: ${body}")
			.unmarshal(jacksonFormat)
			.log(LoggingLevel.INFO, "Perosna (Objeto): ${body}")
			.to("sql:classpath:sql/insert-data.sql")
			.to("activemq:queue:bmindQueueOutputTest")
		.end()
		.log(LoggingLevel.INFO, "Archivo: ${body}")
		.to("file:src/main/resources/files/out?fileName=personas.csv");
	
	}

}
