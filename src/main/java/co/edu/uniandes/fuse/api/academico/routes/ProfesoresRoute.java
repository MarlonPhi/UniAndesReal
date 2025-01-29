package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.profesor.ProfesorService;
import co.edu.uniandes.fuse.api.academico.models.profesor.ProfesorSecciones;
import co.edu.uniandes.fuse.api.academico.processors.ProfesorSeccionAggregator;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definición de componentes rest/swagger y definición de rutas para profesor
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
		rest("/profesores").description("Consulta de informaci&oacute;n de profesores")		
			// programas		
			.get("/{id}/{periodo}/secciones")
				.description("Consulta las secciones de un profesor por Id y periodo")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo/Login del profesor").defaultValue("npena")
					.endParam()
					.param()
					  	.name("periodo").description("Periodo").defaultValue("201610")
				  	.endParam()
				  	.param()
					  	.name("codigoCurso").description("C&oacute;digo del curso")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("nombreCurso").description("Nombre del curso")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
					  	.name("seccion").description("Num Seccion")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				.outType(ProfesorSecciones[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProfesorSecciones")		
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE PAISES
			
			from("direct-vm:getProfesorSecciones")
				.to("direct-vm:validaPIDM")
				.to("velocity:template/profesores/query_profesores_secciones.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(ProfesorService.class, "getProfesorSecciones")
				.split(body())
					.setHeader("crn").simple("${body.crn}")
					.enrich("direct-vm:getSeccion", new ProfesorSeccionAggregator())
				.end()
		        .wireTap("mock:outputGetProfesorSecciones")
			;			
			
	}
}
