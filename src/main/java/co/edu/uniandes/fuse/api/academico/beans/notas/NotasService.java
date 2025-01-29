package co.edu.uniandes.fuse.api.academico.beans.notas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Nota;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.models.entity.Profesor;
import co.edu.uniandes.fuse.api.academico.models.notas.PeriodosCalificadosMaxMin;
import co.edu.uniandes.fuse.api.academico.models.notas.CalificadosMaxMIn;
import co.edu.uniandes.fuse.api.academico.models.notas.NotaEstudiante;
import co.edu.uniandes.fuse.api.academico.models.notas.NotaHistorico;
import co.edu.uniandes.fuse.api.academico.models.notas.NotaHomologada;
import co.edu.uniandes.fuse.api.academico.models.notas.NotasSicua;
import co.edu.uniandes.fuse.api.academico.models.notas.PromedioAcumulado;
import co.edu.uniandes.fuse.api.academico.models.notas.PromedioSeccion;
import co.edu.uniandes.fuse.api.academico.models.notas.PromedioSemestre;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.Curso;
import co.edu.uniandes.model.NotaParcial;

public class NotasService {
	ResponseCode rCode = new ResponseCode();
	PromedioAcumulado responsePAcumulado = new PromedioAcumulado();
	PromedioSeccion responsePromedioSeccion = new PromedioSeccion();
	PromedioSemestre respPromedioSemestre = new PromedioSemestre();
	CalificadosMaxMIn respCalificados = new CalificadosMaxMIn();
	NotasSicua respNotasSicua = new NotasSicua();
	
	public void getNotasHomologadas (Exchange exchange) throws Exception{
		
		List<Map<String, Object>> resultSet = exchange.getIn().getBody(List.class);
		List<NotaHomologada> notaH = new ArrayList<>();

		if ((resultSet != null) || (!resultSet.isEmpty())) {

			for (Map<String, Object> map : resultSet) {
				NotaHomologada h = new NotaHomologada();
				Nota n = new Nota();
				CursoSeccion cs = new CursoSeccion();
				cs.setsCreditos(UtilsAcademico.getColumnFloatFromBigDecimal(map.get("CREDITOS")).toString());
				n.setsCodigoCurso(UtilsAcademico.getColumnString(map.get("COD_CURSO")));
				n.setsNivel(UtilsAcademico.getColumnString(map.get("NIVEL_CURSO")));
				n.setsNombreCurso(UtilsAcademico.getColumnString(map.get("NOMBRE_CURSO")));
				n.setsNota(UtilsAcademico.getColumnString(map.get("NOTA")));
				n.setsPeriodo(UtilsAcademico.getColumnString(map.get("PERIODO")));
				n.setsPidm(new DecimalFormat("#.##").format(UtilsAcademico.getColumnFloatFromBigDecimal(map.get("PIDM"))));
				
				h.setNota(n);
				h.setCursoSeccion(cs);
				notaH.add(h);
			}
			exchange.getIn().setBody(notaH);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getNotasPromedioAcumulado (Exchange exchange) throws Exception{
		
		List<Map<String, Object>> resultSet = exchange.getIn().getBody(List.class);
		
		if ((resultSet != null) || (!resultSet.isEmpty())) {

			Map sql = (Map) resultSet.get(0);
			responsePAcumulado.setPromedioAcumuladoEstudiante(UtilsAcademico.getColumnFloatFromBigDecimal(sql.get("PROM_SEM_REGULARES")).toString());
			exchange.getIn().setBody(responsePAcumulado);
			
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getNotasEstudiante (Exchange exchange) throws Exception {
		
		List<Map<String, Object>> resultGet = exchange.getIn().getBody(List.class);
		List<NotaEstudiante> listNe = new ArrayList<>();
		
		if ((resultGet != null) || (!resultGet.isEmpty())) {
			
			for (Map<String, Object> map : resultGet) {
				NotaEstudiante ne = new NotaEstudiante();
				Nota n = new Nota();
				CursoSeccion cs = new CursoSeccion();
				
				  cs.setsCRN(DataTypes.getColumnString(map.get("CRN")));
		          cs.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
		          cs.setsCodigoCurso(DataTypes.getColumnString(map.get("CODIGO")));
		          cs.setsSeccion(DataTypes.getColumnString(map.get("SECCION")));
		          cs.setsCreditos(UtilsAcademico.getColumnFloatFromBigDecimal(map.get("CREDS")).toString());
	
		          n.setsPidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("LLAVE_ESTUDIANTE")).toString());
		          n.setsPeriodo(DataTypes.getColumnString(map.get("SEMESTRE")));
		          n.setsNivel(DataTypes.getColumnString(map.get("NIVEL_HA")));
		          n.setsNota(DataTypes.getColumnString(map.get("NOTA")));
		          n.setdFechaNota(DataTypes.getColumnString(map.get("FECHA_NOTA")));
		          
		          ne.setCursoSeccion(cs);
		          ne.setNota(n);
		          listNe.add(ne);
			}
			
			exchange.getIn().setBody(listNe);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
	}
	
	public void getNotasHistorico (Exchange exchange) throws Exception{
		
		List<Map<String, Object>> resultGet = exchange.getIn().getBody(List.class);
		List<NotaHistorico> listNh = new ArrayList<>();
		
		if (!resultGet.isEmpty()) {
			
			for (Map<String, Object> map : resultGet) {
				 NotaHistorico nh = new NotaHistorico();
				 CursoSeccion cursoSeccion = new CursoSeccion();
			     Nota nota = new Nota();
			     Curso curso = new Curso();
			     Profesor profesor = new Profesor();
			     Persona persona = new Persona(); 
			     
			      cursoSeccion.setsCRN(DataTypes.getColumnString(map.get("CRN")));
			      cursoSeccion.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
			      cursoSeccion.setsCodigoCurso(DataTypes.getColumnString(map.get("CODIGO")));
			      cursoSeccion.setsSeccion(DataTypes.getColumnString(map.get("SECCION")));
			      cursoSeccion.setsCreditos(UtilsAcademico.getColumnFloatFromBigDecimal(map.get("CREDS")).toString());
			      curso.setsNombreLargoCurso(DataTypes.getColumnString(map.get("NOM_CURSO")));
			      nota.setsPidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("LLAVE_ESTUDIANTE")).toString());
			      nota.setsPeriodo(DataTypes.getColumnString(map.get("SEMESTRE")));
			      nota.setsNivel(DataTypes.getColumnString(map.get("NIVEL_HA")));
			      nota.setsNota(DataTypes.getColumnString(map.get("NOTA")));
			      nota.setdFechaNota(DataTypes.getColumnString(map.get("FECHA_NOTA")));
			      


			      String str = DataTypes.getColumnString(map.get("NOMBRES_PROF"));
			      String[] splited = str.split("\\s+");
			      
			      String str2 = DataTypes.getColumnString(map.get("APELLIDOS_PROF"));
			      String[] splited2 = str2.split("\\s+");
			      
			      persona.setSprimerNombre(splited[0]);
			      if(splited.length>1 ){ persona.setSsegundoNombre(splited[1]);}
			      
			      
			      persona.setSprimerApellido(splited2[0]);
			      if(splited2.length>1 ){ persona.setSsegundoApellido(splited2[1]);}

			      profesor.setcPersona(persona);
			      profesor.setsLogin(DataTypes.getColumnString(map.get("USUARIO_PROF")));
			      
			      nh.setCurso(curso);
			      nh.setCursoSeccion(cursoSeccion);
			      nh.setProfesor(profesor);
			      nh.setNota(nota);
			      listNh.add(nh);
			}
			exchange.getIn().setBody(listNh);
			
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getPromedioSeccion (Exchange exchange) throws Exception {
		
		List<Map<String, Object>> resultGet = exchange.getIn().getBody(List.class);
		List<Nota> listN = new ArrayList<>();
		
		if (!resultGet.isEmpty()) {
			
			for (Map<String, Object> map : resultGet) {
				Nota nota = new Nota();
				
				nota.setsPidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("LLAVE_ESTUDIANTE")).toString());
				nota.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				nota.setsNombreCurso(DataTypes.getColumnString(map.get("NOM_CURSO")));
				nota.setsCodigoCurso(DataTypes.getColumnString(map.get("CODIGO")));
				nota.setsPeriodo(DataTypes.getColumnString(map.get("SEMESTRE")));
				nota.setsNivel(DataTypes.getColumnString(map.get("NIVEL_HA")));
				nota.setdFechaNota(DataTypes.getColumnString(map.get("FECHA_NOTA")));
				nota.setsNota(DataTypes.getColumnString(map.get("NOTA")));
				
				listN.add(nota);
			}
			responsePromedioSeccion.setNota(listN);
			exchange.getIn().setBody(responsePromedioSeccion);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getPromedioSemestre (Exchange exchange) throws Exception{
		
		List<Map<String, Object>> resultGet = exchange.getIn().getBody(List.class);
		
		if (!resultGet.isEmpty()) {
			Nota nota = new Nota();
			Map map = (Map)resultGet.get(0);
			nota.setsNota(UtilsAcademico.getColumnFloatFromBigDecimal(map.get("PROM_SEM_REGULARES")).toString());
			
			respPromedioSemestre.setNota(nota);
			exchange.getIn().setBody(respPromedioSemestre);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getCalificadoMaxMin(Exchange exchange) throws Exception{
		
		List<Map<String, Object>> maxPeriod = exchange.getProperty("maxPeriod", List.class);
		List<Map<String, Object>> minPeriod = exchange.getProperty("minPeriod", List.class);
		
		List<Map<String, Object>> resultMaxMin = new ArrayList<>();
		
		
		if (!maxPeriod.isEmpty() && !minPeriod.isEmpty()) {
			PeriodosCalificadosMaxMin c = new PeriodosCalificadosMaxMin();
			
			Map map = (Map)maxPeriod.get(0);
			Map map2 = (Map)minPeriod.get(0);
			
			c.setMaximoPeriodo(DataTypes.getColumnString(map.get("MAX_PERIODO")));
			c.setMinimoPeriodo(DataTypes.getColumnString(map2.get("MIN_PERIODO")));
			
			respCalificados.setSemestreCalificado(c);
			exchange.getIn().setBody(respCalificados);
			
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	public void getNotasSicua(Exchange exchange)throws Exception{
		
		String notas = exchange.getProperty("bodyNotas", String.class);
		String observaciones = exchange.getProperty("bodyDescripciones", String.class);
		
		 List<NotaParcial> listNotasParciales = new ArrayList<>();
		 List listMapDescriptions = new ArrayList<>();
		
		if (!notas.isEmpty() && notas != null) {
			HashMap<String, Object> result = (HashMap<String, Object>)new ObjectMapper().readValue(notas, HashMap.class);
		    List<Map<String, Object>> listmap = (List<Map<String, Object>>)result.get("results");
		    
		    if ((!observaciones.isEmpty()) &&  (observaciones != null)) {
		    	HashMap mapDescriptions = (HashMap)new ObjectMapper().readValue(observaciones, HashMap.class);
		        listMapDescriptions = (List)mapDescriptions.get("results");
			}
		    if ((!listmap.isEmpty()) && (listmap != null)) {
		    	for (Map<String, Object> mNotas : listmap) {
		    		NotaParcial np = new NotaParcial();
		    		np.setsNota(UtilsAcademico.getColumnDoubleToString(mNotas.get("score")));
		    		np.setsTexto(DataTypes.getColumnString(mNotas.get("text")));
		    		if ((!listMapDescriptions.isEmpty()) && (listMapDescriptions != null)) {
		    			np.setsDescripcion(getDescriptions(listMapDescriptions, UtilsAcademico.getColumnString(mNotas.get("columnId"))));
					}
		    		listNotasParciales.add(np);
				}
			}
			respNotasSicua.setNotaParcials(listNotasParciales);
			exchange.getIn().setBody(respNotasSicua);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	private String getDescriptions (List<Map<String, Object>> listMap, String id) {
		for (Map<String, Object> map : listMap) {
			if (UtilsAcademico.getColumnString(map.get("id")).equalsIgnoreCase(id)) {
				return UtilsAcademico.getColumnString(map.get("name"));
			}
		}
		return "";
	}

}
