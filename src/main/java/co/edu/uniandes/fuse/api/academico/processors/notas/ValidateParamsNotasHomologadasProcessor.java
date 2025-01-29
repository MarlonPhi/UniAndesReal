package co.edu.uniandes.fuse.api.academico.processors.notas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamsNotasHomologadasProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		String nombre = ex.getIn().getHeader("snombrecurso", String.class);
		String codigo = ex.getIn().getHeader("scodigocurso", String.class);
		String nivel = ex.getIn().getHeader("snivel", String.class);
		
		validateQueryData(ex, nombre, codigo, nivel);

	}
	
	private void validateQueryData(Exchange ex, String nombre, String codigo, String nivel) {
		if ((codigo == null || codigo.trim().equals("")) && (nombre == null || nombre.trim().equals(""))) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Parámetros insuficientes para realizar la consulta");
		}

		if ((codigo != null) && (!codigo.trim().equals(""))) {
			try {
				Long.parseLong(codigo);
			} catch (NumberFormatException e) {
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Campo scodigocurso inválido");
			}
		}

		if ((nombre != null) && (!nombre.trim().equals(""))) {
			try {
				Long.parseLong(nombre);
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Campo snombrecurso inválido");
			} catch (NumberFormatException e) {
				// Si es texto
			}
		}

		if (nivel == null) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("campo snivel inavlido");
		} else {
			if (nivel.equals("")) {
				ex.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("campo snivel inavlido");
			}
		}
		
		ex.setProperty("snombrecurso", nombre);
		ex.setProperty("scodigocurso", codigo);
		ex.setProperty("snivel", nivel);
	}

}
