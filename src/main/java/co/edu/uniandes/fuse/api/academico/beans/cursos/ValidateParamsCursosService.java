package co.edu.uniandes.fuse.api.academico.beans.cursos;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.hamcrest.core.IsInstanceOf;

import co.edu.uniandes.fuse.api.academico.models.cursos.RequestCurso;

public class ValidateParamsCursosService {
	
	
	public final static Logger log = Logger.getLogger("");

	public void validateCursos (Exchange exchange) throws Exception{
		
		String codigo = exchange.getIn().getHeader("codigo", String.class );
		String nombre = java.net.URLDecoder.decode(exchange.getIn().getHeader("nombre", String.class), StandardCharsets.UTF_8.name());
		String curso = exchange.getIn().getHeader("curso", String.class );
	}
	
	public void validateCursosInfo (Exchange exchange) throws Exception{
		
		String codigo = exchange.getIn().getHeader("codigo", String.class );
		String nombre = exchange.getIn().getHeader("nombre", String.class );
		String curso = exchange.getIn().getHeader("curso", String.class );
		String facultad = exchange.getIn().getHeader("facultad", String.class );
		String periodo = exchange.getIn().getHeader("periodo", String.class );
		String crn = exchange.getIn().getHeader("crn", String.class );
		
		if (curso.isEmpty() || curso == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [curso].");
		}
		
		if (periodo.isEmpty() || periodo == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [periodo].");
		}
		
		if (crn.isEmpty() || crn == null) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario el campo requerido [crn].");
		}
		
		exchange.setProperty("scodigodpto", codigo.trim());
		exchange.setProperty("snombrecurso", nombre.trim());
		exchange.setProperty("scodigocurso", curso.trim());
		exchange.setProperty("sfacultad", facultad.trim());
		exchange.setProperty("scrn", crn.trim());
		exchange.setProperty("speriodo", periodo.trim());
		
	}
	
	public void validateArrayCursosInfo(Exchange ex)throws Exception{
		log.info("ENTRANDO A VALIDACION....");
		List<RequestCurso> datosCursos = (List<RequestCurso>)ex.getIn().getBody(); //array de cursos
		
		if (!datosCursos.isEmpty()) {
			ArrayList<Integer> position = new ArrayList<>();
			String positionS = "";
			boolean positionCero = false;
			List datosCursosLista = new ArrayList<>();
			for (int i = 0; i < datosCursos.size(); i++) {
				Integer curso, periodo, crn;
				curso = datosCursos.get(i).getCurso();
				periodo = datosCursos.get(i).getPeriodo();
				crn = datosCursos.get(i).getCrn();
				if(curso == 0 || periodo == 0 || crn == 0) {
					positionCero = true;
				}
				
				
			}
			
			
			ex.setProperty("listObjects", datosCursos.size());
			ex.setProperty("listArrayDatosCursos", datosCursos);
			
			if (positionCero == true) {
				ex.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesario declarar totalmente 'curso', 'periodo' y 'crn', en el array.");
			}
		}else{
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Es necesario definir el array de objetos en el cuerpo de la petición.");
		}
	}
		
}
