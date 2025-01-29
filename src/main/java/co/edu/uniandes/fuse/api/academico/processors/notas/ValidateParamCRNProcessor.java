package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamCRNProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String crn = exchange.getIn().getHeader("scrn",String.class);
		
		if (crn != null) {
			if (crn.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo scrn requerido");
			}
		}else if(crn == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo scrn requerido");
		}
		
		exchange.setProperty("scrn", crn);

	}

}
