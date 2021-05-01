package com.bmind.camel.examples.components;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.bmind.camel.examples.dto.CrearEmpleadoResponseDto;
import com.bmind.camel.examples.dto.EmployeeDTO;

@Component
public class FinalProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		
		EmployeeDTO employee = exchange.getIn().getBody(EmployeeDTO.class);
		
		CrearEmpleadoResponseDto response = new CrearEmpleadoResponseDto();
		response.setApellidos(employee.getLastNames());
		response.setArea(employee.getDeparment());
		response.setCargo(employee.getPosition());
		response.setDireccion(employee.getAddress());
		response.setId(employee.getId());
		response.setNombres(employee.getNames());
		response.setNumeroIdentificacion(employee.getIdNumber());
		response.setSalario(employee.getSalary());
		response.setTelefono(employee.getPhoneNumber());
		response.setTipoIdentificacion(employee.getIdType());
		
		
		exchange.getIn().setBody(response);

	}

}
