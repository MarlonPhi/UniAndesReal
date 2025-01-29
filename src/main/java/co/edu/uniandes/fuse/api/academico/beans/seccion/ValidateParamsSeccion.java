package co.edu.uniandes.fuse.api.academico.beans.seccion;

import org.apache.camel.Exchange;

public class ValidateParamsSeccion {
	
	public void vSeccionCRN (Exchange exchange) throws Exception{
		
		String nombreCurso = exchange.getIn().getHeader("snombreCurso",String.class);
		String codigoCurso = exchange.getIn().getHeader("scodigoCurso",String.class);
		String seccion = exchange.getIn().getHeader("sseccion",String.class);
		String periodo = exchange.getIn().getHeader("speriodo",String.class);
		
		if (((nombreCurso.isEmpty()) || (nombreCurso == null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
			((seccion.isEmpty()) || (seccion == null)) && ((periodo.isEmpty()) || (periodo == null))) {
				
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Son necesarios los campos requeridos.");
				
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
				((seccion.isEmpty()) || (seccion == null)) && ((periodo.isEmpty()) || (periodo == null))) {
				
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Son necesarios los campos requeridos: [scodigoCurso, sseccion, speriodo].");
				
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((!codigoCurso.isEmpty()) || (codigoCurso != null)) &&
				((seccion.isEmpty()) || (seccion == null)) && ((periodo.isEmpty()) || (periodo == null))) {
			
				exchange.setProperty("HttpErrorCode", "http.code.bad.request");
				throw new IllegalArgumentException("Son necesarios los campos requeridos: [sseccion, speriodo].");
				
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((!codigoCurso.isEmpty()) || (codigoCurso != null)) &&
				((!seccion.isEmpty()) || (seccion != null)) && ((periodo.isEmpty()) || (periodo == null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [speriodo].");
			
		}else if(((nombreCurso.isEmpty()) || (nombreCurso == null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
				((seccion.isEmpty()) || (seccion == null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [snombreCurso, scodigoCurso, sseccion].");
			
		}else if (((nombreCurso.isEmpty()) || (nombreCurso == null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
				((!seccion.isEmpty()) || (seccion != null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [snombreCurso, scodigoCurso].");
			
		}else if(((nombreCurso.isEmpty()) || (nombreCurso == null)) && ((!codigoCurso.isEmpty()) || (codigoCurso != null)) &&
				((!seccion.isEmpty()) || (seccion != null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [snombreCurso].");
			
		}else if(((nombreCurso.isEmpty()) || (nombreCurso == null)) && ((!codigoCurso.isEmpty()) || (codigoCurso != null)) &&
				((!seccion.isEmpty()) || (seccion != null)) && ((periodo.isEmpty()) || (periodo == null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [snombreCurso, speriodo].");
			
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
				((seccion.isEmpty()) || (seccion == null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [scodigoCurso, sseccion].");
			
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((!codigoCurso.isEmpty()) || (codigoCurso != null)) &&
				((seccion.isEmpty()) || (seccion == null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [sseccion].");
			
		}else if(((!nombreCurso.isEmpty()) || (nombreCurso != null)) && ((codigoCurso.isEmpty()) || (codigoCurso == null)) &&
				((!seccion.isEmpty()) || (seccion != null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [scodigoCurso].");
		}
		
		
		exchange.setProperty("nombreCurso", nombreCurso);
		exchange.setProperty("codigoCurso", codigoCurso);
		exchange.setProperty("seccion", seccion);
		exchange.setProperty("periodo", periodo);
		
	}
	
	public void vSeccionCRNPeriodo (Exchange exchange) throws Exception {
		
		String crn = exchange.getIn().getHeader("crn",String.class);
		String periodo = exchange.getIn().getHeader("periodo",String.class);
		
		if (((crn.isEmpty()) || (crn == null)) && ((periodo.isEmpty()) || (periodo == null))) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [crn, periodo].");
		}else if (((!crn.isEmpty()) || (crn != null)) && ((periodo.isEmpty()) || (periodo == null))) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [periodo].");
		}else if (((crn.isEmpty()) || (crn == null)) && ((!periodo.isEmpty()) || (periodo != null))) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos: [crn].");
		}
		
		exchange.setProperty("crn", crn);
		exchange.setProperty("periodo", periodo);
	}
	
	public void vSeccionHorarios (Exchange exchange) throws Exception {
		
		String nombreCurso = exchange.getIn().getHeader("snombreCurso", String.class);
		String codigoCurso = exchange.getIn().getHeader("scodigoCurso", String.class);
		String periodo = exchange.getIn().getHeader("speriodo", String.class);
		String crn = exchange.getIn().getHeader("scrn", String.class);
		String seccion = exchange.getIn().getHeader("sseccion", String.class);
		

		
		if ((nombreCurso.isEmpty()) || (nombreCurso == null)) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido: [snombreCurso].");
		}else if((codigoCurso.isEmpty()) || (codigoCurso == null)) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido: [scodigoCurso].");
		}else if((periodo.isEmpty()) || (periodo == null)) {
			exchange.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido: [speriodo].");
		}
		
		
		exchange.setProperty("SUBJCODE", nombreCurso);
		exchange.setProperty("CODE", codigoCurso);
		exchange.setProperty("PERIODO", periodo);
		exchange.setProperty("CRN", crn);
		exchange.setProperty("SECCION", seccion);
		
		
	}

}
