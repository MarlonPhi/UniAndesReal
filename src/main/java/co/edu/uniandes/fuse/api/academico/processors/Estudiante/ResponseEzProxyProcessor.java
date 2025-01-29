package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class ResponseEzProxyProcessor implements Processor {
	
	private final static Logger log = Logger.getLogger("");

	@Override
	public void process(Exchange exchange) throws Exception {
		
		boolean entrada = exchange.getProperty("estado", Boolean.class);
		String message = exchange.getProperty("message", String.class);
		boolean ValidacionDA = exchange.getProperty("ValidacionDA", Boolean.class);
		String cliente = exchange.getProperty("cliente", String.class);
		boolean esPublico = exchange.getProperty("esPublico", Boolean.class);
		
		String response = "";
		int perfilValido = 0;
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		
		for (Map<String, Object> map : resultSet) {
			perfilValido = UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("TOTAL_PERFILES_VALIDOS"));
		}
		if (esPublico) {
				if (perfilValido > 0) {
					response = "+Usuario_Publico_OK";
				}else {
					response = "+Sin perfil válido para ezproxy: " + cliente;
				}
			
		}else {
			if (entrada == true) {
				if (ValidacionDA == true) {
					if (resultSet != null && !resultSet.isEmpty()) {
							log.info("verificando el perfil valido: "+ perfilValido);
							if (perfilValido > 0) {
								response = "+Usuario_Uniandes_OK";
							}else {
								response = "+Sin perfil válido para ezproxy: " + cliente;
							}
						
					}else {
						response = "+Error consulta";
					}
				}else {
					response = "+Usuario/Clave inválidos";
				}
			}
		}
		
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "plain/text; charset=UTF-8");
		exchange.getIn().setBody(response, String.class);
	}

}
