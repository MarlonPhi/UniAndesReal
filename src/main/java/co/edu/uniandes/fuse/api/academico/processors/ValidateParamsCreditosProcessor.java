package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamsCreditosProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String nivel = exchange.getIn().getHeader("nivel", String.class );
		
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		if (nivel.isEmpty() || nivel == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [nivel].");
		}


	}

}
