package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.homologaciones.HomologacionesService;
import co.edu.uniandes.fuse.api.academico.models.programa.ProgramaHomologaciones;
import co.edu.uniandes.fuse.api.academico.processors.HomologacionesPogramasprocessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class HomologacionesRoute extends RestConfiguration{
	
	public void configure() throws Exception{
		
		 onException(Exception.class)
	        .handled(true)
	        .to("direct-vm:manageException");
	        ;
	        
	     
	      rest("/homologaciones").description("Consulta de programas para homologaciones")
	      		.get("/programas")
		      		.description("Consulta la lista de programas de la universidad")
		      		.consumes("application/json").produces("application/json")
			      		.param()
						  	.name("programName").description("Ej: MAESTRIA ")
						  	.type(RestParamType.query)				  	
						  	.required(false)
					  	.endParam()
		      		.outType(ProgramaHomologaciones[].class)
		      		.responseMessage().code("000")
	      							.message("300 - Redirect<br>400 - Client Error<br>500 - Server Error")
	      							.responseModel(ErrorResponse.class)
	      							.endResponseMessage()
	      		    .to("direct-vm:getProgramas")
	      ;
	      
	      
	      from("direct-vm:getProgramas")
	      		.log(" SALIDA DE PROGRAMA --->  ${headers.programName}")
	      		.process(new HomologacionesPogramasprocessor())
	      		.log(" SALIDA DE PROGRAMA --->  ${headers.programName}")
	      		.to("velocity:template/homologaciones/query_programas_estructura.vm")
	      		.setHeader("camelSqlQuery").simple("${body}")
	      		.log("SQL: ${body}")
	      		.to("direct:bannerSQL")
	      		.log("Response: ${body}")
	      		.bean(HomologacionesService.class, "getProgramasH")
	      		.wireTap("mock:outputGetProgramas")
	      ;
	}

}
