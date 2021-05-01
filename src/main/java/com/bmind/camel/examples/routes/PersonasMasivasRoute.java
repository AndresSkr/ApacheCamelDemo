package com.bmind.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.components.PersonaArchivoProcessor;
import com.bmind.camel.examples.dto.PersonDTO;
import com.bmind.camel.examples.entities.CrearPersonaEntity;
import com.bmind.camel.examples.entities.CrearPersonaResponseEntity;

//@Component
public class PersonasMasivasRoute extends RouteBuilder{

	@Value("${persona.camel.rs.url.crear}")
	private String urlCrearPersona;
	
	@Autowired
	private PersonaArchivoProcessor personaFileProcessor;
	
	@Override
	public void configure() throws Exception {
		from("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?delay=1000&initialDelay=1000&fileName=personas.csv&noop=true")
		.log("Procesando ${file:name}")
		.unmarshal().bindy(BindyType.Csv, CrearPersonaEntity.class)
		.log("body1 : ${body}")
		.split()
		.body()
		.log("${body.nombres}")
		.to("direct:crearPersonaCamel")
		.to("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?fileName=${file:name}.proc&tempPrefix=filesInProgress")
		.end();
		
		from("direct:crearPersonaCamel")
		.marshal().json(JsonLibrary.Jackson)
		.toD(urlCrearPersona + "?bridgeEndpoint=true&httpMethod=POST")
		.unmarshal(new JacksonDataFormat(PersonDTO.class))
		.log("Respuesta en ell body${body}")
		.process(personaFileProcessor)
		.marshal()
		.bindy(BindyType.Csv,CrearPersonaResponseEntity.class)
		.log("body45 : ${body}")
		//.to("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?fileName=${file:name}.proc&tempPrefix=filesInProgress")
		.end();
		
		
		
	}

}
