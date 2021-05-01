package com.bmind.camel.examples.dto;

public class Empleado {
	
	private String nombres;
	private String apellidos;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String direccion;
	private Long salario;
	
	public Empleado() {

	}
	
	public Empleado(String nombres, String apellidos, String tipoIdentificacion, String numeroIdentificacion,
			String direccion, Long salario) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoIdentificacion = tipoIdentificacion;
		this.numeroIdentificacion = numeroIdentificacion;
		this.direccion = direccion;
		this.salario = salario;
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		this.salario = salario;
	}

	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	

}
