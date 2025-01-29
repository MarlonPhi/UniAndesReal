package co.edu.uniandes.fuse.api.academico.beans.estudiante;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Handler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.estudiante.EstadoMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.FotoEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.HistorialPrestamos;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Horario;
import co.edu.uniandes.fuse.api.academico.models.estudiante.InfoProgramasEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.KeywordsGestEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Horarios;
import co.edu.uniandes.fuse.api.academico.models.estudiante.KeywordsGestEstudiante.KeywordList;
import co.edu.uniandes.fuse.api.academico.models.estudiante.KeywordsGestEstudiante.Keywords;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ParametroPregunta;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Prestamo;
import co.edu.uniandes.fuse.api.academico.models.estudiante.PrestamoRecurso;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponsePrestamo;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SalidaEstadoMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SimpleResponse;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.HomologacionesEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.OpcionesAcademicasHomologacion;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.PeriodosSuspencionAcademicaHomologacion;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.ProgramaHomologacion;

import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.RecursoBibliografico;

public class EstudianteService {
	ResponseCode rCode = new ResponseCode();
	private final static Logger LOGGER = Logger.getLogger("");
	SimpleResponse er = new SimpleResponse();
	ResponsePrestamo rPrestamo = new ResponsePrestamo();

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
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
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
				LOGGER.info("Verificando dato--->::"+ dato );
		}else {
			er.setStatus(false);
			er.setMessage("No se encontró o recuperó resultados para los parametros de busqueda");
			er.setData(new HashMap<Object, Object>());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			exchange.getIn().setBody(er);
			LOGGER.log(Level.INFO, "error 204::" + er.getMessage());
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
				"estado_academico", //dato 9
				"nom_programa_1", //dato 10
				"nom_programa_2", // dato 11
				"cod_programa_1",
				"cod_programa_2", // dato 13
				"nivel_programa_1",
				"nivel_programa_2",
				"cod_grado_programa_1",
				"cod_grado_programa_2",
				"periodo_admision",
				"colegio",
				"opciones_academicas",
				"periodos_suspenciones_academicas", //dato 21
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
		
		//String hh = homologaciones.getPeriodoAdmision();
		//LOGGER.info("verificando hh:::: "+ hh);
		
		if (estado.isEmpty() && periodos_suspenciones_academicas.isEmpty() && cod_programa2.isEmpty()) {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "404");
		}else {
			exchange.getIn().setBody(homologaciones);
		}
		
		
	}
	
	public void getHistorialPrestamos (Exchange exchange) throws Exception {
		
		ArrayList<HistorialPrestamos> sal = new ArrayList<HistorialPrestamos>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		boolean status = true;
		
		if (!resultSet.isEmpty()) {
			for (Map<String, Object> map : resultSet) {
				
				HistorialPrestamos prestamo = new HistorialPrestamos();		
				prestamo.setsIdTitulo(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("ID_TITULO")).toString());
				prestamo.setsTitulo(UtilsAcademico.getColumnString(map.get("TITULO")));
				prestamo.setsAutor(UtilsAcademico.getColumnString(map.get("AUTOR")));
				prestamo.setsClasificacion(UtilsAcademico.getColumnString(map.get("N_TOPOGRAFICO")));
				prestamo.setsIdItem(UtilsAcademico.getColumnString(map.get("CODIGO_BARRAS_ITEM")));
				prestamo.setsCodigo(UtilsAcademico.getColumnString(map.get("ID_USUARIO")));
				prestamo.setsBibliotecaPrestamo(UtilsAcademico.getColumnString(map.get("PRESTADO_EN")));
				prestamo.setdFechaPrestamo(UtilsAcademico.getColumnString(map.get("PRESTADO_EL")));					
				prestamo.setdFechaVencimiento(UtilsAcademico.getColumnString(map.get("VENCE_EL")));
				prestamo.setdFechaRenovacion(UtilsAcademico.getColumnString(map.get("RENOVADO_EL")));
				prestamo.setsRenovaciones(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("RENOVACIONES_ONLINE")).toString());
				prestamo.setdFechaDevolucion(UtilsAcademico.getColumnString(map.get("DEVUELTO_EL")));
				prestamo.setsMulta(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("SANCION_MOSTRARENFORMATOMONEDA")).toString());
				prestamo.setsBibliotecaDevolucion(UtilsAcademico.getColumnString(map.get("DEVUELTO_EN")));
				
				Date now = new Date();
				String fechaV = prestamo.getdFechaVencimiento().toString();
				String fechaD = prestamo.getdFechaDevolucion().toString();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date fvformat = formatter.parse(fechaV);
				Date fdformat = formatter.parse(fechaD);
				
				//LOGGER.info("Fecha actual parseada: "+ now.toString());
				//LOGGER.info("Fecha vence parseada: "+ fvformat.toString());
				//LOGGER.info("Fecha devolucion parseada: "+ fdformat.toString());
				
				/**
				 * CONDITIONALS OF DATES COMPARE OF LOAN HISTORY
				 * 1. IF Date expiration is before date now  and date return loan is empty THEN status is true (active).
				 * 2. IF Date expiration is after date now and date return loan is empty  THEN status is true (active).
				 * 3. IF Date return loan is not empty THEN IF date return loan is after date expiration THEN status is true (active).
				 * 4. IF Date Date expiration is before date now and return loan is not empty THEN status is false (desactive).
				 */
				LOGGER.info("----CONDITIONALS LOAN HISTORY----");
				if(fvformat.before(now)  && (!prestamo.getdFechaDevolucion().isEmpty() || prestamo.getdFechaDevolucion() != null)){
					//LOGGER.info("ENTRAA IF 1 SIENDo vencimiento antes de ahora y no vacio devolucion");
					if (fdformat.before(fvformat)) {
						status = false;
						prestamo.setsEstado(status);
						LOGGER.info("- date return is before of expiration");
					}else {
						prestamo.setsEstado(status);
						LOGGER.info("- date return is after of expiration");
					}
				 }else {
					 prestamo.setsEstado(status);
					 LOGGER.info("- date expis adter of date now and return loan is not empty");
				 }
				sal.add(prestamo);	
			}
			exchange.getIn().setBody(sal);
		}else {
			//er.setStatus(false);
			//er.setMessage("No se encontrarón resultados para los parametros de busqueda");
			//er.setData(new HashMap<Object, Object>());
			//exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			//exchange.getIn().setBody(er);
			LOGGER.log(Level.INFO, "error else No se encontrarón resultados para los parametros de busqueda");
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	@Handler
	public void getActualizarMultas (Exchange exchange,  @ExchangeProperty("Validation") Boolean validation, 
									@ExchangeProperty("IDPROCESO") String idProceso,
									@ExchangeProperty("RESPONSE") String repuesta, 
									@ExchangeProperty("message") String message) throws Exception {
		
		EstadoMultas estMu = new EstadoMultas();
		SalidaEstadoMultas estadoMul = new SalidaEstadoMultas();
		List<SalidaEstadoMultas> salida = new ArrayList<>();
		String estado = exchange.getIn().getBody(String.class);
		
		//split estado
	    String[] split1 = estado.split(";");
	    String part1 = split1[0];//codigo
	    String descripcion = split1[1];//descripcion
	    //splitCodigo
	    String[] split2 = part1.split("=");
	    String codigo = split2[1]; 
	    
	    if (!estado.isEmpty()) {
	    	
	    	if (idProceso != null) {
				estadoMul.setTipoProceso(idProceso.toString());
			}else {
				estadoMul.setTipoProceso("");
			}
			
	    	estadoMul.setEstadoTransaccion(descripcion);
	    	estadoMul.setCodigoRespuesta(codigo);
	    	salida.add(estadoMul);
	    	estMu.setEstadoMultas(salida);
	    	
	    	exchange.getIn().setBody(estMu);
		}else {
			//er.setStatus(false);
			//er.setMessage("No se encontrarón resultados para los parametros de busqueda.");
			//er.setData(new HashMap<Object, Object>());
			//exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			//exchange.getIn().setBody(er);
			LOGGER.log(Level.INFO, "error else  404: No se encontrarón resultados para los parametros de busqueda." );
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
			
		}
		
		
		
		
	}
	
	public void getObtenerPrestamos (Exchange exchange) throws Exception {
		
		List<Map<String, Object>> result = (List<Map<String, Object>>)exchange.getIn().getBody();
		List<Prestamo> listPrestamo = new ArrayList<Prestamo>();
		PrestamoRecurso pr = new PrestamoRecurso();

		
		if (!result.isEmpty() && result != null) {
			
			for (Map<String, Object> map: result) {
				RecursoBibliografico rb = new RecursoBibliografico();
				Prestamo prestamo = new Prestamo();
				
				pr.setsCodigo(DataTypes.getColumnString(map.get("ID")));
				pr.setsNumeroDocumento(DataTypes.getColumnString(map.get("DOCUMENTO")));
				pr.setsBiblioteca(DataTypes.getColumnString(map.get("BIBLIOTECA")));
				//pr.setsBiblioteca(DataTypes.getColumnString(map.get("nombre_biblioteca")).replace("\"", ""));
				pr.setdFechaPrestamo(UtilsAcademico.getDateFromString(DataTypes.getColumnString(map.get("FEC_PRESTAMO"))));
				pr.setdFechaDevolucion(UtilsAcademico.getDateFromString(DataTypes.getColumnString(map.get("FEC_DEVOLUCION"))));
				rb.setsIdItem(DataTypes.getColumnString(map.get("ID_ITEM")));
				rb.setsAutor(DataTypes.getColumnString(map.get("AUTOR")));
				rb.setsMateria(DataTypes.getColumnString(map.get("MATERIA")));
				rb.setsTitulo(DataTypes.getColumnString(map.get("TITULO")));
				
				prestamo.setPrestamoRecurso(pr);
				prestamo.setRecursoBibliografico(rb);
				
				listPrestamo.add(prestamo);
				
			}
			rPrestamo.setPrestamo(listPrestamo);
			exchange.getIn().setBody(rPrestamo);
		}else {
			//er.setStatus(false);
			//er.setMessage("No se encontrarón resultados para los parametros de busqueda.");
			//er.setData(new HashMap<Object, Object>());
			//exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			//exchange.getIn().setBody(er);
			
			LOGGER.log(Level.INFO, "error else  404: No se encontrarón resultados para los parametros de busqueda." );
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		
	}
	
	public void getInfoPersonalParameter (Exchange exchange) throws Exception {
		
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		List<ParametroPregunta> pParameter = new ArrayList<>();
		
		if (!result.isEmpty() && result != null) {
			for (Map<String, Object> map : result) {
				ParametroPregunta pp = new ParametroPregunta();
				pp.setParameterQuest(DataTypes.getColumnString(map.get("question")));
				pParameter.add(pp);
			}
			exchange.getIn().setBody(pParameter);
		}else {
			//exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			//rCode.setStatus(false);
			//rCode.setMessage("El recurso no devuelve contenido.");
			//exchange.getIn().setBody(rCode);
			
			LOGGER.log(Level.INFO, "error else  404: No se encontrarón resultados para los parametros de busqueda." );
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
	}
	
	public void getObjectNotes(Exchange ex) throws Exception {
		LOGGER.info("ENTRANDO A METODO -----getObjectNotes-----");
		List<Map<String, Object>> resultList = (List<Map<String, Object>>)ex.getIn().getBody();
		
        Map<String, Horarios> cursosPorNrc = new HashMap<>();
        
        for (Map<String, Object> map : resultList) {
        	
        	
        	String nrc = DataTypes.getColumnString(map.get("NRC"));
        	
        	if(!cursosPorNrc.containsKey(nrc) || cursosPorNrc == null) {
        		Horarios horarios = new Horarios();
        		
        		horarios.setNrc(Integer.parseInt(DataTypes.getColumnString(map.get("NRC"))));
        		horarios.setMateria(DataTypes.getColumnString(map.get("MATERIA")));
        		horarios.setNombre_curso(DataTypes.getColumnEmptyString(map.get("NOMBRE_CURSO")));
        		horarios.setNumero_curso(DataTypes.getColumnString(map.get("NRO_CURSO")));
        		horarios.setPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
        		horarios.setSeccion(DataTypes.getColumnEmptyString(map.get("SECCION")));
        		
        		List<Horario> horarioList = new ArrayList<>();
        		Horario horario = new Horario();
        		
        		horario.setHora_inicio(DataTypes.getColumnString(map.get("HORA_INICIO")));
        		horario.setHora_fin(DataTypes.getColumnString(map.get("HORA_FIN")));
        		horario.setDia_semana(obtenerDias(map));
        		horario.setSalon(DataTypes.getColumnString(map.get("SALON")));
        		horarioList.add(horario);
        		
        		
        		horarios.setHorario(horarioList);
        		cursosPorNrc.put(nrc, horarios);
        		
        	}else {
        		
        		Horarios horarios = cursosPorNrc.get(nrc);
        		Horario horario = new Horario();
    			
    			horario.setHora_inicio(DataTypes.getColumnString(map.get("HORA_INICIO")));
    			LOGGER.info("devuelve setHora_inicio::" + horario.getHora_inicio());
        		horario.setHora_fin(DataTypes.getColumnString(map.get("HORA_FIN")));
        		LOGGER.info("devuelve setHora_fin::" + horario.getHora_fin());
        		horario.setDia_semana(obtenerDias(map));
        		LOGGER.info("devuelve setDia_semana::" + horario.getDia_semana());
        		horario.setSalon(DataTypes.getColumnString(map.get("SALON")));
        		LOGGER.info("devuelve setSalon::" + horario.getSalon());
        		
        		horarios.getHorario().add(horario);
        		
        	}
        	
		}
        
        List<Horarios> horariosList = new ArrayList<>(cursosPorNrc.values());
        
        ex.getIn().setBody(horariosList);
        
	}
	
	private static List<String> obtenerDias(Map<String, Object> curso) {
        List<String> dias = new ArrayList<>();
        if (curso.get("LUNES") != null) dias.add("L");
        if (curso.get("MARTES") != null) dias.add("M");
        if (curso.get("MIERCOLES") != null) dias.add("I");
        if (curso.get("JUEVES") != null) dias.add("J");
        if (curso.get("VIERNES") != null) dias.add("V");
        if (curso.get("SABADO") != null) dias.add("S");
        if (curso.get("DOMINGO") != null) dias.add("D");
        return dias;
    }
	
}
