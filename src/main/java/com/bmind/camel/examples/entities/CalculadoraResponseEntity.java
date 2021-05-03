package com.bmind.camel.examples.entities;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator =",",skipField = true, skipFirstLine = true )
public class CalculadoraResponseEntity {
	@DataField(pos=0)
	private String responseCalculate;

	public CalculadoraResponseEntity() {
		super();
	}

	public CalculadoraResponseEntity(String responseCalculate) {
		super();
		this.responseCalculate = responseCalculate;
	}

	public String getResponseCalculate() {
		return responseCalculate;
	}

	public void setResponseCalculate(String responseCalculate) {
		this.responseCalculate = responseCalculate;
	}

}
