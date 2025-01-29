package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.models.suspension.ResponseSuspensionesAcademicas;
import co.edu.uniandes.fuse.api.academico.models.suspension.ResponseSuspensionesDisciplinarias;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.api.academico.processors.suspensiones.SuspensionesAcademicasProcessor;
import co.edu.uniandes.fuse.api.academico.processors.suspensiones.SuspensionesDisciplinariasProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class SuspensionesRoute extends RestConfiguration{

	public void configure() throws Exception {
			// ------- EXCEPTIONS -----------------------------------------------------------------------------		
			onException(Exception.class)
	        	.handled(true)
	        	.to("direct-vm:manageException");
			
			// ------- RESTS ----------------------------------------------------------------------------------
	        rest("/suspensiones")
	        	.get("/academicas")
		        	.description("Consulta las suspensiones academicas de un estudiante")				
		        	.consumes("application/json").produces("application/json")
					.param()
						.name("snumerodocumento").description("Documento de Identificaci&oacute;n del estudiante. <br> Ejemplo: 1061725247")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("slogin").description("Login acad&eacute;mico del estudiante. <br>  Ejemplo: jm.revelo120")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("scodigo").description("C&oacute;digo acad&eacute;mico del estudiante. <br> Ejemplo: 200721534")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("spidm").description("Documento de Identificaci&oacute;n del estudiante.  <br> Ejemplo: 79940821 ")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("speriodo").description("Periodo acad&eacute;mico.  <br> Ejemplo: 201519 ")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.outType(ResponseSuspensionesAcademicas.class) 
					.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
					.to("direct:obtenerSuspensionesAcademicas")
	        	
				.get("/disciplinarias")
		        	.description("Consulta las suspensiones disciplinarias de un estudiante")				
		        	.consumes("application/json").produces("application/json")
					.param()
						.name("snumerodocumento").description("Documento de Identificaci&oacute;n del estudiante. <br> Ejemplo: 1061725247")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("slogin").description("Login acad&eacute;mico del estudiante. <br>  Ejemplo: jm.revelo120")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("scodigo").description("C&oacute;digo acad&eacute;mico del estudiante. <br> Ejemplo: 200721534")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("spidm").description("Documento de Identificaci&oacute;n del estudiante.  <br> Ejemplo: 79940821 ")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.outType(ResponseSuspensionesDisciplinarias.class)
					.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
					.to("direct:obtenerSuspensionesDisciplinarias")
				;
	        
	        
	        
	        from("direct:obtenerSuspensionesAcademicas")
        	.to("direct:set-authentication")
        	.to("direct:validatePidmData")
        	.setProperty("speriodo", simple("${headers.speriodo}"))
        	.to("direct:pidmRoute")
			.choice()
				.when(simple("${property.ContinueProcedure} != true"))
		    		.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
		    		.setHeader("CamelHttpResponseCode", simple("404"))
					.process(new ValidateErrorProcessor())
	    		.otherwise()
	    			.log("Header speriodo :::: ${headers.speriodo} :::: property spreiodo ${property.speriodo}")
					.to("velocity:template/suspensiones/query_suspensiones_academicas.vm")
					.setHeader("CamelSqlQuery").simple("${body}")
					.to("direct:bannerSQL")
					.choice()
						.when(simple("${header.CamelHttpResponseCode} != 200"))
							.process(new ValidateErrorProcessor())
						.otherwise()
							.log("Query response ::::::::: ${body}")
							.process(new SuspensionesAcademicasProcessor())
					.end()
    		.end();

        //----- OBTENER SUSPENSIONES ACADEMICAS ----------------------------------------------------------------------
        from("direct:obtenerSuspensionesDisciplinarias")
        	.to("direct:set-authentication")
        	.to("direct:validatePidmData")
        	.to("direct:pidmRoute")
			.choice()
				.when(simple("${property.ContinueProcedure} != true"))
		    		.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
	    			.setHeader("CamelHttpResponseCode", simple("404"))
					.process(new ValidateErrorProcessor())
	    		.otherwise()
	    			.to("velocity:template/suspensiones/query_suspensiones_disciplinarias.vm")
	    			.setHeader("CamelSqlQuery").simple("${body}")
	    			.to("direct:bannerSQL")
					.choice()
						.when(simple("${header.CamelHttpResponseCode} != 200"))
							.process(new ValidateErrorProcessor())
						.otherwise()
							.log("Query response ::::::::: ${body}")
							.process(new SuspensionesDisciplinariasProcessor())
					.end()
    		.end();
	        
	}
}
	
