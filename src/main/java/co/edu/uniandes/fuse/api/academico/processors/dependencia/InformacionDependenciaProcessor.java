package co.edu.uniandes.fuse.api.academico.processors.dependencia;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InformacionDependenciaProcessor implements Processor{
	@Override
	public void process(Exchange exchange) throws Exception {
		String response = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response , new TypeReference<Map<String, Object>>() {});
		exchange.getOut().setBody(map);
		exchange.setProperty("responseAudit", map);
		
	}

}
