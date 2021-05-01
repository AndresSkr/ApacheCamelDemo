package com.bmind.camel.examples.entities;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator =",",skipField = true, skipFirstLine = true )
public class CrearPersonaEntity {
	
	@DataField(pos=1)
	private int tipoidentificacion;
	
	@DataField(pos=2)
	private String numeroIdentificacion;
	
	@DataField(pos=3)
	private String nombres;
	
	@DataField(pos=4)
	private String apellidos;
	
	@DataField(pos=5)
	private String telefono;

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
