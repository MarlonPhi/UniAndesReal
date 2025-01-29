package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetCodeResultOnBaseProcessor implements Processor {
	
	public final static Logger log = Logger.getLogger("");
	
	public void process(Exchange exchange) throws Exception {
		
		log.info("ENTRANDO A PROCESOR DE CODE STATUS");
		String responseBody = exchange.getIn().getBody(String.class);
		
		//log.warn("verify body http4 ::: "+ responseBody);

        // Convierte el JSON de la respuesta en un mapa de Java usando Jackson
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = mapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
        // Obtiene el valor de 'codResult' del 'resultHeader' del mapa de respuesta
        String codResult = null;
        if (responseMap.containsKey("resultHeader")) {
            Map<String, Object> resultHeader = (Map<String, Object>) responseMap.get("resultHeader");
            codResult = resultHeader.get("codResult").toString();
        }
        String documentId = null;
        if (responseMap.containsKey("resultDocumentSingle")) {
            Map<String, Object> resultDocumentSingle = (Map<String, Object>) responseMap.get("resultDocumentSingle");
            documentId = resultDocumentSingle.get("id").toString();
        }
        exchange.setProperty("idForm", documentId);
        exchange.setProperty("codeResult", codResult);
        exchange.setProperty("bodyOnBase", responseBody);
	}
}
