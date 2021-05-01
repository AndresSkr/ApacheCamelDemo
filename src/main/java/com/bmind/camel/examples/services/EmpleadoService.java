package com.bmind.camel.examples.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.bmind.camel.examples.dto.Empleado;

@Service
public class EmpleadoService {
	
	private List<Empleado> empleados = new ArrayList<>(); 	
	
	@PostConstruct
	private void init() {
		empleados.add(new Empleado("Julian", "Lopez", "CC", "79888777", "Calle 100 - Bogotá", 1000000L));
	}
	
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	
	public Empleado agregaEmpleado(Empleado empleado)  {
		empleados.add(empleado);
		return empleado;
	}
	

}
