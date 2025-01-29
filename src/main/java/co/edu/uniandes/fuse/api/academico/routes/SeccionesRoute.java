package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.seccion.SeccionService;
import co.edu.uniandes.fuse.api.academico.beans.seccion.ValidateParamsSeccion;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseCrn;
import co.edu.uniandes.fuse.api.academico.models.seccion.ResponseSeccionCupos;
import co.edu.uniandes.fuse.api.academico.models.seccion.ResponseSeccionNotas;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionEstudiantes;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionHorarios;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definici�n de componentes rest/swagger y definici�n de rutas para seccion
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class SeccionesRoute extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/secciones").description("Consulta de informaci&oacute;n de secciones")	
		
			.get("/{crn}/{periodo}")
				.description("Consulta la seccion por CRN")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("crn").description("CRN").defaultValue("19434")
					.endParam()
					.param()
						.name("periodo").description("Periodo").defaultValue("201610")
					.endParam()
				.outType(Seccion[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getSeccion")	
		
			.get("/{crn}/{periodo}/estudiantes")
				.description("Consulta los estudiantes de una seccion por CRN Y Periodo")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("crn").description("CRN").defaultValue("19434")
					.endParam()
					.param()
						.name("periodo").description("Periodo").defaultValue("201610")
					.endParam()
				.outType(SeccionEstudiantes[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getSeccionEstudiantes")	
				
			.get("/crn")
				.description("Consulta CRN")				
				.consumes("application/json").produces("application/json")
					.param()
					  	.name("snombreCurso").description("Nombre del curso. <br> Ejemplo: MATE")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("scodigoCurso").description("C&oacute;digo del curso. <br> Ejemplo: 2210")
					  	.type(RestParamType.query)				  	
					  	.required(true)
				  	.endParam()
				  	.param()
					  	.name("sseccion").description("Secci&oacute;n del curso. <br> Ejemplo: 1")
					  	.type(RestParamType.query)				  	
					  	.required(true)
				  	.endParam()
				  	.param()
					  	.name("speriodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
					  	.type(RestParamType.query)				  	
					  	.required(true)
				  	.endParam()
			  	.outType(ResponseCrn.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getSeccionPorCrn")
				
			.get("/{crn}/{periodo}/cupos")
				.description("Consulta el n&uacute;mero de cupos que tiene una secci&oacute;n en un periodo especifico")				
				.consumes("application/json").produces("application/json")
				.param()
					.name("crn").description("CRN").defaultValue("19434")
				.endParam()
				.param()
					.name("periodo").description("Periodo").defaultValue("201610")
				.endParam()
				.outType(ResponseSeccionCupos.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getSeccionCupos")
			
			.get("/horario")
				.description("Consulta el horario de una secci&oacute;n y el profesor encargado de la secci&oacute;n")				
				.consumes("application/json").produces("application/json")
				.param()
				  	.name("snombreCurso").description("Nombre del curso. <br> Ejemplo: MATE")
				  	.type(RestParamType.query)				  	
				  	.required(true)
			  	.endParam()
			  	.param()
				  	.name("scodigoCurso").description("C&oacute;digo del curso. <br> Ejemplo: 2210")
				  	.type(RestParamType.query)				  	
				  	.required(true)
			  	.endParam()
			  	.param()
				  	.name("scrn").description("CRN. <br> Ejemplo: 13577")
				  	.type(RestParamType.query)				  	
				  	.required(false)
			  	.endParam()
			  	.param()
				  	.name("sseccion").description("Secci&oacute;n del curso. <br> Ejemplo: 1")
				  	.type(RestParamType.query)				  	
				  	.required(false)
			  	.endParam()
			  	.param()
				  	.name("speriodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
				  	.type(RestParamType.query)				  	
				  	.required(true)
			  	.endParam()
				.outType(SeccionHorarios.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getSeccionHorarios")
				
			.get("/{crn}/{periodo}/notas")
				.description("Consulta el n&uacute;mero de estudiantes inscritos que tiene una secci&oacute;n en un periodo especifico")				
				.consumes("application/json").produces("application/json")
				.param()
					.name("crn").description("CRN del curso.").defaultValue("13577")
				.endParam()
				.param()
					.name("periodo").description("Periodo acad&eacute;mico.").defaultValue("201610")
				.endParam()
				.outType(ResponseSeccionNotas.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getSeccionNotas")
				
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE SECCION
			from("direct-vm:getSeccion")
				.setProperty("periodo").simple("${headers.periodo}")
				.setProperty("crn").simple("${headers.crn}")
				.to("velocity:template/secciones/query_secciones.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(SeccionService.class, "getSeccion")
				.removeHeaders("*")
		    ;
		
			//ROUTE ESTUDIANTES SECCION
			from("direct-vm:getSeccionEstudiantes")
				.to("direct:set-authentication")
				.to("velocity:template/secciones/query_secciones_estudiantes.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(SeccionService.class, "getSeccionEstudiantes")
	        ;
			
			from("direct:getSeccionPorCrn")
				.to("direct:set-authentication")
				.bean(ValidateParamsSeccion.class, "vSeccionCRN")
				.to("velocity:template/secciones/query_secciones_crn.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.removeHeaders("*")
				.bean(SeccionService.class, "getSeccionPorCrn")
			;
			
			from("direct:getSeccionCupos")
				.to("direct:set-authentication")
				.log("verificando headers ...> ${headers}")
				.bean(ValidateParamsSeccion.class, "vSeccionCRNPeriodo")
				.to("velocity:template/secciones/query_secciones_cupos_inscritos.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.log("verificando headrs 2 : ${headers} y body: ${body}")
				.removeHeaders("*")
				.bean(SeccionService.class, "getSeccionPorCupos")
			;
			
			from("direct:getSeccionHorarios")
				.to("direct:set-authentication")
				.log("verificando headers ...> ${headers}")
				.bean(ValidateParamsSeccion.class, "vSeccionHorarios")
				.to("velocity:template/secciones/query_secciones_horario_materia.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.log("verificando headrs 2 : ${headers} y body: ${body}")
				.removeHeaders("*")
				.bean(SeccionService.class, "getSeccionPorHorarios")
			;
			
			from("direct:getSeccionNotas")
				.to("direct:set-authentication")
				.log("verificando headers ...> ${headers}")
				.bean(ValidateParamsSeccion.class, "vSeccionCRNPeriodo")
				.to("velocity:template/secciones/query_secciones_notas.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.log("verificando headrs 2 : ${headers} y body: ${body}")
				.removeHeaders("*")
				.bean(SeccionService.class, "getSeccionNotas")
			;
						
			
	}
}
