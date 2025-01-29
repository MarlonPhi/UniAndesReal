package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamNivProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String nivel = exchange.getIn().getHeader("snivel", String.class);
		
		if (nivel != null) {
			if (nivel.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo snivel inavlido");
			}
		}else if(nivel == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo snivel inavlido");
		}
		 
		exchange.setProperty("snivel", nivel);
		
	}

}
