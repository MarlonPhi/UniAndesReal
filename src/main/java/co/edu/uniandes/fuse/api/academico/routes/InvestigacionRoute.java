package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.investigacion.InvestigacionService;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class InvestigacionRoute extends RestConfiguration {
	
	public void configure() throws Exception{
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException")
        ;
        
        rest("/investigacion")
        
        	.get("/researchGroups")
	        	.description("Consulta y retorna los nombres y apellidos de una persona.")				
	        	.consumes("application/json").produces("application/json")
		        	.param()
						.name("sigla").description("Sigla (n&uacute;mero sigla del grupo). <br> Ejemplo: 00000000000459")
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
					.param()
						.name("idGroup").description("Id del grupo de investigaci&oacute;n. <br> Ejemplo: 485")
						.type(RestParamType.query)
						.required(false)
					.endParam()
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:obtenerGrupo")
        ;
        
        from("direct:obtenerGrupo")
        	.bean(InvestigacionService.class, "validateParamsGrupos")
        	.filter(simple("${property.start} == '' || ${property.start} == null"))
        		.setProperty("start",constant(0))
        	.end()
        	.filter(simple("${property.limit} == 0 || ${property.limit} == '' || ${property.limit} == null"))
        		.setProperty("limit",constant(20))
        	.end()
        	.removeHeaders("*")
        	.choice()
        		.when(simple("${property.sigla} != '' && ${property.idGroup} == ''"))
        			.setHeader("CamelHttpUri").simple("{{url.service.researchGroups}}?sigla={{qMarks}}${property.sigla}{{qMarks}}&start=${property.start}&${property.limit}")
        			.log("verificando header: CamelHttpUri-> ${headers.CamelHttpUri} ")
        			.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 1")
					.to("direct:route-consume-proxy")
				.when(simple("${property.sigla} == '' && ${property.idGroup} != ''"))
					.setHeader("CamelHttpUri").simple("{{url.service.researchGroups}}/${property.idGroup}")
					.log("verificando header: CamelHttpUri-> ${headers.CamelHttpUri} ")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 2")
					.to("direct:route-consume-proxy")
				.when(simple("${property.sigla} != '' && ${property.idGroup} != ''"))
					.setHeader("CamelHttpUri").simple("{{url.service.researchGroups}}/${property.idGroup}")
					.log("verificando header: CamelHttpUri-> ${headers.CamelHttpUri} ")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 3")
					.to("direct:route-consume-proxy")
				.otherwise()
					.setHeader("CamelHttpUri").simple("{{url.service.researchGroups}}")
					.log("verificando header: CamelHttpUri-> ${headers.CamelHttpUri} ")
					.log(LoggingLevel.WARN, "::::::: ENTRO WHEN 4")
					.to("direct:route-consume-proxy")
			.end()
			.bean(InvestigacionService.class, "getGrupos")
        ;
	}

}
