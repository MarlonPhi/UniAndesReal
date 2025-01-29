package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.programa.ProgramaService;
import co.edu.uniandes.fuse.api.academico.beans.seccion.SeccionService;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionEstudiantes;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definición de componentes rest/swagger y definición de rutas para seccion
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
			// consulta seccion por crn
			.get("/{crn}/{periodo}")
			.description("Consulta la seccion por CRN")				
			.consumes("application/json").produces("application/json")
				.param()
					.name("crn").description("CRN").defaultValue("19434")
				.endParam()
				.param()
					.name("periodo").description("Periodo").defaultValue("201610")
				.endParam()
			.outType(Seccion.class)
			.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
			.to("direct-vm:getSeccion")		
			// estudiantes de la seccion		
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
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE SECCION
			from("direct-vm:getSeccion")
			.to("velocity:template/secciones/query_secciones.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.bean(SeccionService.class, "getSeccion")
	        .wireTap("mock:outputGetSeccion");
		
			//ROUTE ESTUDIANTES SECCION
			from("direct-vm:getSeccionEstudiantes")
			.to("velocity:template/secciones/query_secciones_estudiantes.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.to("direct:bannerSQL")
			.bean(SeccionService.class, "getSeccionEstudiantes")
	        .wireTap("mock:outputGetSeccionEstudiantes");
			;			
			
	}
}
