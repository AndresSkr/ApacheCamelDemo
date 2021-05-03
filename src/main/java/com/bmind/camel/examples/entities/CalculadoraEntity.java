package com.bmind.camel.examples.entities;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator =",")
public class CalculadoraEntity {
	@DataField(pos=1)
	private int num1;
	@DataField(pos=2)
	private int num2;
	
	public CalculadoraEntity() {
		super();
	}

	public CalculadoraEntity(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	
}
