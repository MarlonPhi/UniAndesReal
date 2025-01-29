package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.MessageHelper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InformacionDoctoralProcessor implements Processor {

	private final static Logger log = Logger.getLogger("");
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String response = exchange.getIn().getBody(String.class);
		
		log.info("verify response --> "+ response);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map  = mapper.readValue(response, new TypeReference<Map<String,Object>>(){});
		
		boolean isEmpt = false;
		
		Integer totalStudents = Integer.parseInt(map.get("totalStudents").toString());
			if(totalStudents < 1) {
				isEmpt = true;
			}
		
		
		if (isEmpt == false) {
			exchange.getOut().setBody(map);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
	}

}
