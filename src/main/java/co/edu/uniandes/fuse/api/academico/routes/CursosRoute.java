package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.SplitDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.spi.DataFormat;

import co.edu.uniandes.fuse.api.academico.aggregators.CourseAggregator;
import co.edu.uniandes.fuse.api.academico.beans.cursos.CursosService;
import co.edu.uniandes.fuse.api.academico.beans.cursos.ValidateParamsCursosService;
import co.edu.uniandes.fuse.api.academico.models.cursos.Cursos;
import co.edu.uniandes.fuse.api.academico.models.cursos.RequestCurso;
import co.edu.uniandes.fuse.api.academico.models.cursos.ResponseInfoCursos;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class CursosRoute  extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
        .handled(true)
        .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/cursos").description("Consulta de informaci&oacute;n de cursos")		
			// programas		
			.get("/")
				.description("Consulta los cursos por codigo o nombre tanto activos como inactivos")				
				.consumes("application/json").produces("application/json")
					.param()
					  	.name("codigo").description("Codigo (Ej: CESP)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("nombre").description("Nombre (Ej: TRANSICION)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
					.endParam()
					.param()
					  	.name("curso").description("C&oacute;digo del curso (Ej: CESP1101)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
		  	        .endParam()					
				.outType(Cursos[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getCursos")
			.get("/activos/")
				.description("Consulta los cursos activos por codigo o nombre")				
				.consumes("application/json").produces("application/json")
					.param()
					  	.name("codigo").description("Codigo (Ej: CESP)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("nombre").description("Nombre (Ej: TRANSICION)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
					.endParam()
					.param()
					  	.name("curso").description("C&oacute;digo del curso (Ej: CESP1101)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
		  	        .endParam()					
				.outType(Cursos[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getCursosActivos")
				
			.get("/info")
				.description("Consulta la informaci&oacute;n completa del curso")				
				.consumes("application/json").produces("application/json")
					.param()
					  	.name("codigo").description("C&oacute;digo departamento (Ej: ADMI)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("nombre").description("Nombre curso (Ej: MADM)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("curso").description("C&oacute;digo del curso (Ej: 4701)")
					  	.type(RestParamType.query)				  	
					  	.required(true)
				  	.endParam()
				  	.param()
					  	.name("facultad").description("C&oacute;digo del curso (Ej: AD)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
					.endParam()
					.param()
					  	.name("periodo").description("colocar otra documentacion - verificar con gestion curso (Ej: 201810)")
					  	.type(RestParamType.query)				  	
					  	.required(true)
				  	.endParam()
				  	.param()
					  	.name("crn").description("colocar otra documentacion - verificar con gestion curso (Ej: 28255)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
			  	.outType(ResponseInfoCursos.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getInfoCursos")
				
			.post("/v2/info")
				.description("Consulta la informaci&oacute;n completa del curso a traves de un array de cursos")
				.consumes("application/json").produces("application/json")
				.type(RequestCurso[].class)
				.outType(ResponseInfoCursos.class)
				.to("direct:getInfoPerArrayCursos")
								
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE CURSOS
			
			from("direct-vm:getCursos")
				.bean(ValidateParamsCursosService.class, "validateCursos")
			    //.process(new CursosProcessor()) 
				.to("velocity:template/cursos/query_cursos.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.log("SQL: ${body}")
				.to("direct:bannerSQL")
				.log("Response: ${body}")
				.bean(CursosService.class, "getCursos")
			;	
			
			from("direct-vm:getCursosActivos")
				.bean(ValidateParamsCursosService.class, "validateCursos")
				.to("velocity:template/cursos/query_cursos_v2.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.log("SQL: ${body}")
				.to("direct:bannerSQL")
				.log("Response: ${body}")
				.bean(CursosService.class, "getCursos")
			;
			
			//verificar con gestion-curso
			from("direct:getInfoCursos")
				.bean(ValidateParamsCursosService.class, "validateCursosInfo")
				/*.to("direct:set-authentication")
				.log("verificando headers antes de adquirir properties: ${headers}")
				.setProperty("scodigodpto").simple("${headers.codigo}")
				.setProperty("snombrecurso").simple("${headers.nombre}")
				.setProperty("scodigocurso").simple("${headers.curso}")
				.setProperty("sfacultad").simple("${headers.facultad}")
				.setProperty("scrn").simple("${headers.scrn}")
				.setProperty("speriodo").simple("${headers.periodo}")*/
				.removeHeaders("*")
				.to("velocity:template/cursos/query_informacion_curso.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.log("SQL: ${body}")
				.to("direct:bannerSQL")
				.choice()
					.when(body().isNotNull())
						.setHeader("CamelHttpResponseCode").simple("{{http.code.ok}}")
						.log("salida ok")
					.otherwise()
						.setHeader("CamelHttpResponseCode").simple("{{http.code.not.found}}")
						.log("salida fail")
				.end()
				.choice()
					.when(simple("${header.CamelHttpResponseCode} != 200"))
						.process(new ValidateErrorProcessor())
					.otherwise()
						.bean( CursosService.class, "getInfoCursos")
				.end()
			;
			
			/**
			 * Ruta que permite realizar un response de varias consultas dependen de el json de request.
			 */
			from("direct:getInfoPerArrayCursos")
			.log("VERIFY body INICIO RECEPCION: ${body}")
				.choice()
					.when(body().isEqualTo(""))
						.setHeader("CamelHttpResponseCode").simple("404")
						.process(new ValidateErrorProcessor())
					.otherwise()
						.bean(ValidateParamsCursosService.class, "validateArrayCursosInfo")
						.split().exchangeProperty("listArrayDatosCursos").stopOnException()
							.parallelProcessing()
							.aggregationStrategy(new CourseAggregator())
							.log("ENTRANDO A SPLIT CON :: ${property.listArrayDatosCursos}")
							.setProperty("objetoCurso").simple("${body}")
							.log("verificando body de property objetoCurso:: ${property.objetoCurso}")
							.bean(CursosService.class, "getArrayCursos")
							.log("verificando properties:: curso: ${property.scodigocurso}, crn: ${property.scrn}, periodo: ${property.speriodo}")
							.to("velocity:template/cursos/query_informacion_curso_v2.vm")
							.setHeader("CamelSqlQuery").simple("${body}")
							.to("direct:bannerSQL")
							.bean(CursosService.class, "getInfoCursosv2")
						.end()
				.end()
			;
			
			
	}
}
