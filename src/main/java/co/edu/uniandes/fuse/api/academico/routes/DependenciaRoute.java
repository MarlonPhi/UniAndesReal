package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.api.academico.processors.dependencia.InformacionDependenciaProcessor;
import co.edu.uniandes.fuse.api.academico.processors.dependencia.ValidateParametersProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class DependenciaRoute extends RestConfiguration{
	
	public void configure() throws Exception{
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		rest("/dependencia")
			.get("/")
			.description("Consulta y retorna los nombres y apellidos de una persona.")				
        	.consumes("application/json").produces("application/json")
        	.param()
				.name("sdependencianombre").description("Nombre de la dependencia. <br> Ejemplo: ARTE")
				.type(RestParamType.query)
				.required(false)
			.endParam()
        	.param()
				.name("sdependenciaexternalid").description("Identificador externo de la dependencia. <br> Ejemplo: Departamento de Arte")
				.type(RestParamType.query)
				.required(false)
			.endParam()
			.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
			.to("direct:obtenerDependencia")
        ;
		
		
		from("direct:obtenerDependencia")
			.to("direct:set-authentication")
			.process(new ValidateParametersProcessor())
			.removeHeaders("*")
			.log("headers: ${headers}")
			.choice()
				.when(simple("${property.sdepName} != '' && ${property.sdepExtId} == ''"))
					.setHeader("CamelHttpUri").simple("{{url.service.dependencias}}?dependencyName=${property.sdepName}")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 1")
					.to("direct:route-consume-proxy")
				.when(simple("${property.sdepName} == '' && ${property.sdepExtId} != ''"))
					.setHeader("CamelHttpUri").simple("{{url.service.dependencias}}?dependencyExternalId=${property.sdepExtId}")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 2")
					.to("direct:route-consume-proxy")
				.when(simple("${property.sdepName} != '' && ${property.sdepExtId} != ''"))
					.setHeader("CamelHttpUri").simple("{{url.service.dependencias}}?dependencyName=${property.sdepName}&dependencyExternalId=${property.sdepExtId}")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 3")
					.to("direct:route-consume-proxy")
				.otherwise()
					.log("verificando salida otherwise - error diferente")
					.setHeader("CamelHttpUri").simple("{{url.service.dependencias}}")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 4")
					.to("direct:route-consume-proxy")
			.end()
			.choice()
				.when(simple("${header.CamelHttpResponseCode} != 200"))
					.process(new ValidateErrorProcessor())
				.otherwise()
					.process(new InformacionDependenciaProcessor())
			.end()
		;
	}

}