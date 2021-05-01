package com.bmind.camel.examples.dto;

public class CrearEmpleadoRequestDto {
	
	private Long idPersona;
	private String area;
	private String cargo;	
	
	public CrearEmpleadoRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
