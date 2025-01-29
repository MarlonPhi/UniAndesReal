package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.LoggingLevel;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.profesor.ProfesorService;
import co.edu.uniandes.fuse.api.academico.beans.profesor.ValidateParamsProfesorService;
import co.edu.uniandes.fuse.api.academico.models.profesor.ProfesorSecciones;
import co.edu.uniandes.fuse.api.academico.processors.ProfesorSeccionAggregator;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definici�n de componentes rest/swagger y definici�n de rutas para profesor
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class ProfesoresRoute extends RestConfiguration {
	
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/profesores").description("Consulta de informaci&oacute;n de las secciones asignadas a un profesores en un periodo espec&iacute;fico")		
			// programas		
			.get("/{id}/{periodo}/secciones")
				.description("Consulta las secciones de un profesor por Id y periodo")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo/Login del profesor <br> Ejemplo: npena").defaultValue("npena")
					.endParam()
					.param()
					  	.name("periodo").description("Periodo <br> Ejemplo: 201610").defaultValue("201610")
				  	.endParam()
				  	.param()
					  	.name("crn").description("CRN del curso. <br> Ejemplo: 10833")
					  	.type(RestParamType.query)				  	
				  	.required(false)
			  	.endParam()
				.outType(ProfesorSecciones[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProfesorSecciones")
				
			.get("/{login}/producto")
				.description("Consulta en academia los productos de un profesor de acuerdo al slogin")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("login").description("C&oacute;digo/Login del profesor <br> Ejemplo: npena").defaultValue("npena")
					.endParam()
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProfesorProducto")
				
			.get("/academia")
				.description("Consulta en academia los productos de un profesor de acuerdo al slogin")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("idProfesor").description("C&oacute;digo/Login del profesor <br> Ejemplo: npena").defaultValue("npena")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("start").description("Rango comienzo. <br> Ejemplo: 0")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("limit").description("Rango final. <br> Ejemplo: 20")
						.type(RestParamType.query)
						.required(false)
					.endParam()
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProfesorAcademia")
				
			.get("/{login}/info")
				.description("Consulta en academia los productos de un profesor de acuerdo al login")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("login").description("C&oacute;digo/Login del profesor <br> Ejemplo: npena").defaultValue("npena")
					.endParam()
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProfesorInfo")
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE PAISES
			
			from("direct-vm:getProfesorSecciones")
				.bean(ValidateParamsProfesorService.class,"validateSeccion" )
				.log("verif property periodo: ${property.periodo}")
				.log("verif property id: ${property.id}")
				.log("verif property crn: ${property.crn}")
				.to("direct-vm:validaPIDM")
				.to("velocity:template/profesores/query_profesores_secciones.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(ProfesorService.class, "getProfesorSecciones")
				.split(body())
					.setHeader("crn").simple("${body.crn}")
					.enrich("direct-vm:getSeccion", new ProfesorSeccionAggregator())
				.end()
			;	
			
			
			from("direct-vm:getProfesorProducto")
				.bean(ValidateParamsProfesorService.class, "validateLogin")
				.removeHeaders("*")
        		.setHeader("CamelHttpUri").simple("{{url.service.professors}}/?username=${property.login}")
        		.log("verificando headers 1 : ${headers}")
				.to("direct:route-consume-proxy")
				.log("verificando headers 1 : ${headers}")
				.log("verificando body 1 : ${body}")
				.setProperty("idProfesor").jsonpath("$.faculties[0].id", true, String.class)
				.log("verificando property  idProfesor : ${property.idProfesor}")
				.choice()
					.when(simple("${property.idProfesor} == '' || ${property.idProfesor} == null"))
						.setHeader("CamelHttpResponseCode", simple("{{http.code.not.found}}"))
						.process(new ValidateErrorProcessor())
					.otherwise()
						.log("verificando body 2 : ${body}")
						.removeHeaders("*")
						.setHeader("CamelHttpUri").simple("{{url.service.products}}/${property.idProfesor}")
						.log("verificando headers 1 : ${headers}")
						.filter(simple("${property.idProfesor} != ''"))
							.to("direct:route-consume-proxy")
						.end()
						.log("verificando headers 2 : ${headers}")
						.log("verificando body 3 : ${body}")
						.bean(ProfesorService.class, "profesorProducto")
				.end()
				.removeHeaders("*")
			;
			
			from("direct-vm:getProfesorAcademia")
				.bean(ValidateParamsProfesorService.class, "validateIdSLProfesor")
				.filter(simple("${property.start} == '' || ${property.start} == null"))
        			.setProperty("start",constant(0))
        		.end()
        		.filter(simple("${property.limit} == 0 || ${property.limit} == '' || ${property.limit} == null"))
        			.setProperty("limit",constant(20))
        		.end()
        		.removeHeaders("*")
            	.choice()
            		.when(simple("${property.idProfesor} != ''"))
	            		.setHeader("CamelHttpUri").simple("{{url.service.professors}}/${property.idProfesor}")
	        			.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 1")
						.to("direct:route-consume-proxy")
					.otherwise()
						.setHeader("CamelHttpUri").simple("{{url.service.professors}}?start=${property.start}&${property.limit}")
						.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 2")
						.to("direct:route-consume-proxy")
            	.end()
            	//.filter(body().contains("404"))
            	.choice()
					.when(body().contains("404"))
						.setHeader("CamelHttpResponseCode", simple("{{http.code.not.found}}"))
						.process(new ValidateErrorProcessor())
					.otherwise()
						.bean(ProfesorService.class, "profesorAcademia")
				.end()
            	//.bean(ProfesorService.class, "profesorAcademia")
				.removeHeaders("*")
			;
			
			from("direct-vm:getProfesorInfo")
				.bean(ValidateParamsProfesorService.class, "validateLogin")
        		.log("verificando login: ${property.login}")
        		.removeHeaders("*")
        		.setHeader("CamelHttpUri").simple("{{url.service.professors}}/?username=${property.login}")
				.to("direct:route-consume-proxy")
				.setProperty("faculties").jsonpath("$.faculties[0]", true, String.class)
				.choice()
					.when(simple("${property.faculties} == '' || ${property.faculties} == null"))
						.setHeader("CamelHttpResponseCode", simple("{{http.code.not.found}}"))
						.process(new ValidateErrorProcessor())
					.otherwise()
						.bean(ProfesorService.class, "profesorAcademia")
				.end()
				.removeHeaders("*")
			;
			
	}
}
