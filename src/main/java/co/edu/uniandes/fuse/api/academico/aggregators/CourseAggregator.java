package co.edu.uniandes.fuse.api.academico.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.cursos.InfoCursos;
import co.edu.uniandes.fuse.api.academico.models.cursos.ListResInfoCursosv2;
import co.edu.uniandes.fuse.api.academico.models.cursos.ResponseInfoCursos;
import io.swagger.annotations.Info;

public class CourseAggregator implements AggregationStrategy {

	private final static Logger log = Logger.getLogger("");
	
	
	@Override
	public Exchange aggregate(Exchange oldEx, Exchange newEx) {
		
		log.info("-------------- ENTRANDO A AGREGATOR CURSOS --------------------");
		ResponseInfoCursos r;
		if (oldEx != null) {
			 r = oldEx.getIn().getBody(ResponseInfoCursos.class);
		}else {
			r = new ResponseInfoCursos();
			log.info("se INICIALIZO RESPONSE INFO CURSO");
		}
		
		List<InfoCursos> lic = (List<InfoCursos>)newEx.getIn().getBody();
		r.getCursos().addAll(lic); // implemented array in class for add list or object of new Exchange
		
		newEx.getIn().setBody(r);
				
		return newEx;
	}

}
