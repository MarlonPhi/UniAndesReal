package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamNomCuCodCuProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String nombreCurso = exchange.getIn().getHeader("snombrecurso", String.class);
		String codigoCurso = exchange.getIn().getHeader("scodigocurso", String.class);
		
		if (nombreCurso != null) {
			if (nombreCurso.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo snombrecurso requerido");
			}
		}else if(nombreCurso == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo snombrecurso requerido");
		}
		
		if (codigoCurso != null) {
			if (codigoCurso.equals("")) {
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo scodigocurso requerido");
			}
		}else if(codigoCurso == null) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo scodigocurso requerido");
		}
		
		exchange.setProperty("snombrecurso", nombreCurso);
		exchange.setProperty("scodigocurso", codigoCurso);
	}

}
