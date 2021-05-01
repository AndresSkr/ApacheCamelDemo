package com.bmind.camel.examples.entities;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator =",",skipField = true, skipFirstLine = true )
public class CrearEmpleadoEntity {
	
	@DataField(pos=1)
	private Long idPersona;
	
	@DataField(pos=2)
	private String cargo;
	
	@DataField(pos=3)
	private String area;

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPerson) {
		this.idPersona = idPerson;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	

}
