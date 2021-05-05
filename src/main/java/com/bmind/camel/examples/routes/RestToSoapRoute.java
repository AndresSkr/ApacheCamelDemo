package com.bmind.camel.examples.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.ResponseDto;

@Component
public class RestToSoapRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);
		
		onException(HttpOperationFailedException.class)
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
		
		rest("/calculadora")
		.id("calculadora-route")
		.get("/sumar/{num1}/{num2}")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.param()
			.name("num1")
			.type(RestParamType.path)
		.endParam()
		.param()
			.name("num2")
			.type(RestParamType.path)
		.endParam()
		.route()
			.to("direct:calculadora-sumar-route")
		.end();
		
		from("direct:calculadora-sumar-route")
		.routeId("calculadora-sumar-route")
		.to("velocity://file:src/main/resources/templates/sumar-request.xml?contentCache=true")
		.removeHeader("*")
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=UTF-8"))
		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
		.toD("http://www.dneonline.com/calculator.asmx?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=60000")
		.convertBodyTo(String.class)
		.removeHeader("*")
		.setHeader("result", xpath("//*[local-name()='AddResult']//text()",String.class))
		.setProperty("result", xpath("//*[local-name()='AddResult']//text()",String.class))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				ResponseDto response = new ResponseDto(Integer.valueOf((String)exchange.getIn().getHeader("result")));
				exchange.getIn().setBody(response);
			}
		})
		.setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200))
		.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
		.end();
		
		
		rest("/calculadora")
		.get("/subtract/{num1}/{num2}")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.param()
			.name("num1")
			.type(RestParamType.path)
		.endParam()
		.param()
			.name("num2")
			.type(RestParamType.path)
		.endParam()
		.route()
		.to("direct:calculadora-subtract-route")
		.end();
		
		from("direct:calculadora-subtract-route")
		.to("velocity://file:src/main/resources/templates/substract-request.xml?contentCache=true")
		.removeHeader("*")
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=UTF-8"))
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.toD("http://www.dneonline.com/calculator.asmx?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=60000")
		.convertBodyTo(String.class)
		.removeHeader("*")
		.setHeader("result", xpath("//*[local-name()='SubtractResult']//text()",String.class))
		.setProperty("result", xpath("//*[local-name()='SubtractResult']//text()",String.class))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				ResponseDto response = new ResponseDto(Integer.valueOf((String)exchange.getIn().getHeader("result")));
				exchange.getIn().setBody(response);
			}
		})
		.setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200))
		.setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE))
		.end();
		
		rest("/calculadora")
		.get("/multiply/{num1}/{num2}")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.param()
			.name("num1")
			.type(RestParamType.path)
		.endParam()
		.param()
			.name("num2")
			.type(RestParamType.path)
		.endParam()
		.route()
		.to("direct:calculadora-multiply-route")
		.end();
		
		from("direct:calculadora-multiply-route")
		.to("velocity://file:src/main/resources/templates/multiply-request.xml?contentCache=true")
		.removeHeader("*")
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=UTF-8"))
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.toD("http://www.dneonline.com/calculator.asmx?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=60000")
		.convertBodyTo(String.class)
		.removeHeader("*")
		.setHeader("result", xpath("//*[local-name()='MultiplyResponse']//text()",String.class))
		.setProperty("result", xpath("//*[local-name()='MultiplyResponse']//text()",String.class))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				ResponseDto response = new ResponseDto(Integer.valueOf((String)exchange.getIn().getHeader("result")));
				exchange.getIn().setBody(response);
			}
		})
		.setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200))
		.setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE))
		.end();
		

		rest("/calculadora")
		.get("/divide/{num1}/{num2}")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.param()
			.name("num1")
			.type(RestParamType.path)
		.endParam()
		.param()
			.name("num2")
			.type(RestParamType.path)
		.endParam()
		.route()
		.to("direct:calculadora-divide-route")
		.end();
		
		from("direct:calculadora-divide-route")
		.to("velocity://file:src/main/resources/templates/divide-request.xml?contentCache=true")
		.removeHeader("*")
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml;charset=UTF-8"))
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.toD("http://www.dneonline.com/calculator.asmx?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=60000")
		.convertBodyTo(String.class)
		.removeHeader("*")
		.setHeader("result", xpath("//*[local-name()='DivideResult']//text()",String.class))
		.setProperty("result", xpath("//*[local-name()='DivideResult']//text()",String.class))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				ResponseDto response = new ResponseDto(Integer.valueOf((String)exchange.getIn().getHeader("result")));
				exchange.getIn().setBody(response);
			}
		})
		.setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200))
		.setHeader(Exchange.CONTENT_TYPE,constant(MediaType.APPLICATION_JSON_VALUE))
		.end();
	}
}
