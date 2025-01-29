package co.edu.uniandes.fuse.api.academico.beans.estudiante;

import org.apache.camel.Exchange;

public class ValidateParamsEstudianteService {
	
	
	public void validatePrestamo (Exchange exchange) {
		String id = exchange.getIn().getHeader("id", String.class );
		String fPrestamo = exchange.getIn().getHeader("dfechaprestamo", String.class );
		String fDevolucion = exchange.getIn().getHeader("dfechadevolucion", String.class );
	
	
		if (fPrestamo.isEmpty() || fPrestamo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [dfechaprestamo].");
		}
		
		if (fDevolucion.isEmpty() || fDevolucion == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [fDevolucion].");
		}
		
		exchange.setProperty("id", id);
		exchange.setProperty("dfechaprestamo", fPrestamo);
		exchange.setProperty("dfechadevolucion", fDevolucion);
	}
	
	public void validateId (Exchange exchange) throws Exception {
		String id = exchange.getIn().getHeader("id", String.class);
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
	}
	
	public void validateLogin (Exchange exchange) throws Exception {
		String login = exchange.getIn().getHeader("slogin", String.class);
		
		if (login.isEmpty() || login == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [slogin].");
		}
		
		exchange.setProperty("slogin", login);
	}
	
	public void validateParamsQuest(Exchange exchange) throws Exception {
		
		String answerout = exchange.getIn().getHeader("answerout", String.class);
		String pregunta = exchange.getIn().getHeader("questtype", String.class);
		String tablename = exchange.getIn().getHeader("tablename", String.class);
		String codein = exchange.getIn().getHeader("codein", String.class);

		
		if ((answerout.isEmpty() || answerout == null) && (pregunta.isEmpty() || pregunta == null) 
				&& (tablename.isEmpty() || tablename == null) && (codein.isEmpty() || codein == null)) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario los campos [answerout, questtype, tablename, codein ].");
		}else {
			if (answerout.isEmpty() || answerout == null) {
				exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesario el campo requerido [answerout].");
			}
			
			if (pregunta.isEmpty() || pregunta == null) {
				exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesario el campo requerido [questtype].");
			}
			
			if (tablename.isEmpty() || tablename == null) {
				exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesario el campo requerido [tablename].");
			}
			
			if (codein.isEmpty() || codein == null) {
				exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesario el campo requerido [codein].");
			}
		}
		
		exchange.setProperty("answerout", answerout);
		exchange.setProperty("questtype", pregunta);
		exchange.setProperty("tablename", tablename);
		exchange.setProperty("codein", codein);
	}
	
	
	public void validateParamsPeriods( Exchange ex) throws Exception {
		
		String fechaInic = ex.getIn().getHeader("fechainicial", String.class);
		String fechaFin = ex.getIn().getHeader("fechafinal", String.class);
		String tipoPeriodo = ex.getIn().getHeader("tiposperiodo", String.class);
		String anio = ex.getIn().getHeader("anio", String.class);
		
		if(fechaInic.isEmpty() && fechaFin.isEmpty() && tipoPeriodo.isEmpty() && anio.isEmpty()) {
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos.");
		}
		
		if(!fechaInic.isEmpty() && !fechaFin.isEmpty() && !tipoPeriodo.isEmpty() && !anio.isEmpty()) {
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("No es posible realizar la petición con los 4 campos.");
		}
		
		if((!fechaInic.isEmpty() && !anio.isEmpty()) || (!fechaFin.isEmpty() && !anio.isEmpty()) || (!tipoPeriodo.isEmpty() && !anio.isEmpty()) 
				|| (!fechaInic.isEmpty() && fechaFin.isEmpty() && anio.isEmpty()) || (!fechaInic.isEmpty() && !tipoPeriodo.isEmpty() && !anio.isEmpty())
				|| (!fechaFin.isEmpty() && !anio.isEmpty() && !tipoPeriodo.isEmpty())) {
			
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("No es posible realizar la petición de esta manera.");
		}
		
		ex.setProperty("anio", anio);
		ex.setProperty("tipoPeriodo", tipoPeriodo);
		ex.setProperty("fechaFin", fechaFin);
		ex.setProperty("fechaInic", fechaInic);
		
	}

}
