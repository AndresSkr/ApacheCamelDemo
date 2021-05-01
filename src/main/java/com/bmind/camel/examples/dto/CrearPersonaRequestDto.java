package com.bmind.camel.examples.dto;

public class CrearPersonaRequestDto {
	
	private Long id;
	private int tipoidentificacion;
	private String numeroIdentificacion;
	private String nombres;
	private String apellidos;
	private String telefono;
	
	
	public CrearPersonaRequestDto() {
		super();
	}

	public CrearPersonaRequestDto(Long id, int tipoidentificacion, String numeroIdentificacion, String nombres,
			String apellidos, String telefono) {
		super();
		this.id = id;
		this.tipoidentificacion = tipoidentificacion;
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoidentificacion() {
		return tipoidentificacion;
	}

	public void setTipoidentificacion(int tipoidentificacion) {
		this.tipoidentificacion = tipoidentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

}
