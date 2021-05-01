package com.bmind.camel.examples.errors;

import org.apache.camel.Exchange;

public class SalaryError {
	
	public void minusSalary(Exchange exchange) {
		exchange.getIn().setBody("Salario menor al parametrizado");
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
		exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
	}
	
//	public void salaryMinus(Exchange exchange) {
//		exchange.getIn().setBody("Salario menor al parametrizado");
//		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
//		exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
//	}

}
