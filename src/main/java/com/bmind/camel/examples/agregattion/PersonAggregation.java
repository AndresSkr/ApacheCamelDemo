package com.bmind.camel.examples.agregattion;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import com.bmind.camel.examples.entities.PersonaEntity;
import com.ibm.icu.text.SimpleDateFormat;

public class PersonAggregation implements AggregationStrategy{

	
	private SimpleDateFormat sdf;
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		this.sdf= new SimpleDateFormat("dd-MM/yyyy");
		PersonaEntity p = null;
		
		if(oldExchange==null) {
			p = newExchange.getIn().getBody(PersonaEntity.class);
			newExchange.getIn().setBody(this.getLine(p));
		}else {
			p = newExchange.getIn().getBody(PersonaEntity.class);
			String text = oldExchange.getIn().getBody(String.class);
			
			newExchange.getIn().setBody(text.concat(this.getLine(p)));
		}
		
		return newExchange;
	}
	
	private String getLine(PersonaEntity p) {
		
		StringBuilder sb = new StringBuilder();
		sb
		.append(p.getFirstName())
		.append("|")
		.append(p.getLastName())
		.append("|")
		.append(p.getGender())
		.append("|")
		.append(sdf.format(p.getBirthDate()))
		.append("|OK\n");
		
		return sb.toString();
	}

}
