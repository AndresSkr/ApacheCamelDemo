package com.bmind.camel.examples.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CalculadoraDto;
import com.bmind.camel.examples.dto.ResponseDto;
import com.bmind.camel.examples.entities.CalculadoraEntity;
import com.bmind.camel.examples.entities.CalculadoraResponseEntity;
import com.bmind.camel.examples.entities.CrearPersonaResponseEntity;

@Component
public class CalculadoraMasivaRoute extends RouteBuilder{

	
	@Value("${calculadora.camel.rs.url.suma}")
	private String urlCalculadoraSuma;
	
	@Value("${calculadora.camel.rs.url.subtract}")
	private String urlCalculadoraSubtract;
	
	@Value("${calculadora.camel.rs.url.divide}")
	private String urlCalculadoraDivide;
	
	@Value("${calculadora.camel.rs.url.multiply}")
	private String urlCalculadoraMultiply;
	@Override
	public void configure() throws Exception {
		
		from("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?delay=300000&initialDelay=1000&fileName=calculadora.csv&noop=true")
		.unmarshal().bindy(BindyType.Csv, CalculadoraEntity.class)
		.split()
		.body()
		.to("direct:calculadoraCsv")
		.end();
		
		from("direct:calculadoraCsv")
		//.log("1 ${body}")
		.marshal().json(JsonLibrary.Jackson)
		//.log("2 ${body}")
		.unmarshal(new JacksonDataFormat(CalculadoraDto.class))
		//.log("3 ${body}")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				CalculadoraDto numerosCalculadora = exchange.getIn().getBody(CalculadoraDto.class);
				exchange.setProperty("num1", numerosCalculadora.getNum1());
				exchange.setProperty("num2", numerosCalculadora.getNum2());
			}
		})
		//.log("${exchangeProperty.num1}")
		//.log("${exchangeProperty.num2}")
		.toD(urlCalculadoraSuma + "/${exchangeProperty.num1}/${exchangeProperty.num2}?bridgeEndpoint=true&httpMethod=GET")
		//.log("LA SUMA DE  ${exchangeProperty.num1} + ${exchangeProperty.num2} = ${body.response}")
		.unmarshal(new JacksonDataFormat(ResponseDto.class))
		.setHeader("ResultadoSuma",simple("${exchangeProperty.num1} + ${exchangeProperty.num2} = ${body.response}"))
		.log("Suma : ${headers.ResultadoSuma}")
		
		
		.toD(urlCalculadoraSubtract + "/${exchangeProperty.num1}/${exchangeProperty.num2}?bridgeEndpoint=true&httpMethod=GET")
		.unmarshal(new JacksonDataFormat(ResponseDto.class))
		.setHeader("ResultadoSubtract",simple("${exchangeProperty.num1} - ${exchangeProperty.num2} = ${body.response}"))
		.log("Subtract : ${headers.ResultadoSubtract}")
		
		.toD(urlCalculadoraDivide + "/${exchangeProperty.num1}/${exchangeProperty.num2}?bridgeEndpoint=true&httpMethod=GET")
		.unmarshal(new JacksonDataFormat(ResponseDto.class))
		.setHeader("ResultadoDivide",simple("${exchangeProperty.num1} / ${exchangeProperty.num2} = ${body.response}"))
		.log("Divide : ${headers.ResultadoDivide}")
		
		.toD(urlCalculadoraMultiply + "/${exchangeProperty.num1}/${exchangeProperty.num2}?bridgeEndpoint=true&httpMethod=GET")
		.unmarshal(new JacksonDataFormat(ResponseDto.class))
		.setHeader("ResultadoMultiply",simple("${exchangeProperty.num1} * ${exchangeProperty.num2} = ${body.response}"))
		.log("Multiply : ${headers.ResultadoMultiply}")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				CalculadoraResponseEntity res = new CalculadoraResponseEntity(
						exchange.getIn().getHeader("ResultadoSuma")+" , "+
								exchange.getIn().getHeader("ResultadoSubtract")+" , "+
								exchange.getIn().getHeader("ResultadoDivide")+" , "+
								exchange.getIn().getHeader("ResultadoMultiply"));
				exchange.getIn().setBody(res);
			}
		})
		.marshal()
		.bindy(BindyType.Csv,CalculadoraResponseEntity.class)
		.to("file:C:/Users/jhohan.hoyos.meneses/OneDrive - Accenture/Desktop?fileName=${file:name}.proc&tempPrefix=filesInProgress")
		.end();
		
		
		
		
	}

}
