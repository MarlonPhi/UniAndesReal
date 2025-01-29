package co.edu.uniandes.fuse.api.academico.beans.inscripciones;


import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

public class ValidateParamsInscriptionService {
	
	private final static Logger log = Logger.getLogger("");
	
	public void validateParamsSecciones (Exchange exchange) throws Exception {
		
		String id = exchange.getIn().getHeader("id", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String tipoInscripcion =  exchange.getIn().getHeader("tipoInscripcion", String.class );
		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		
		if (tipoInscripcion.trim().contains(",") && !tipoInscripcion.isEmpty()) {
			String part = ""; String part2= "";
			String ti = tipoInscripcion.trim();
			String ti1 = ti.replace("[", ""); String ti2 = ti1.replace("]", "");
			String tInscripcion = ti2.replace(" ", ""); String[] split = tInscripcion.split(",");
			for (int i = 0; i < split.length; i++) {
				int n = split.length; int nSplit = i+1;
				String string = split[i];
				part2 = "'"+string+"'";
				if (nSplit == n) {
					part += part2;
				}else {
					part += part2+",";
				}
			}
			exchange.setProperty("tipoInscripcion", part);
		}else {
			exchange.setProperty("tinsc", tipoInscripcion);
			tipoInscripcion =  "'"+tipoInscripcion+"'";
			exchange.setProperty("tipoInscripcion",tipoInscripcion);
		}
		
		
		exchange.setProperty("id", id.trim());
		exchange.setProperty("periodo", periodo.trim());
		
	}
	
	public void validateParamsSeccionesCant (Exchange exchange) throws Exception {
		String id = exchange.getIn().getHeader("id", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String tipoInscripcion =  exchange.getIn().getHeader("tipoInscripcion", String.class );
		String nivel =  exchange.getIn().getHeader("nivel", String.class );

		
		if (id.isEmpty() || id == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [id].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		if (tipoInscripcion.isEmpty() || tipoInscripcion == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [tipoInscripcion].");
		}
		
		if (nivel.isEmpty() || nivel == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [nivel].");
		}
		
		if (tipoInscripcion.trim().contains(",")) {
			String part = ""; String part2= "";
			String ti = tipoInscripcion.trim();
			String ti1 = ti.replace("[", ""); String ti2 = ti1.replace("]", "");
			String tInscripcion = ti2.replace(" ", ""); String[] split = tInscripcion.split(",");
			for (int i = 0; i < split.length; i++) {
				int n = split.length; int nSplit = i+1;
				String string = split[i];
				part2 = "'"+string+"'";
				if (nSplit == n) {
					part += part2;
				}else {
					part += part2+",";
				}
			}
			exchange.setProperty("tipoInscripcion", part);
		}else {
			exchange.setProperty("tipoInscripcion", tipoInscripcion);
		}
		
		exchange.setProperty("id", id.trim());
		exchange.setProperty("periodo", periodo.trim());
		exchange.setProperty("nivel", nivel.trim());
	}
	
	public void validateTurno (Exchange exchange) throws Exception {
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
		
		exchange.setProperty("id", id.trim());
		exchange.setProperty("periodo", periodo.trim());
	}

}
