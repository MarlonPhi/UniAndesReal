package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaBecado;
import co.edu.uniandes.fuse.api.academico.models.entity.Becado;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class BecadoProcessor implements Processor{
	
	public final  Logger log = Logger.getLogger("");

	@Override
	public void process(Exchange exchange) throws Exception {
		
		List becados = new ArrayList<>();
		RespuestaBecado response = new RespuestaBecado();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> map : resultSet) {
		    	  Becado becado = new Becado();
		            becado.setsPidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("PIDM")).intValue());
		            becado.setsCodigoBeca(UtilsAcademico.getColumnString(map.get("CODIGO_BECA")));
		            becado.setsDescripcionBeca(UtilsAcademico.getColumnString(map.get("DESCRIPCION_BECA")));
		            becado.setsSemestre(UtilsAcademico.getColumnString(map.get("SEMESTRE_BECAS")));
		            becados.add(becado);
		            
		            
		    }
		    
		    response.setBecados(becados);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		//log.info("verificando response estudiante --->"+ response.getBecados());
		
		exchange.getIn().setBody(response);
		
		
		
	}

}
