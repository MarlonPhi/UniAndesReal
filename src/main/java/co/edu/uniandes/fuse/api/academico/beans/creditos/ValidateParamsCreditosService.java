package co.edu.uniandes.fuse.api.academico.beans.creditos;

import org.apache.camel.Exchange;

public class ValidateParamsCreditosService {
	
	public void validatePerNiv (Exchange exchange) throws Exception {

		String id = exchange.getIn().getHeader("id", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String nivel = exchange.getIn().getHeader("nivel", String.class );
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		if (nivel.isEmpty() || nivel == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [nivel].");
		}

		exchange.setProperty("periodo", periodo);
		exchange.setProperty("nivel", nivel);
	}
	
	public void validateId (Exchange exchange) throws Exception {
		String id = exchange.getIn().getHeader("id", String.class);
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
	}
	
	public void validateNiv (Exchange exchange) throws Exception {
		String nivel = exchange.getIn().getHeader("nivel", String.class );
		
		if (nivel.isEmpty() || nivel == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [nivel].");
		}
	}
	
	public void validateCodCred (Exchange exchange) throws Exception {
		
		String creditosInt = exchange.getIn().getHeader("creditosIntentados", String.class);
		String codPrograma = exchange.getIn().getHeader("codigoPrograma", String.class );
		
		if (creditosInt.isEmpty() || creditosInt == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [creditosIntentados].");
		}
		
		if (codPrograma.isEmpty() || codPrograma == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [codigoPrograma].");
		}
		
		exchange.setProperty("creditosIntentados", creditosInt);
		exchange.setProperty("codigoPrograma", codPrograma);
		
	}
	
	public void validatePer (Exchange exchange) throws Exception{
		String id = exchange.getIn().getHeader("id", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		exchange.setProperty("periodo", periodo);
	}

}
