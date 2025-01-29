package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamCodCurProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String codigoCurso = exchange.getIn().getHeader("scodigocurso", String.class);

		if (codigoCurso != null) {
			if (codigoCurso.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo scodigocurso requerido");
			}
		}else if(codigoCurso == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo scodigocurso requerido");
		}
		
		exchange.setProperty("scodigocurso", codigoCurso);
	}

}
