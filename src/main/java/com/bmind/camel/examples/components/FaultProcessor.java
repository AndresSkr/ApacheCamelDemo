package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.util.exception.NotFoundException;

@Component
public class FaultProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		if (((Integer) exchange.getIn().getHeader("CamelHttpResponseCode")).equals(HttpStatus.NO_CONTENT.value())) {
			throw new NotFoundException("La persona no existe");
		} else if (((Integer) exchange.getIn().getHeader("CamelHttpResponseCode"))
				.equals(HttpStatus.CONFLICT.value())) {
			throw new NotFoundException("Ya Existe");
		} else if (((Integer) exchange.getIn().getHeader("CamelHttpResponseCode"))
				.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
			throw new Exception("Error inesperado");
		}

	}

}
