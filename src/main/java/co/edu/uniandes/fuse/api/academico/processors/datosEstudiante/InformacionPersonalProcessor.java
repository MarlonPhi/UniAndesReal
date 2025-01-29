package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaInformacionPersonal;

public class InformacionPersonalProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		RespuestaInformacionPersonal response = new RespuestaInformacionPersonal();
		String resParamQuery = exchange.getProperty("responsePP", String.class);
		
		if (!resParamQuery.isEmpty() && !resParamQuery.equals("")) {
			
			response.setsRespuesta(resParamQuery);
			
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		exchange.getIn().setBody(response);
		
	}

}
