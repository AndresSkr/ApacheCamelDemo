package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SalaryResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Double salario = exchange.getIn().getBody(Double.class);
		exchange.setProperty("salario", salario);
		exchange.getIn().setBody(null);
	}

}
