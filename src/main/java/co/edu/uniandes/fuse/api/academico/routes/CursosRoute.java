package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.cursos.CursosService;
import co.edu.uniandes.fuse.api.academico.models.cursos.Cursos;
import co.edu.uniandes.fuse.api.academico.processors.CursosProcessor;
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
				.description("Consulta los cursos por codigo o nombre")				
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
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE CURSOS
			
			from("direct-vm:getCursos")
			    .process(new CursosProcessor()) 
				.to("velocity:template/cursos/query_cursos.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.log("SQL: ${body}")
				.to("direct:bannerSQL")
				.log("Response: ${body}")
				.bean(CursosService.class, "getCursos")
		        .wireTap("mock:outputGetCursos")
			;			
			
	}
}
