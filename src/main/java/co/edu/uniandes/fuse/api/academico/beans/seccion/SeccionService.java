package co.edu.uniandes.fuse.api.academico.beans.seccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.entity.Horarios;
import co.edu.uniandes.fuse.api.academico.models.entity.Nota;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.models.entity.Profesor;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseCrn;
import co.edu.uniandes.fuse.api.academico.models.seccion.ResponseSeccionCupos;
import co.edu.uniandes.fuse.api.academico.models.seccion.ResponseSeccionNotas;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionCupos;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionEstudiantes;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionHorarios;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.Curso;
import co.edu.uniandes.model.CursoSeccion;

public class SeccionService {
	
	ResponseCode rCode = new ResponseCode();
	private final static Logger log = Logger.getLogger("");
	/**
	 * Seccion
	 */
	public void getSeccion(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		if (!result.isEmpty()) {
				Map<String, Object> row = result.get(0);
				
				Seccion seccion = new Seccion();
				seccion.setMateria(DataTypes.getColumnString(row.get("MATERIA")));
				seccion.setNombreCorto(DataTypes.getColumnString(row.get("NOMBRE_CORTO")));
				seccion.setNombreLargo(DataTypes.getColumnString(row.get("NOMBRE_LARGO")));
				seccion.setNumero(DataTypes.getColumnString(row.get("NUMERO")));
				seccion.setSeccion(DataTypes.getColumnString(row.get("SECCION")));
				
			exchange.getIn().setBody(seccion);			

		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	
	/**
	 * Estudiantes Seccion
	 */
	public void getSeccionEstudiantes(Exchange exchange) throws Exception {

		List<Map<String, Object>> result = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<SeccionEstudiantes> listaSeccionEst = new ArrayList<>();
		String nombres = "";
		String apellidos = "";
		if (!result.isEmpty() && result != null) {
			
			for (Map<String, Object> row : result) {
				SeccionEstudiantes seccionEst = new SeccionEstudiantes();
				seccionEst.setCodigo(DataTypes.getColumnString(row.get("CODIGO")));
				nombres = (DataTypes.getColumnString(row.get("NOMBRES")));
				apellidos = (DataTypes.getColumnString(row.get("APELLIDOS")));
				seccionEst.setNombre(apellidos + " " + nombres);
				seccionEst.setNombres(nombres);
				seccionEst.setApellidos(apellidos);
				seccionEst.setEmail(DataTypes.getColumnString(row.get("CORREO")));
				seccionEst.setNivel(DataTypes.getColumnString(row.get("NIVEL")));

				listaSeccionEst.add(seccionEst);
			}
			exchange.getIn().setBody(listaSeccionEst);			

		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getSeccionPorCrn(Exchange exchange) throws Exception {
		
		List<Map<String, Object>> resultCrn = exchange.getIn().getBody(ArrayList.class);
		ResponseCrn response = new ResponseCrn();
		
		if (!resultCrn.isEmpty() && resultCrn != null) {
			for (Map<String, Object> map : resultCrn) {
				response.setCrn(DataTypes.getColumnString(map.get("SSBSECT_CRN")));
			}
			exchange.getIn().setBody(response);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getSeccionPorCupos(Exchange exchange) throws Exception {
		
		ResponseSeccionCupos responseSc = new ResponseSeccionCupos();
		List<Map<String, Object>> body = exchange.getIn().getBody(ArrayList.class);
		
		if (!body.isEmpty()) {
			for (Map<String, Object> map : body) {
				
				SeccionCupos sc = new SeccionCupos();
				sc.setCrn(DataTypes.getColumnString(map.get("SSBSECT_CRN")));
				sc.setPeriodo(DataTypes.getColumnString(map.get("SSBSECT_TERM_CODE")));
				sc.setCupoMaximo(Integer.valueOf(UtilsAcademico.getColumnIntegerFromBigDecimalInteger(map.get("SSBSECT_MAX_ENRL")).intValue()));
				sc.setCupoInscritos(Integer.valueOf(UtilsAcademico.getColumnIntegerFromBigDecimalInteger(map.get("SSBSECT_ENRL")).intValue()));
				responseSc.setCursoSeccion(sc);
			}
			exchange.getIn().setBody(responseSc);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getSeccionPorHorarios (Exchange exchange) throws Exception {
		
		SeccionHorarios responseHorarios = new SeccionHorarios();
		
		List<Map<String, Object>> resultBody = exchange.getIn().getBody(ArrayList.class);
		List<Horarios> listH = new ArrayList<>();
		
		
		List<Profesor> listP = new ArrayList<>();

		
		if (!resultBody.isEmpty()) {
			
			for (Map<String, Object> map : resultBody) {
				
				Horarios horario = new Horarios();
				Horarios hAnterior = new Horarios();
				Profesor profesor = new Profesor();
				Profesor profAnt = new Profesor();
				Persona person = new Persona();
				CursoSeccion cs = new CursoSeccion();
				Curso c = new Curso();
				
				horario.setFechaFin(UtilsAcademico.getColumnString(UtilsAcademico.getColumnDate__(map.get("FECHA_FIN")).toString()));
				horario.setFechaInicio(UtilsAcademico.getColumnString(UtilsAcademico.getColumnDate__(map.get("FECHA_INICIO")).toString()));
				horario.setsHoraInicio(UtilsAcademico.getColumnString(map.get("HORAINI")));
				horario.setsHoraFin(UtilsAcademico.getColumnString(map.get("HORAFIN")));
				horario.setsSalon(DataTypes.getColumnString(map.get("SALON")));
				
				horario.setsLunes(DataTypes.getColumnString(map.get("LUNES")));
				horario.setsMartes(DataTypes.getColumnString(map.get("MARTES")));
				horario.setsMiercoles(DataTypes.getColumnEmptyString(map.get("MIERCOLES")));
				horario.setsJueves(DataTypes.getColumnEmptyString(map.get("JUEVES")));
				horario.setsViernes(DataTypes.getColumnEmptyString(map.get("VIERNES")));
				horario.setsSabado(DataTypes.getColumnEmptyString(map.get("SABADO")));
				horario.setsDomingo(DataTypes.getColumnEmptyString(map.get("DOMINGO")));
				
				cs.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				cs.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
				cs.setsSeccion(DataTypes.getColumnString(map.get("SECCION")));
				
				c.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
				c.setsCodigoCurso(DataTypes.getColumnString(map.get("NUMERO")));
				c.setsNombreLargoCurso(DataTypes.getColumnString(map.get("NOMBRE_CURSO")));
				
				 String str = DataTypes.getColumnString(map.get("NOMBRES_PROF"));
				 String[] splited = str.split("\\s+");
				  
				 String str2 = DataTypes.getColumnString(map.get("APELLIDOS_PROF"));
				 String[] splited2 = str2.split("\\s+");
				  
				 person.setSprimerNombre(splited[0]);
				 if(splited.length>1 ){ person.setSsegundoNombre(splited[1]);}
				
				 person.setSprimerApellido(splited2[0]);
				 if(splited2.length>1 ){ person.setSsegundoApellido(splited2[1]);}
				 

		         
				if (horario.getsSalon().equals(hAnterior.getsSalon())) {
					
				}else {
					hAnterior = horario;
					listH.add(horario);
				}
		         
		         
		         profesor.setcPersona(person);
		         profesor.setsLogin(DataTypes.getColumnString(map.get("USUARIO_PROF")));
		         
		        if (profesor.getsLogin() == null || profesor.getsLogin().trim().isEmpty()) {
		        	profesor.setsLogin("");
		        }
		         
				log.info("REVISANDO LOGIN PROFESOR --> PROFESOR ACTUAL -> " + profesor.getsLogin());
		        log.info("REVISANDO LOGIN PROFESOR --> PROFESOR ACTUAL -> " + profAnt.getsLogin());
		        if (!profesor.getsLogin().equals(profAnt.getsLogin())) {
					profAnt= profesor;
					listP.add(profesor);
					
				}
		        //listP.add(profesor);
		        
				responseHorarios.setCurso(c);
				responseHorarios.setCursoseccion(cs);
				responseHorarios.setHorario(listH);
				responseHorarios.setProfesor(listP);
			}

			
			exchange.getIn().setBody(responseHorarios);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getSeccionNotas (Exchange exchange) throws Exception {
		
		ResponseSeccionNotas response = new ResponseSeccionNotas();
		Nota nota = new Nota();
		List<Nota> listNota = new ArrayList<>();
		List<Map<String, Object>> resultBody = exchange.getIn().getBody(ArrayList.class);
		
		if ((!resultBody.isEmpty()) && (resultBody != null)) {
			for (Map<String, Object> map : resultBody) {
				nota.setsCRN(DataTypes.getColumnString(map.get("SHRTCKN_CRN")));
				nota.setsNota(DataTypes.getColumnString(map.get("SHRTCKG_GRDE_CODE_FINAL")));
				nota.setsPidm(DataTypes.getColumnString(map.get("SHRTCKN_PIDM")));
				nota.setdFechaNota(map.get("SHRTCKN_PTRM_END_DATE").toString());
				
				listNota.add(nota);
			}
			response.setNotas(listNota);
			exchange.getIn().setBody(response);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
}

