package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionPersonalEstudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class InformacionPersonalEstudianteProcessor implements Processor{
	
	public final  Logger log = Logger.getLogger("");
	ResponseCode rCode = new ResponseCode();
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		InformacionPersonalEstudiante response = new InformacionPersonalEstudiante();
		Estudiante e = new Estudiante();
		Persona p = new Persona();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<Map<String,Object>> person = (List<Map<String, Object>>) exchange.getProperty("namesPerson");
		
		if (resultSet != null && !resultSet.isEmpty()) {
			for (Map<String, Object> map : resultSet) {
				e.setScodigo(UtilsAcademico.getColumnString(map.get("CODIGO")));
				e.setSpidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("LLAVE_ESTUDIANTE")).toString());
				e.setSlogin(UtilsAcademico.getColumnString(map.get("USR_LDAP")));
			}
			if (!person.isEmpty() && person != null) {
				for (Map<String, Object> map : person) {
					p.setSprimerNombre(UtilsAcademico.getColumnString(map.get("PRIMER_NOMBRE")));
					p.setSsegundoNombre(UtilsAcademico.getColumnString(map.get("NOMBRE_EST")));
					p.setSprimerApellido(UtilsAcademico.getColumnString(map.get("PRIMER_APELLIDO")));
					p.setSsegundoApellido(UtilsAcademico.getColumnString(map.get("SEGUNDO_APELLIDO")));
				}
			}
			response.setPersona(p);
			response.setEstudiante(e);
			exchange.getIn().setBody(response);
		}else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
}
