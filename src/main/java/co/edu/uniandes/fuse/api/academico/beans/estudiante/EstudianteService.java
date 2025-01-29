package co.edu.uniandes.fuse.api.academico.beans.estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.egresado.Titulos;
import co.edu.uniandes.fuse.api.academico.models.estudiante.FotoEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.InfoProgramasEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.HomologacionesEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.OpcionesAcademicasHomologacion;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.PeriodosSuspencionAcademicaHomologacion;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.ProgramaHomologacion;
import co.edu.uniandes.fuse.api.academico.models.programa.Programa;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class EstudianteService {

	/**
	 * CORREOS
	 * 
	 * Lee del body el resultado de la consulta a la base de datos, crea una lista
	 * de Correos y mapea la dirección de correo y el tipo de correo
	 */
	public void getProgramasEstudiante(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<Object> listaProgramas = new ArrayList<>();
		//List<InfoProgramasEstudiante> listaProgramas = new ArrayList<>();
		if (!result.isEmpty()) {

			//for (int x = 0; x < result.size(); x++) {
			
				Map<String, Object> row = result.get(0);
			    HashMap<String, String> programa1 = new HashMap<String, String>();
			    
			    programa1.put("codigo", DataTypes.getColumnString(row.get("COD_PROGRAMA_1")));
			    programa1.put("prioridad", DataTypes.getColumnString(row.get("PRIORIDAD_1")));
			    listaProgramas.add(programa1);
			    
			    String cod2 = DataTypes.getColumnString(row.get("COD_PROGRAMA_2"));
			    if (cod2 != null) {
				    HashMap<String, String> programa2 = new HashMap<String, String>();
				    programa2.put("codigo", DataTypes.getColumnString(row.get("COD_PROGRAMA_2")));
				    programa2.put("prioridad", DataTypes.getColumnString(row.get("PRIORIDAD_2")));
				    listaProgramas.add(programa2);
			    }
			    
			exchange.getIn().setBody(listaProgramas);
			
			List<InfoProgramasEstudiante> programasEstudiante = new ArrayList<>();
			exchange.setProperty("programasEstudiante", programasEstudiante);

		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
	
	public void getFotoEstudiante(Exchange exchange) throws Exception {
	
		String timestamp = exchange.getIn().getHeader("CamelFileLastModified", String.class);
		String data = null;
		
		FotoEstudiante foto = new FotoEstudiante();
		foto.setTimestamp(timestamp);
		foto.setData(data);
		exchange.getIn().setBody(foto);
		
	}
	
	public void getDatoHomologaciones(Exchange exchange) throws Exception {
		Integer indice = exchange.getProperty("parametro", Integer.class);
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		String dato = "";
		if (!result.isEmpty()) {
				Map<String, Object> row = result.get(0);
				dato = DataTypes.getColumnEmptyString(row.get("DATO"));
		}
		
		String[] columnas = {
				"nulo",
				"codigo", 
				"nombres", 
				"apellidos",
				"email",
				"telefono_casa",
				"celular",
				"pidm",
				"semestre_segun_creditos",
				"estado_academico",
				"nom_programa_1",
				"nom_programa_2",
				"cod_programa_1",
				"cod_programa_2",
				"nivel_programa_1",
				"nivel_programa_2",
				"cod_grado_programa_1",
				"cod_grado_programa_2",
				"periodo_admision",
				"colegio",
				"opciones_academicas",
				"periodos_suspenciones_academicas",
				"suspencion_disciplinaria",
				};
		
		exchange.setProperty(columnas[indice], dato);		
	}
	
	public void getHomologacionesEstudiante(Exchange exchange) throws Exception {
		
		HomologacionesEstudiante homologaciones = new HomologacionesEstudiante();
		
		ProgramaHomologacion programa1 = new ProgramaHomologacion();
		ProgramaHomologacion programa2 = new ProgramaHomologacion();
		
		
		OpcionesAcademicasHomologacion opcionesAcademicas = new OpcionesAcademicasHomologacion();
		
		List<ProgramaHomologacion> programasList = new ArrayList<>();
		programa1.setPrograma(exchange.getProperty("nom_programa_1", String.class));
		programa1.setNombre(exchange.getProperty("nom_programa_1", String.class));
		programa1.setCodigo(exchange.getProperty("cod_programa_1", String.class));
		programa1.setNivel(exchange.getProperty("nivel_programa_1", String.class));
		programa1.setCodigoGrado(exchange.getProperty("cod_grado_programa_1", String.class));
		programasList.add(programa1);
		
		String cod_programa2 = exchange.getProperty("cod_programa_2", String.class);
		
		if (!cod_programa2.isEmpty()) {
			
			programa2.setPrograma(exchange.getProperty("nom_programa_2", String.class));
			programa2.setNombre(exchange.getProperty("nom_programa_2", String.class));
			programa2.setCodigo(exchange.getProperty("cod_programa_2", String.class));
			programa2.setNivel(exchange.getProperty("nivel_programa_2", String.class));
			programa2.setCodigoGrado(exchange.getProperty("cod_grado_programa_2", String.class));
			programasList.add(programa2);
		}
		
		String opciones_academicas = exchange.getProperty("opciones_academicas", String.class);
		List<OpcionesAcademicasHomologacion> opcionesAcademicasList = new ArrayList<>();
		
		if (!opciones_academicas.isEmpty()) {
			opcionesAcademicas.setOpcion(exchange.getProperty("opciones_academicas", String.class));
			opcionesAcademicasList.add(opcionesAcademicas);
		}
		
		String periodos_suspenciones_academicas = exchange.getProperty("periodos_suspenciones_academicas", String.class);
		List<PeriodosSuspencionAcademicaHomologacion> periodosSuspencionList = new ArrayList<>();
		
		if (!periodos_suspenciones_academicas.isEmpty()) {
			
			
			String[] periodos = periodos_suspenciones_academicas.split("-");
			
			for (int i = 0; i < periodos.length; i++) {
				PeriodosSuspencionAcademicaHomologacion periodosSuspencion = new PeriodosSuspencionAcademicaHomologacion();
				periodosSuspencion.setPeriodo(periodos[i]); 
				periodosSuspencionList.add(periodosSuspencion);
			}
		} 
		
		homologaciones.setCodigo(exchange.getProperty("codigo", String.class));
		homologaciones.setPidm(exchange.getProperty("pidm", String.class));
		homologaciones.setNombres(exchange.getProperty("nombres", String.class));
		homologaciones.setApellidos(exchange.getProperty("apellidos", String.class));
		homologaciones.setEmail(exchange.getProperty("email", String.class));
		homologaciones.setTelefonoCasa(exchange.getProperty("telefono_casa", String.class));
		homologaciones.setCelular(exchange.getProperty("celular", String.class));
		homologaciones.setSemestreSegunCredito(exchange.getProperty("semestre_segun_creditos", String.class));
		String estado = exchange.getProperty("estado_academico", String.class);
		if (!estado.isEmpty()) {
			estado = estado.substring(0,2);
		}
			
		
		homologaciones.setEstadoAcademico(estado);
		homologaciones.setPeriodoAdmision(exchange.getProperty("periodo_admision", String.class));
		homologaciones.setColegio(exchange.getProperty("colegio", String.class));
		homologaciones.setSuspencioDiciplinaria(exchange.getProperty("suspencion_disciplinaria", String.class));
		
		homologaciones.setPrograma(programasList);
		homologaciones.setOpcionesAcademicas(opcionesAcademicasList);
		homologaciones.setPeriodosSuspencionAcademica(periodosSuspencionList);
		
		exchange.getIn().setBody(homologaciones);
	}

	
	
}
