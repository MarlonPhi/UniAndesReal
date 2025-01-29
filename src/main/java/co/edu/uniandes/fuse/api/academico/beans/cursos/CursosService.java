package co.edu.uniandes.fuse.api.academico.beans.cursos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.cursos.Cursos;
import co.edu.uniandes.fuse.api.academico.models.cursos.InfoCursos;
import co.edu.uniandes.fuse.api.academico.models.cursos.RequestCurso;
import co.edu.uniandes.fuse.api.academico.models.cursos.ResponseInfoCursos;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.Curso;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class CursosService {
	
	private final static Logger log = Logger.getLogger("");
	ResponseInfoCursos responseInfoCursos = new ResponseInfoCursos();
	
	public void getCursos(Exchange exchange) throws Exception {
		
		
		String codigo = exchange.getIn().getHeader("codigo", String.class);
		String codCurso = exchange.getIn().getHeader("curso", String.class);
		String nombre = exchange.getIn().getHeader("nombre", String.class);
		
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<Cursos> cursos = new ArrayList<>();
		if (!result.isEmpty()) {

			for (int x = 0; x < result.size(); x++) {

				Map<String, Object> row = result.get(x);
				
				Cursos curso = new Cursos();
				curso.setCodigo(DataTypes.getColumnString(row.get("CODIGO")));
				curso.setNombre(DataTypes.getColumnString(row.get("NOMBRE")));
				curso.setCurso(DataTypes.getColumnString(row.get("CURSO")));
				curso.setCreditos(DataTypes.getColumnString(row.get("CREDITOS")));
				curso.setNumero(DataTypes.getColumnString(row.get("NUMERO")));
				curso.setNivel(DataTypes.getColumnString(row.get("NIVEL")));
				cursos.add(curso);
			
			}
			exchange.getIn().setBody(cursos);			

		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		
	}
	
	
	public void getInfoCursos(Exchange exchange) throws Exception{
		
		
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<InfoCursos> listaInfo = new ArrayList<>();

		log.info("verificando results dentro de processor--->" + resultSet.toString());
		
		if (!resultSet.isEmpty() ) {
			
			for (int i = 0; i < resultSet.size(); i++) {
				Map<String, Object> map = resultSet.get(i);
				
				CursoSeccion cursoSeccion = new CursoSeccion();
				Curso curso = new Curso();
				InfoCursos infoCurso = new InfoCursos();
				cursoSeccion.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				cursoSeccion.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
				curso.setsNombreLargoCurso(DataTypes.getColumnString(map.get("NOMBRE_CURSO")));
				curso.setsFacultad(DataTypes.getColumnString(map.get("FAC_COD")));
				curso.setsDescFacultad(DataTypes.getColumnString(map.get("FACULTAD")));
				curso.setsDepartamento(DataTypes.getColumnString(map.get("DEPARTAMENTO")));
				curso.setsDescDepartamento(DataTypes.getColumnString(map.get("DESC_DEPTO")));
				curso.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
				curso.setsCodigoCurso(DataTypes.getColumnString(map.get("COD_CURSO")));
				curso.setsDescMateria(DataTypes.getColumnString(map.get("DESCRIPCION_MATERIA")));
				
				infoCurso.setCurso(curso);
				infoCurso.setCursoSeccion(cursoSeccion);
				listaInfo.add(infoCurso);
			}
			responseInfoCursos.setCursos(listaInfo);
			exchange.getIn().setBody(responseInfoCursos);
			
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
	}
	
	/**
	 * Metodo que permite setear ifnromacion de un curso en las clases determinadas para un curso
	 * para la ruta de arrayt de cursos
	 * @param exchange
	 * @throws Exception
	 */
	public void getInfoCursosv2(Exchange exchange) throws Exception{
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<InfoCursos> listaInfo = new ArrayList<>();

		log.info("verificando results dentro de processor--->" + resultSet.toString());
		
		if (!resultSet.isEmpty() ) {
			
			for (int i = 0; i < resultSet.size(); i++) {
				Map<String, Object> map = resultSet.get(i);
				
				CursoSeccion cursoSeccion = new CursoSeccion();
				Curso curso = new Curso();
				InfoCursos infoCurso = new InfoCursos();
				cursoSeccion.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				cursoSeccion.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
				curso.setsNombreLargoCurso(DataTypes.getColumnString(map.get("NOMBRE_CURSO")));
				curso.setsFacultad(DataTypes.getColumnString(map.get("FAC_COD")));
				curso.setsDescFacultad(DataTypes.getColumnString(map.get("FACULTAD")));
				curso.setsDepartamento(DataTypes.getColumnString(map.get("DEPARTAMENTO")));
				curso.setsDescDepartamento(DataTypes.getColumnString(map.get("DESC_DEPTO")));
				curso.setsNombreCurso(DataTypes.getColumnString(map.get("MATERIA")));
				curso.setsCodigoCurso(DataTypes.getColumnString(map.get("COD_CURSO")));
				curso.setsDescMateria(DataTypes.getColumnString(map.get("DESCRIPCION_MATERIA")));
				
				infoCurso.setCurso(curso);
				infoCurso.setCursoSeccion(cursoSeccion);
				listaInfo.add(infoCurso);
			}
			//responseInfoCursos.setCursos(listaInfo);
			exchange.getIn().setBody(listaInfo);
			exchange.setProperty("listaInfo", listaInfo);
			//log.warn("verify body en procesor de getInfoCursov2 -->"+ exchange.getIn().getBody());
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
	}
	
	
	public void getNameCursos(Exchange exchange) throws Exception{
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<InfoCursos> listaInfo = new LinkedList<InfoCursos>();
		
		if (!resultSet.isEmpty()) {
			for (Map<String, Object> map : resultSet) {
				Curso curso = new Curso();
				CursoSeccion cursoSeccion = new CursoSeccion();
				InfoCursos infoCurso = new InfoCursos();
				cursoSeccion.setsCRN(DataTypes.getColumnString(map.get("CRN")));
				cursoSeccion.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
				curso.setsNombreLargoCurso(DataTypes.getColumnString(map.get("MATERIA")));
				infoCurso.setCurso(curso);
				infoCurso.setCursoSeccion(cursoSeccion);
				listaInfo.add(infoCurso);
			}
			
			exchange.getIn().setBody(listaInfo);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
	}
	
	public void getArrayCursos(Exchange ex) throws Exception{
		//RequestCurso datosCursosProperty = (RequestCurso)ex.getIn().getBody();
		RequestCurso datosCursosProperty = (RequestCurso)ex.getProperty("objetoCurso");
		
		Integer curso = datosCursosProperty.getCurso();
		Integer crn = datosCursosProperty.getCrn();
		Integer periodo = datosCursosProperty.getPeriodo();
		
		ex.setProperty("scodigocurso", curso);
		ex.setProperty("scrn", crn);
		ex.setProperty("speriodo", periodo);
		
		//log.info("map de salitre --->"+ salitre);
		//Integer curso = datosCursosProperty.g
	
		
	}
	
}
