package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.EmployeeDTO;
import com.bmind.camel.examples.dto.PersonDTO;

@Component
public class CrearEmpleadoRequestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		PersonDTO persona = (PersonDTO)exchange.getProperties().get("persona");
		String area = (String)exchange.getProperties().get("deparment");
		String position = (String)exchange.getProperties().get("position");
		Double salario = (Double)exchange.getProperties().get("salario");
		
		EmployeeDTO empleado = new EmployeeDTO();
		empleado.setAddress(persona.getAddress());
		empleado.setDeparment(area);
		empleado.setIdNumber(persona.getIdNumber());
		empleado.setIdType(persona.getIdType());
		
		StringBuilder sb = new StringBuilder();
		empleado.setLastNames(sb.append(persona.getLastnames()).append(" ").append(persona.getSurnames()).toString());
		
		sb = new StringBuilder();
		empleado.setNames(sb.append(persona.getFisrtnames()).append(" ").append(persona.getMiddlename()).toString());
		empleado.setPhoneNumber(persona.getPhoneNumber());
		
		empleado.setPosition(position);
		empleado.setSalary(salario.toString());

		exchange.getIn().setBody(empleado);
		
	}

}
