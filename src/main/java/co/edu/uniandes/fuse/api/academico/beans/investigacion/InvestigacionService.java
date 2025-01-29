package co.edu.uniandes.fuse.api.academico.beans.investigacion;

import java.util.Map;

import org.apache.camel.Exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvestigacionService {
	
	
	public void validateParamsGrupos (Exchange exchange) throws Exception{
		
		String sigla = exchange.getIn().getHeader("sigla", String.class);
		String idGroup = exchange.getIn().getHeader("idGroup", String.class);
		String start = exchange.getIn().getHeader("start", String.class);
		String limit = exchange.getIn().getHeader("limit", String.class);
		
		
		exchange.setProperty("sigla", sigla);
		exchange.setProperty("idGroup", idGroup);
		exchange.setProperty("start", start);
		exchange.setProperty("limit", limit);
		
	}
	
	public void getGrupos(Exchange exchange) throws Exception{
		
		String response = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response , new TypeReference<Map<String, Object>>() {});
		exchange.getOut().setBody(map);
		exchange.setProperty("responseAudit", map);
		
	}

}
