package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaPreguntaParametrica;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class PreguntaProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		RespuestaPreguntaParametrica response = new RespuestaPreguntaParametrica();
		List<Map<String, Object>> body = (List<Map<String, Object>>) exchange.getIn().getBody();
		String codigo = exchange.getProperty("responsePP", String.class);
		
		if (!body.isEmpty()) {
			
				response.setsRespuestaParametrica(codigo);
		}else {
			new ThrowExceptionDefinition();
		}
		
		String resp = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", resp);
		exchange.getIn().setBody(response);
		
	}

}
