package co.edu.uniandes.fuse.api.academico.routes;


import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.estudiante.EstudianteService;
import co.edu.uniandes.fuse.api.academico.models.estudiante.FotoEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ProgramasEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.HomologacionesEstudiante;
import co.edu.uniandes.fuse.api.academico.models.programa.Programa;
import co.edu.uniandes.fuse.api.academico.processors.ProgramasEstudianteProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definición de componentes rest/swagger y definición de rutas para el recurso sedes
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class EstudiantesRoute extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/estudiantes").description("Consulta de informaci&oacute;n de estudiantes")		
			// programas		
			.get("/{id}/programas")
				.description("Consulta los programas de un estudiante por Id")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo/Login del estudiante").defaultValue("cafanador")
					.endParam()
					.param()
					  	.name("periodo").description("Periodo (Ej: 201620)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
					 .param()
					  	.name("documento").description("Documento del estudiante (Ej: 201021881)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				.outType(ProgramasEstudiante[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getProgramasEstudiante")
			.get("/{id}/foto")
				.description("Consulta la foto de un estudiante por Id")				
				.consumes("application/json").produces("image/jpeg")
					.param()
						.name("id").description("C&oacute;digo/Login del estudiante").defaultValue("cafanador")
					.endParam()
				.responseMessage().code("404").message("Image not found").endResponseMessage()
				.to("direct-vm:getFotoEstudiante")
			/*.get("/{id}/foto/data")
				.description("Consulta la data de la foto de un estudiante por Id")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo/Login del estudiante").defaultValue("cafanador")
					.endParam()
				.outType(FotoEstudiante.class)
				.to("direct-vm:getFotoEstudianteData")*/
			.get("/homologaciones")
				.description("Consulta la data de homologaciones de un estudiante por login/id")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("login").description("Login del estudiante").defaultValue("cafanador")
						.type(RestParamType.query)				  	
					  	.required(true)
					.endParam()
				.outType(HomologacionesEstudiante.class)
				.to("direct:getHomologacionesEstudiante")
			
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE PROGRAMAS	
			
			from("direct-vm:getProgramasEstudiante")
				.to("direct-vm:validaPIDM")
				.to("velocity:template/estudiantes/query_programas.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(EstudianteService.class, "getProgramasEstudiante")
				.split(body())
		        	.setHeader("id").simple("${body[codigo]}")
		        	.setHeader("prioridad").simple("${body[prioridad]}")
		        	.to("direct-vm:getProgramaEstructura")
		        	.process(new ProgramasEstudianteProcessor())
		        .end()
		        .log("Programas: ${property[programasEstudiante]}")
		        .setBody().simple("${property[programasEstudiante]}")
		        .to("mock:GetProgramasEstudiante")
			;	
			
			from("direct-vm:getFotoEstudiante")
				.log("Fuente: {{photos.source}}")
				.choice()
					.when(simple("'{{photos.source}}' == 'SFTP'"))
						.setHeader("finalPath", simple("sftp://{{sftp.server}}{{sftp.path.photos}}?username={{sftp.username}}&password={{sftp.password}}&include=${headers.id}\\.(jpg|jpeg|JPG|JPEG)&idempotent=false&disconnect=true"))
					.otherwise()
						.setHeader("finalPath", simple("file:{{path.photos}}?include=${headers.id}\\.(jpg|jpeg|JPG|JPEG)&noop=true&idempotent=false"))
				.end()
				.log("Final path: ${headers.finalPath}")
				.pollEnrich()
					.simple("${headers.finalPath}")
					.timeout(20000)
				.end()
				.choice()
					.when(simple("${body} != null"))
						.log("Size: ${headers.CamelFileLength} // LastModified: ${headers.CamelFileLastModified}")
					.otherwise()
						.setHeader("CamelHttpResponseCode").simple("404")
				.end()
			.end();
			
			/*from("direct-vm:getFotoEstudianteData")
			.to("direct-vm:getFotoEstudiante")
			.bean(EstudianteService.class, "getFotoEstudiante");*/
			//.to("mock:GetFotoEstudianteData");
			
			from("direct:getHomologacionesEstudiante")
			.setHeader("id").simple("${headers.login}")
			.to("direct-vm:validaPIDM")
			.loop(23)
				.setProperty("parametro").simple("${exchangeProperty.CamelLoopIndex}")
				.log("${property[parametro]}")
				.to("velocity:template/estudiantes/query_homologaciones.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:homologacionesSQL")
				.log("Respuesta: ${body}")
				.bean(EstudianteService.class, "getDatoHomologaciones")
			.end()
			.log("Homologar!")
			.bean(EstudianteService.class, "getHomologacionesEstudiante")
			//.log("Transformado ${body}")
			.to("mock:GetHomologacionesEstudiante");
			
			
			
			
	}
}
