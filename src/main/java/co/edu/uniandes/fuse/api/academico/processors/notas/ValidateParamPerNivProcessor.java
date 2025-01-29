package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamPerNivProcessor implements Processor {
	private final static int valueComparate = 6;

	@Override
	public void process(Exchange ex) throws Exception {
		String periodo = ex.getIn().getHeader("speriodo", String.class);
		String nivel = ex.getIn().getHeader("snivel", String.class);

		if (periodo != null) {
			if (periodo.equals("")) {
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo speriodo invalido");
			}

			if (periodo.length() != valueComparate) {
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo speriodo invalido");
			}
		} else if (periodo == null) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo speriodo invalido");
		}

		try {
			Integer.parseInt(periodo);
		} catch (NumberFormatException e) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo speriodo invalido");
		}

		if (nivel == null) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo snivel invalido");
		}
		if (nivel.equals("")) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo snivel invalido");
		}

		ex.setProperty("speriodo", periodo);
		ex.setProperty("snivel", nivel);

	}

}
