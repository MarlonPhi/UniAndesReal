package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.Acudiente;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionAcudiente;
import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class InformacionAcudienteProcessor implements Processor {
	private final static Logger log = Logger.getLogger("");

	
	public void process(Exchange exchange) throws Exception {
		log.info("ENTRANDO A PROCESSOR DE ACUDIENTE");
		
		InformacionAcudiente response = new InformacionAcudiente();
		
		//------------- ESTUDIANTE -------------------------------------------------------------
		Estudiante e = new Estudiante();
		String identificaciones =   exchange.getProperty("identificaciones", String.class);
		
		identificaciones = identificaciones.replace("\"","");
		identificaciones = StringUtils.substringBetween(identificaciones, "{","}");
		log.info("verificando identificaciones sin comillas: "+ identificaciones);
		String[] split = identificaciones.split(",");
		Map<String, Object> estMap = new HashMap<String, Object>();
		
		for (String pair : split) {
			log.info("veridicando pair:"+ pair);
			String[] entry = pair.split(":");
			estMap.put(entry[0].trim(), entry[1].trim());
		}
		
		log.info("verificando map pidm: "+ estMap.get("pidm"));
		
	    //Estudiante
    	e.setSpidm(UtilsAcademico.getColumnString(estMap.get("pidm")));
    	e.setSlogin(UtilsAcademico.getColumnString(estMap.get("login")));
    	e.setScodigo(UtilsAcademico.getColumnString(estMap.get("codigo")));
    	
    	
    	//------------- PERSONA -------------------------------------------------------------
    	
    	//Datos que va en persona
    	//Datos datos = exchange.getProperty("datos", Datos.class);
    	String datos = exchange.getProperty("datos", String.class);
    	Persona persona = new Persona();
    	ArrayList<Persona> p = new ArrayList<Persona>();
    	Acudiente a = new Acudiente();
    	
    	datos = datos.replace("\"","");
    	datos = StringUtils.substringBetween(datos, "{","}");
    	log.info("verificando identificaciones sin comillas: "+ datos);
		String[] splitPersona = datos.split(",");
		Map<String, Object> personaMap = new HashMap<String, Object>();
		
		for (String pair : splitPersona) {
			log.info("veridicando pair:"+ pair);
			String[] entry = pair.split(":");
			personaMap.put(entry[0].trim(), entry[1].trim());
		}
    	
	    persona.setSprimerNombre(UtilsAcademico.getColumnString(personaMap.get("primerNombre")));
	    persona.setSsegundoNombre(UtilsAcademico.getColumnString(personaMap.get("segundoNombre")));
	    persona.setSprimerApellido(UtilsAcademico.getColumnString(personaMap.get("primerApellido")));
	    persona.setSsegundoApellido(UtilsAcademico.getColumnString(personaMap.get("segundoApellido")));
	    p.add(persona);
    	
    	//------------- IDENTIFICACIONES (DOCUMENTOS)-------------------------------------------------------------
    	
    	//Documento va en identicacion estudiante
    			//Documento docIdent = exchange.getProperty("documentos", Documento.class);
	    String documentos = exchange.getProperty("documentos", String.class);
    	IdentificacionEstudiante i = new IdentificacionEstudiante();
    	
    	
    	documentos = documentos.replace("\"","");
    	documentos = StringUtils.substringBetween(documentos, "[{","}]");
    	
    	log.info("verificando identificaciones sin comillas: "+ documentos);
		String[] splitIdent = documentos.split(",");
		Map<String, Object> identMap = new HashMap<String, Object>();
		
		for (String pair : splitIdent) {
			log.info("veridicando pair:"+ pair);
			String[] entry = pair.split(":");
			identMap.put(entry[0].trim(), entry[1].trim());
		}
    	
		//Identificacion
		   i.setsTipoDocumento(UtilsAcademico.getColumnString(identMap.get("tipoDocumento")));
		   i.setsNumeroDocumento(UtilsAcademico.getColumnString(identMap.get("numeroDocumento")));
		   i.setdFechaExpedicion(UtilsAcademico.getColumnString(identMap.get("fechaExpedicion")));
		   i.setdFechaExpiracion(UtilsAcademico.getColumnString(identMap.get("fechaVencimiento")));
		   i.setbActivo(UtilsAcademico.getColumnBooleanFromString(identMap.get("activo")));
    	
    	//------------- RESPONSABLE ECONOMICO -------------------------------------------------------------
    	
	    	List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
	    	log.info("verificando resultset de acudiente:::::::"+ resultSet);
			
	    	//for (Map<String, Object> row : resultSet) {
	    	Map<String, Object> row = resultSet.get(0);
						  
				    a.setsPrimerNombre(UtilsAcademico.getColumnString(row.get("PRIMER_NOMBRE")));
				    a.setsSegundoNombre(UtilsAcademico.getColumnString(row.get("SEGUNDO_NOMBRE")));
				    a.setsApellidos(UtilsAcademico.getColumnString(row.get("APELLIDOS")));
				    a.setsParentesco(UtilsAcademico.getColumnString(row.get("PARENTESCO")));
				    a.setsTipoDocumento(UtilsAcademico.getColumnString(row.get("TIPO_DOCUMENTO")));
				    a.setsNumeroDocumento(UtilsAcademico.getColumnString(row.get("DOCUMENTO")));
				    log.info("ssetNumeroDocumento:"+ a.getsNumeroDocumento());
					
	
			//}
		
		response.setEstudiante(e);
		response.setPersona(p);
		response.setIdentificacion(i);
		response.setAcudiente(a);
		
		String resp = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", resp);
		
		exchange.getIn().setBody(response);

	}

}
