package co.edu.uniandes.fuse.api.academico.beans.inscripciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Inscripcion;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseSecciones;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseSeccionesConteo;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseSeccionesInfo;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseTurno;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.Secciones;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.SeccionesInfo;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.Turno;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class InscripcionesService {
	ResponseCode rCode = new ResponseCode();
	ResponseSecciones rSecciones = new ResponseSecciones();
	ResponseSeccionesInfo rSeccionesInfo = new ResponseSeccionesInfo();
	ResponseSeccionesConteo rSeccConteo = new ResponseSeccionesConteo();
	ResponseTurno rTurno = new ResponseTurno();
	private final static Logger log = Logger.getLogger("");

	public void getSeccionesCantidad(Exchange exchange) throws Exception {
		List<Map<String, Object>> resultSet = exchange.getIn().getBody(ArrayList.class);

		if (!resultSet.isEmpty() && resultSet != null) {
			for (Map<String, Object> map : resultSet) {
				rSeccConteo.setConteoMaterias(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("CONTEO_MATERIAS")));
			}
			exchange.getIn().setBody(rSeccConteo);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getSecciones(Exchange exchange) throws Exception {
		Secciones secciones = new Secciones();
		List<CursoSeccion> listCs = new ArrayList<>();
		List<List<Map<String, Object>>> resultSet = (List<List<Map<String, Object>>>) exchange.getProperty("LISTA");
		
		if (resultSet != null && !resultSet.isEmpty()) {
			for (List<Map<String, Object>> row : resultSet) {
				if (row != null && !row.isEmpty()) {
					for (Map<String, Object> map : row) {
						CursoSeccion cs = new CursoSeccion();
						cs.setsCodigoCurso(DataTypes.getColumnString(map.get("CURSO")));
						cs.setsCRN(DataTypes.getColumnString(map.get("CRN")));
						cs.setsNombreCurso(DataTypes.getColumnString(map.get("NOMBRE_CURSO")));
						cs.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
						cs.setsSeccion(DataTypes.getColumnString(map.get("SECCION")));
						listCs.add(cs);
					}
				}
			}
			secciones.setCursoSeccion(listCs);
			exchange.getIn().setBody(secciones);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getListSecciones(Exchange exchange) throws Exception {
		((List)exchange.getProperty("LISTA")).add((List)exchange.getIn().getBody());
	}
	
	public void getSeccionesInfo(Exchange exchange) throws Exception {
		
		List<SeccionesInfo> listSeccInfo = new ArrayList<>();
		
		List<Map<String, Object>> resultSet =(List<Map<String, Object>>) exchange.getIn().getBody();
		
		if (!resultSet.isEmpty() && resultSet != null) {
			for (Map<String, Object> map : resultSet) {
				Inscripcion inscripcion = new Inscripcion();
				CursoSeccion cs = new CursoSeccion();
				SeccionesInfo seccInfo = new SeccionesInfo();
				
				cs.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				cs.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
				cs.setsCodigoCurso(DataTypes.getColumnString(map.get("CODIGO")));
				cs.setsSeccion(DataTypes.getColumnString(map.get("SECCION")));
				cs.setsCreditos(DataTypes.getColumnString(map.get("CREDS")));
				inscripcion.setsEstadoInscripcion(DataTypes.getColumnString(map.get("ESTADO_INSCRIPCION")));
				inscripcion.setdFechaActividad(DataTypes.getColumnString(map.get("FECHA_ACTIVIDAD")));
				inscripcion.setsTipoRegistro(DataTypes.getColumnString(map.get("TIPO_REGISTRO")));
				inscripcion.setsPeriodo(DataTypes.getColumnString(map.get("SEMESTRE")));
				inscripcion.setsNivel(DataTypes.getColumnString(map.get("NIVEL_INSCRIPCION_MATERIA")));
				
				seccInfo.setCursoSeccion(cs);
				seccInfo.setNotaParcial(DataTypes.getColumnString(map.get("NOTA_PARCIAL")));
				seccInfo.setInscripcion(inscripcion);
				
				listSeccInfo.add(seccInfo);
			}
			rSeccionesInfo.setSeccionesInfo(listSeccInfo);
			exchange.getIn().setBody(rSeccionesInfo);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getTurno(Exchange exchange) throws Exception {
		
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<Turno> listaTurno = new ArrayList<>();
		
		if (resultSet != null && !resultSet.isEmpty()) {
			
			for (Map<String, Object> map : resultSet) {
				Turno turno = new Turno();
				
				turno.setNombreTurno(DataTypes.getColumnString(map.get("TURNO")));
		        turno.setPrioridad(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("PRIORIDAD")).toString());
		        turno.setPrioridadGrupo(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("PRIORIDAD_GRUPO")).toString());
		        turno.setFechaInicio(UtilsAcademico.getDateFromString(DataTypes.getColumnString(map.get("FECHA_INI"))));
		        //turno.setFechaInicio(Util.getColumnDate_(map.get("FECHA_INI")));
		        turno.setHoraInicio(DataTypes.getColumnString(map.get("HORA_INI")));
		        turno.setFechaFin(UtilsAcademico.getDateFromString(DataTypes.getColumnString(map.get("FECHA_FIN"))));
		        //turno.setFechaFin(Util.getColumnDate_(map.get("FECHA_FIN")));
		        turno.setHoraFin(DataTypes.getColumnString(map.get("HORA_FIN")));
		        listaTurno.add(turno);
			}
			rTurno.setTurno(listaTurno);
			exchange.getIn().setBody(rTurno);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}

}
