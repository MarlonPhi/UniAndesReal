package co.edu.uniandes.fuse.api.academico.beans.profesor;

import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;

public class ValidateParamsProfesorService {
	
	public void validateSeccion (Exchange exchange) throws Exception {
		String id = exchange.getIn().getHeader("id", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String crn = exchange.getIn().getHeader("crn", String.class );
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		
		exchange.setProperty("id", id);
		exchange.setProperty("periodo", periodo);
		exchange.setProperty("crn", crn);
	}
	
	public void validateIdProfesor (Exchange exchange) throws Exception {
		String idProfesor = exchange.getIn().getHeader("idProfesor", String.class);

		if (idProfesor.isEmpty() || idProfesor == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [idProfesor].");
		}
		if (idProfesor.matches("^[0-9]+$")) {
			exchange.setProperty("idProfesor", idProfesor);
		}
	}
	
	public void validateIdSLProfesor (Exchange exchange) throws Exception {
		String idProfesor = exchange.getIn().getHeader("idProfesor", String.class);
		String start = exchange.getIn().getHeader("start", String.class);
		String limit = exchange.getIn().getHeader("limit", String.class);

		if (idProfesor.matches("^[0-9]+$")) {
			exchange.setProperty("idProfesor", idProfesor);
		}
		exchange.setProperty("start", start);
		exchange.setProperty("limit", limit);
	}
	
	public void validateLogin (Exchange exchange) throws Exception {
		String login = exchange.getIn().getHeader("login", String.class );
		
		if (login.isEmpty() || login == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [login].");
		}
		if (login.matches("[a-zA-Z_0-9]+.+$")) {
			exchange.setProperty("login", login);
		}
		
	}
	
	
	

}
