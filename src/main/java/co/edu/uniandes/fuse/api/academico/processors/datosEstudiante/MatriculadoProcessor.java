package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaEsMatriculado;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.SalidaEsMatriculado;

public class MatriculadoProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		RespuestaEsMatriculado response = new RespuestaEsMatriculado();
		SalidaEsMatriculado salida = new SalidaEsMatriculado();
		List<Map<String, Object>> estadoMatricula = (List<Map<String, Object>>) exchange.getIn().getBody();
		
		if (!estadoMatricula.isEmpty()) {
			salida.setEsMatriculado(true);
			
		}else {
			salida.setEsMatriculado(false);
			
		}
		
		response.setSalida(salida);
		exchange.getIn().setBody(response);
	}

}
