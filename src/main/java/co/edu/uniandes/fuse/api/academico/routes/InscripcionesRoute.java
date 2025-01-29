package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.inscripciones.InscripcionesService;
import co.edu.uniandes.fuse.api.academico.beans.inscripciones.ValidateParamsInscriptionService;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseSeccionesConteo;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseSeccionesInfo;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.ResponseTurno;
import co.edu.uniandes.fuse.api.academico.models.inscripciones.Secciones;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class InscripcionesRoute extends RestConfiguration{
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
        
        rest("/inscripciones").description("Consulta de informaci&oacute;n de inscripci&oacute;n de un estudiante")
        
        	.get("/{id}/secciones")
	        	.description("consulta y retorna las secciones inscritas por un estudiante en un periodo espec&iacute;fico.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("id").description("N&uacute;mero de Identificaci&oacute;n &oacute; Login acad&eacute;mico del estudiante. <br> Ejemplo: 80843479  |  cafanador")
				.endParam()
				.param()
					.name("periodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.param()
					.name("tipoInscripcion").description("Tipo de inscripci&oacute;n de la secci&oacute;n. <br> Ejemplo : RW, RE, CL, PE, RQ")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.outType(Secciones.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:getSecciones")
				
	        .get("/{id}/secciones/info")
	        	.description("consulta y retorna las secciones inscritas por un estudiante en un periodo espec&iacute;fico.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("id").description("N&uacute;mero de Identificaci&oacute;n &oacute; Login acad&eacute;mico del estudiante. <br> Ejemplo: 80843479  |  cafanador")
				.endParam()
				.param()
					.name("periodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.param()
					.name("tipoInscripcion").description("Tipo de inscripci&oacute;n de la secci&oacute;n. <br> Ejemplo : RW, RE, CL, PE, RQ, RC")
					.type(RestParamType.query)
					.required(false)
				.endParam()
				.outType(ResponseSeccionesInfo.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:getSeccionesInfo")
				
			.get("/{id}/secciones/cantidad") 
	        	.description("consulta y retorna las secciones inscritas por un estudiante en un periodo espec&iacute;fico.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("id").description("N&uacute;mero de Identificaci&oacute;n &oacute; Login acad&eacute;mico del estudiante. <br> Ejemplo: 80843479  |  cafanador")
				.endParam()
				.param()
					.name("periodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.param()
					.name("tipoInscripcion").description("Tipo de inscripci&oacute;n de la secci&oacute;n. <br> Ejemplo (Se debe ingresar uno de los valores)*: RW, RE, CL, PE, RQ")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.param()
					.name("nivel").description("Nivel acad&eacute;mico del estudiante. <br> Ejemplo MA")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.outType(ResponseSeccionesConteo.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:getSeccionesCantidad")
				
	        .get("/{id}/turno") 
	        	.description("consulta y retorna las secciones inscritas por un estudiante en un periodo espec&iacute;fico.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("id").description("N&uacute;mero de Identificaci&oacute;n &oacute; Login acad&eacute;mico del estudiante. <br> Ejemplo: 80843479  |  cafanador")
				.endParam()
				.param()
					.name("periodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201510")
					.type(RestParamType.query)
					.required(true)
				.endParam()
				.outType(ResponseTurno.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:getTurno")
        ;
        
        
        
        
        
        from("direct:getSecciones")
	        .bean(ValidateParamsInscriptionService.class, "validateParamsSecciones")
	        .to("direct-vm:validaPIDM")
	        .to("velocity:template/inscripciones/query_crn.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.choice()
				.when(body().isNull())
					.setHeader("CamelHttpResponseCode", simple("{{http.code.not.found}}"))
					.process(new ValidateErrorProcessor())
				.otherwise()
					.marshal().json(JsonLibrary.Jackson)
					.setProperty("LISTA").spel("#{new java.util.ArrayList()}")
					.split().jsonpath("$.[*].CRN")
						.setProperty("crn", simple("${body}"))
						.to("velocity:template/inscripciones/query_curso_seccion.vm")
						.setHeader("CamelSqlQuery").simple("${body}")
						.to("direct:bannerSQL")
						.bean(InscripcionesService.class, "getListSecciones")
					.end()
					.bean(InscripcionesService.class,"getSecciones")
			.end()
			.removeHeaders("*")
        ;
        
        from("direct:getSeccionesCantidad")
	        .bean(ValidateParamsInscriptionService.class, "validateParamsSeccionesCant")
	        .to("direct-vm:validaPIDM")
	        .to("velocity:template/inscripciones/query_conteo_secciones.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.choice()
				.when(body().isNull())
					.setHeader("CamelHttpResponseCode").constant("404")
					.process(new ValidateErrorProcessor())
					.log("Error!")
				.otherwise()
					.log("verificando body: ${body}")
					.bean(InscripcionesService.class,"getSeccionesCantidad")
			.end()
			
        ;
        
        from("direct:getSeccionesInfo")
        	.bean(ValidateParamsInscriptionService.class, "validateParamsSecciones")
        	.to("direct-vm:validaPIDM")
        	.log("verify properties --> |${property.tipoInscripcion}|")
        	.setHeader("tInsc").simple("${property.tipoInscripcion}")
	        .to("velocity:template/inscripciones/query_info_materias_inscritas.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.choice()
				.when(body().isNull())
					.setHeader("CamelHttpResponseCode").constant("404")
					.process(new ValidateErrorProcessor())
					.log("Error!")
				.otherwise()
					.log("verificando body: ${body}")
					.bean(InscripcionesService.class,"getSeccionesInfo")
			.end()
        ;
        
        from("direct:getTurno")
	        .bean(ValidateParamsInscriptionService.class, "validateTurno")
	        .to("direct-vm:validaPIDM")
	        .to("velocity:template/inscripciones/query_turno.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.choice()
				.when(body().isNull())
					.setHeader("CamelHttpResponseCode").constant("404")
					.process(new ValidateErrorProcessor())
					.log("Error!")
				.otherwise()
					.log("verificando body: ${body}")
					.bean(InscripcionesService.class,"getTurno")
			.end()
		
        ;
	}

}
