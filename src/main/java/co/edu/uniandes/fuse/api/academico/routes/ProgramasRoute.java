package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.programa.ProgramaService;
import co.edu.uniandes.fuse.api.academico.models.programa.Programa;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definición de componentes rest/swagger y definición de rutas para el recurso sedes
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class ProgramasRoute extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/programas").description("Consulta de informaci&oacute;n de programas por Id")		
			// programas		
			.get("/{id}/estructura")
				.description("Consulta la estructura  de un programa por Id")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo del programa").defaultValue("M-DISE")
					.endParam()
					.param()
					  	.name("facultad").description("Facultad (Ej: AQ)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("departamento").description("Departamento (Ej: DISE)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
					  	.param()
					  	.name("nivel").description("Nivel (Ej: MA)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				.outType(Programa.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProgramaEstructura")		
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE PAISES
			
			from("direct-vm:getProgramaEstructura")
				.to("velocity:template/programas/query_estructura.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.log("query")
				.to("direct:bannerSQL")
				.log("Respuesta")
				.bean(ProgramaService.class, "getProgramaEstructura")
				.log("final")
		        .wireTap("mock:outputGetProgramaEstructura")
			;			
			
	}
}
