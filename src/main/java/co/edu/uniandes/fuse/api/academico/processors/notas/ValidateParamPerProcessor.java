package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamPerProcessor implements Processor {
	private final static int valueComparate = 6;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String periodo = exchange.getIn().getHeader("speriodo", String.class);

		
		if (periodo != null) {
			if (periodo.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo speriodo requerido");
			}

			if (periodo.length() != valueComparate) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo speriodo requerido");
			}
		} else if (periodo == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo speriodo requerido");
		}
		
		try {
			Integer.parseInt(periodo);
		} catch (NumberFormatException e) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo speriodo requerido");
		}
		
		exchange.setProperty("speriodo", periodo);
	}

}
