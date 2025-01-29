package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamsProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String numerodocumento = exchange.getIn().getHeader("snumerodocumento", String.class);
		String slogin = exchange.getIn().getHeader("slogin", String.class);
		String scodigo = exchange.getIn().getHeader("scodigo", String.class);
		
		if ((scodigo.trim().equals("") || scodigo == null) && (slogin.trim().equals("") || slogin == null) 
				&& (numerodocumento.trim().equals("") || numerodocumento == null)) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario uno de los campos requeridos.");
		}
		
		
	}

}
