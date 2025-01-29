package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import co.edu.uniandes.fuse.api.academico.models.profesor.ProfesorSecciones;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;

public class ProfesorSeccionAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource) {
		// TODO Auto-generated method stub
		
		ProfesorSecciones profesorSeccion= original.getIn().getBody(ProfesorSecciones.class);
		Seccion seccion= resource.getIn().getBody(Seccion.class);
		
		profesorSeccion.setNombreCurso(seccion.getNombreCorto());
		profesorSeccion.setNombreCursoLargo(seccion.getNombreLargo());
		profesorSeccion.setMateria(seccion.getMateria());
		profesorSeccion.setSeccion(seccion.getSeccion());
		profesorSeccion.setNumero(seccion.getNumero());
		
		original.getIn().setBody(profesorSeccion);


        return original;
	}
	
	/*
	 public Exchange aggregate(Exchange original, Exchange resource) {
	        Object originalBody = original.getIn().getBody();
	        Object resourceResponse = resource.getIn().getBody();
	        Object mergeResult = ... // combine original body and resource response

	        if (original.getPattern().isOutCapable()) {
	            original.getOut().setBody(mergeResult);
	        } else {
	            original.getIn().setBody(mergeResult);
	        }

	        return original;
	    }*/

}
