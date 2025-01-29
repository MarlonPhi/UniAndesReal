package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateNivelAcademicoProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		String speriodo = ex.getIn().getHeader("speriodo", String.class);
		
		if (speriodo != null) {
			if (speriodo.equals("")) {
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Campo speriodo inválido");
			} else {
				try {
					Long.parseLong(speriodo);
				} catch (NumberFormatException exepiton) {
					ex.setProperty("HttpErrorCode", "http.code.bad.request");
					throw new IllegalArgumentException("Campo speriodo inválido");
				}
			}
		} else {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Campo speriodo inválido");
		}
		
		ex.setProperty("speriodo", speriodo);
	}
}
