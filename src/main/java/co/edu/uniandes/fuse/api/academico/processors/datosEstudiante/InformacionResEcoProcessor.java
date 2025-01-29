package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.Acudiente;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionAcudiente;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionResEconomico;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.ResponsableEconomico;
import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class InformacionResEcoProcessor implements Processor {
	private final static Logger log = Logger.getLogger("");

	
	public void process(Exchange exchange) throws Exception {
		log.info("ENTRANDO A PROCESSOR DE ACUDIENTE");
		
		//ArrayList<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		
		
		InformacionResEconomico response = new InformacionResEconomico();
		
		//------------- ESTUDIANTE -------------------------------------------------------------
		Estudiante e = new Estudiante();
		String identificaciones =   exchange.getProperty("identificaciones", String.class);
		
		identificaciones = identificaciones.replace("\"","");
		identificaciones = StringUtils.substringBetween(identificaciones, "{","}");
		String[] split = identificaciones.split(",");
		Map<String, Object> estMap = new HashMap<String, Object>();
		
		for (String pair : split) {
			String[] entry = pair.split(":");
			estMap.put(entry[0].trim(), entry[1].trim());
		}
		
		
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
    	
    	
    	datos = datos.replace("\"","");
    	datos = StringUtils.substringBetween(datos, "{","}");
		String[] splitPersona = datos.split(",");
		Map<String, Object> personaMap = new HashMap<String, Object>();
		
		for (String pair : splitPersona) {
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
    	
		String[] splitIdent = documentos.split(",");
		Map<String, Object> identMap = new HashMap<String, Object>();
		
		for (String pair : splitIdent) {
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
		   ResponsableEconomico rec = new ResponsableEconomico();
		   
		   List<Map<String, Object>> correo =  (List<Map<String, Object>>) exchange.getProperty("correoRE");
		   List<Map<String, Object>> correoOpt =  (List<Map<String, Object>>) exchange.getProperty("correoOpt");
		   //log.info("verificando correo en map list ::::: "+ correo.get(0)); // error if map empty
	       List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
	       
	       if (resultSet == null || resultSet.isEmpty() || hasNullValues(resultSet))  {
	    	   setEmptyValues(rec);
	       }else {
	    	   Map<String, Object> row = resultSet.get(0);
	    	   
    		    rec.setsPrimerNombre(UtilsAcademico.getColumnString(row.get("PRIMER_NOMBRE")).trim());
	    		rec.setsSegundoNombre(UtilsAcademico.getColumnString(row.get("SEGUNDO_NOMBRE")).trim());
	    		rec.setsApellidos(UtilsAcademico.getColumnString(row.get("APELLIDOS")).trim());
	    		rec.setsParentesco(UtilsAcademico.getColumnString(row.get("PARENTESCO")).trim());
	    		rec.setsTipoDocumento(UtilsAcademico.getColumnString(row.get("TIPO_DOCUMENTO")).trim());
	    		rec.setsNumeroDocumento(UtilsAcademico.getColumnString(row.get("DOCUMENTO")));
	    		
	    		
	    		if(correo.size() == 0) {
	    			rec.setsCorreo("");
	    		}else {
	    			Map<String, Object> resultCorreo = correo.get(0);
	    			rec.setsCorreo(UtilsAcademico.getColumnString(resultCorreo.get("CORREO")).trim());
	    		}
	    		
	    		 // Manejar correo
	            rec.setsCorreo(correo.isEmpty() ? "" : UtilsAcademico.getColumnString(correo.get(0).get("CORREO")).trim());
	            // Manejar correo opcional
	            rec.setsCorreoOpt1(correoOpt.size() > 0 ? UtilsAcademico.getColumnString(correoOpt.get(0).get("CORREOOPT")) : "");
	            rec.setsCorreoOpt2(correoOpt.size() > 1 ? UtilsAcademico.getColumnString(correoOpt.get(1).get("CORREOOPT")) : "");
	    		
	    		
	    		
	    		//rec.setsCorreoOpt1(UtilsAcademico.getColumnString(correoOpt.get(0).get("CORREOOPT")));
	    		//rec.setsCorreoOpt2(UtilsAcademico.getColumnString(correoOpt.get(1).get("CORREOOPT")));
	    		
	    		log.info("salida setCorreo opt1:"+ rec.getsCorreoOpt1());
	    		log.info("salida setCorreo opt2:"+ rec.getsCorreoOpt2());
	    		//log.info("ssetNumeroDocumento:"+ rec.getsNumeroDocumento());
	    	   
	       }
	       
	       
		response.setEstudiante(e);
		response.setPersona(p);
		response.setIdentificacion(i);
		response.setResponsableEconomico(rec);
		
		String resp = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", resp);
		
		exchange.getIn().setBody(response);
		
		

	}
	
	
	
	
	/**
	 * Metodo para asignar "" a todos los campos de ResponsableEconomico
	 * @param rec - ResponsableEconomico
	 */
	private void setEmptyValues(ResponsableEconomico rec) {
	    rec.setsPrimerNombre("");
	    rec.setsSegundoNombre("");
	    rec.setsApellidos("");
	    rec.setsParentesco("");
	    rec.setsTipoDocumento("");
	    rec.setsNumeroDocumento("");
	    rec.setsCorreo("");
	    rec.setsCorreoOpt1("");
	    rec.setsCorreoOpt2("");
	}
	
	/**
	 * Metodo para verificar si hay mas de 6 nulls en responsable economico
	 * @param result
	 * @return
	 * @throws Exception
	 */
	 public static boolean hasNullValues(List<Map<String, Object>> result) throws Exception {
	        
		 int nullCount = 0;
		 for (Map<String, Object> map : result) {
	            for (Object value : map.values()) {
	                if (value == null) {
	                    nullCount++;
	                    if(nullCount > 6) {
	        	        	return true;
	        	        }
	                }
	            }
	        }
	        return false;
	    }

}
