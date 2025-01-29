package co.edu.uniandes.fuse.api.academico.routes;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.component.http4.HttpEntityConverter;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.dataformat.MimeMultipartDataFormat;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.spi.ThreadPoolProfile;
import org.apache.camel.util.ObjectHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.aggregators.EstudianteOnBaseAggregator;
import co.edu.uniandes.fuse.api.academico.beans.estudiante.EstudianteService;
import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaRequest;
import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaResponse;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaBecado;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.RespuestaEsMatriculado;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Horarios;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ProgramasEstudiante;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseNivelAcademico;
import co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones.HomologacionesEstudiante;
import co.edu.uniandes.fuse.api.academico.processors.ProgramasEstudianteProcessor;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.api.academico.processors.ValidateInfoStudentProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ClientAuthSalesforceProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ClientSalesforceProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ClienteOnBaseProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.GetCodeResultOnBaseProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.NivelAcademicoProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.UploadFormOnBaseProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ValidateNivelAcademicoProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ValidateParamsNotasProcessor;
import co.edu.uniandes.fuse.api.academico.processors.datosEstudiante.BecadoProcessor;
import co.edu.uniandes.fuse.api.academico.processors.datosEstudiante.MatriculadoProcessor;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.Cronometro;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definici�n de componentes rest/swagger y definici�n de rutas para el recurso sedes
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class EstudiantesRoute extends RestConfiguration {
	
	public static final Logger log = Logger.getLogger("");
	

	/**
	 *
	 */
	public void configure() throws Exception {
		
		ExecutorService threadPoolSalesforce = Executors.newFixedThreadPool(20);
		ExecutorService threadPoolOnBase = Executors.newFixedThreadPool(20);
		
		ThreadPoolProfile onBaseProfile = getContext().getExecutorServiceManager().getDefaultThreadPoolProfile();
		
		//String cronValueOnBase = getContext().resolvePropertyPlaceholders("{{quartz.cron.onbase}}");
		//String quartzuriOnBase = "quartz2://salesforceTimer?cron=" + cronValueOnBase;
		
		
		
		// EXCEPTIONS 
        onException(SQLException.class)
        .handled(true)
        .to("direct-vm:manageException")
        .end()
        ;
        
        onException(Exception.class)
	        .log(LoggingLevel.ERROR, "Se ha producido una excepci�n: ${exception.message}")
	        .handled(true) // Evita que la excepci�n se propague
	        .to("direct:errorHandler") // Puedes enviar a otra ruta de manejo de errores
	    .end();
		
		
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException")
        .end();
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
			
			/*.get("/{id}/foto")
				.description("Consulta la foto de un estudiante por Id")				
				.consumes("application/json").produces("image/jpeg")
					.param()
						.name("id").description("C&oacute;digo/Login del estudiante").defaultValue("cafanador")
					.endParam()
				.responseMessage().code("404").message("Image not found").endResponseMessage()
				.to("direct-vm:getFotoEstudiante")*/
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
			
			.get("/becado")
				.description("Consulta y retorna si un estudiante es becado en un periodo espec&iacute;fico y el tipo de beca que tiene")
	    		.consumes("application/json").produces("application/json")
		    		.param()
						.name("slogin").description("Login acad&eacute;mico del estudiante del estudiante. <br> Ejemplo: jm.giraldo12")
						.type(RestParamType.query)
						.required(false)
					.endParam()	
		        	.param()
						.name("scodigo").description("C&oacute;digo academico del estudiante. <br> Ejemplo: 201519991")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("snumerodocumento").description("Documento de Identificaci&oacute;n del estudiante. <br> Ejemplo: 1018497535")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("speriodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201610")
						.type(RestParamType.query)
						.required(true)
					.endParam()
				.outType(RespuestaBecado.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:obtenerBecado")
				
			.get("/matriculado")
				.description("Consulta y retorna si un estudiante est&aacute; matriculado para un periodo espec&iacute;fico")
	    		.consumes("application/json").produces("application/json")
		    		.param()
						.name("slogin").description("Login acad&eacute;mico del estudiante del estudiante. <br> Ejemplo: ma.duran13")
						.type(RestParamType.query)
						.required(false)
					.endParam()	
		        	.param()
						.name("scodigo").description("C&oacute;digo academico del estudiante. <br> Ejemplo: 201413898")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("snumerodocumento").description("Documento de Identificaci&oacute;n del estudiante. <br> Ejemplo: 1055315249")
						.type(RestParamType.query)
						.required(false)
					.endParam()
					.param()
						.name("speriodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201410")
						.type(RestParamType.query)
						.required(true)
					.endParam()
				.outType(RespuestaEsMatriculado.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:obtenerMatriculado")
			
			.get("/NivelAcademico/")
				.description("Consulta el nivel academico de un estudiante")				
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
					.name("speriodo").description("Periodo acad&eacute;mico.  <br> Ejemplo: 201820 ")
					.type(RestParamType.query)
					.required(true)
				.endParam()
			.outType(ResponseNivelAcademico.class)
			.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
			.to("direct:obtenerNivelAcademico")
			
			.get("/pruebaGestDocumental")
				.description("api de prueba")				
	    		.consumes("application/json").produces("application/json")
	    		.param()
					.name("documento").description("Periodo acad&eacute;mico.  <br> Ejemplo: 201820 ")
					.type(RestParamType.query)
					.required(true)
				.endParam()
	    		.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
	    	.to("direct:bodySplitProcessInitial")
	    		//.to("direct:processBannerOnBase")
	    		
	    		
	    	.get("/horarios")
		    	.description("Consulta y retorna si un estudiante est&aacute; matriculado para un periodo espec&iacute;fico")
	    		.consumes("application/json").produces("application/json")
		    		.param()
						.name("login").description("Login acad&eacute;mico del estudiante del estudiante. <br> Ejemplo: ma.duran13")
						.type(RestParamType.query)
						.required(false)
					.endParam()	
					.param()
						.name("periodo").description("Periodo acad&eacute;mico. <br> Ejemplo: 201410")
						.type(RestParamType.query)
						.required(true)
					.endParam()
				.outType(Horarios[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
			.to("direct:horarios")
			
			.get("/pruebaOnBase").consumes("application/json").produces("application/json").responseMessage()
				.code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error")
				.responseModel(ErrorResponse.class).endResponseMessage().to("direct:processBannerOnBase")
	
				.get("/pruebaSalesforce").consumes("application/json").produces("application/json").responseMessage()
				.code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error")
				.responseModel(ErrorResponse.class).endResponseMessage()
			.to("direct:SendDataAdmisionesSalesforce")

			.get("/pruebaSalesforce/post").consumes("application/json").produces("application/json")
				.param().name("periodo")
					.description("Periodo acad&eacute;mico. <br> Ejemplo: 201410").type(RestParamType.query)
					.required(true)
				.endParam()
				.param().name("idFormulario")
					.description("Periodo acad&eacute;mico. <br> Ejemplo: 201410").type(RestParamType.query)
					.required(true)
				.endParam()
				.param().name("secuenciaFormulario")
					.description("Periodo acad&eacute;mico. <br> Ejemplo: 201410").type(RestParamType.query)
					.required(true)
				.endParam().responseMessage().code("000")
				.message("300 - Redirect<br>400 - Client Error<br>500 - Server Error")
				.responseModel(ErrorResponse.class).endResponseMessage()
			.to("direct:pruebaPostSalesforce")
			
			
		;
		
		
		
		
//ROUTES DEVELOPED -----------------------------------------------------------------------------------------------//			
		
		
		

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
			
			/*from("direct-vm:getFotoEstudiante")
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
			.end();*/
			
			/*from("direct-vm:getFotoEstudianteData")
			.to("direct-vm:getFotoEstudiante")
			.bean(EstudianteService.class, "getFotoEstudiante");*/
			//.to("mock:GetFotoEstudianteData");
			
		
		//ROUTE HOMOLOGACIOENS
			from("direct:getHomologacionesEstudiante")
				.setHeader("slogin").simple("${headers.login}")
				//.process(new ValidateInfoStudentProcessor())
				//.to("direct-vm:validaPIDM")
				.to("direct:pidmRoute")
				 .choice()
		        	.when(simple("${property.ContinueProcedure} != true"))
			        	.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
			        	.setHeader("CamelHttpResponseCode", simple("404"))
						.process(new ValidateErrorProcessor())
					.otherwise()
						.loop(23)
						.setProperty("parametro").simple("${exchangeProperty.CamelLoopIndex}")
						.log("${property[parametro]}")
						.to("velocity:template/estudiantes/query_homologaciones.vm")
						.setHeader("CamelSqlQuery").simple("${body}")
						.to("direct:homologacionesSQL")
						.log("Respuesta: ${body}")
						.bean(EstudianteService.class, "getDatoHomologaciones")
						.end()
						.log("verificando body.. ${body}")
						.removeHeaders("*")
						.bean(EstudianteService.class, "getHomologacionesEstudiante")
						.log("verificando headers despuesd e bean: ${headers} y ${property.responseBean}")
						.filter(simple("${header.CamelHttpResponseCode} == 404"))
							.log("No homologar!")
							.process(new ValidateErrorProcessor())
							
						.end()
						.log("Homologar!")
				.end()
				.to("mock:GetHomologacionesEstudiante");
			
			
		//ROUTE OBTENER BECADO
	        from("direct:obtenerBecado")
		        .process(new ValidateInfoStudentProcessor())
		        .setProperty("Speriodo").simple("${headers.speriodo}")
		        .to("direct:pidmRoute")
		        .log("verificando periodo: ${property.Speriodo}")
		        .log("verificando el pidm: ${property.spidm}")
		        .choice()
		        	.when(simple("${property.ContinueProcedure} != true"))
			        	.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
			        	.setHeader("CamelHttpResponseCode", simple("404"))
						.process(new ValidateErrorProcessor())
		            .otherwise()
			            .to("velocity:template/estudiantes/query-becados.vm")
		            	.setHeader("CamelSqlQuery").simple("${body}")
		            	.log("header sql: ${headers}")
		            	.to("direct:bannerSQL")
		            	.log("body sql: ${body}")
		            	.choice()
	            		.when(simple("${header.responseCode} != 200"))
	            			.process(new ValidateErrorProcessor())
	            		.otherwise()
	            			.process(new BecadoProcessor())
	            			.log("verificando body final: ${body}")
	            	.end()
		            	
		        .end()
	        ;
	        

	      //ROUTE OBTENER MATRICULADO
	        from("direct:obtenerMatriculado")
		        .process(new ValidateInfoStudentProcessor())
		        .setProperty("Speriodo").simple("${headers.speriodo}")
		        .to("direct:pidmRoute")
		        .choice()
		        	.when(simple("${property.ContinueProcedure} != true"))
			        	.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
			        	.setHeader("CamelHttpResponseCode", simple("404"))
						.process(new ValidateErrorProcessor())
		             .otherwise()
			             .to("velocity:template/estudiantes/query-matriculados.vm")
			            	.setHeader("CamelSqlQuery").simple("${body}")
			            	.log("header sql: ${headers}")
			            	.to("direct:bannerSQL")
			            	.log("body sql: ${body}")
			            	.choice()
				         		.when(simple("${header.CamelHttpResponseCode} != 200"))
				         			.process(new ValidateErrorProcessor())
				         		.otherwise()
				         			.process(new MatriculadoProcessor())
				         			.log("verificando body final: ${body}")
				         	.end()
		         .end()
		        	
	        ;
	        

	      //ROUTE OBTENER NIVEL ACADEMICO
	        from("direct:obtenerNivelAcademico")
	        .to("direct:set-authentication")
	    	.to("direct:validatePidmData")
	    	.process(new ValidateNivelAcademicoProcessor())
	    	.to("direct:pidmRoute")
			.choice()
				.when(simple("${property.ContinueProcedure} != true"))
		    		.log(LoggingLevel.ERROR,"::::: Error en Consultar SPIDM")
		    		.setHeader("CamelHttpResponseCode", simple("404"))
					.process(new ValidateErrorProcessor())
				.otherwise()
					.to("velocity:template/estudiantes/query_consulta_nivel_academico.vm")
					.setHeader("CamelSqlQuery").simple("${body}")
					.to("direct:nifeSQL")
					.choice()
						.when(simple("${header.CamelHttpResponseCode} != 200"))
							.process(new ValidateErrorProcessor())
						.otherwise()
							.log("Query response ::::::::: ${body}")
							.process(new NivelAcademicoProcessor())
					.end()
			.end();	

	    

		// RUTA PARA HORARIOS DE ESTUDIANTES
			from("direct:horarios")
				.log("::::::::: START ROUTE NOTES :::::::::")
				.process(new ValidateParamsNotasProcessor())
	            .to("velocity:template/estudiantes/query-notas.vm")
            	.setHeader("CamelSqlQuery").simple("${body}")
            	.log("header sql: ${headers}")
            	.to("direct:bannerSQL")
            	.log("body sql: ${body}")
            	/*.choice()
	        		.when(simple("${body.size()} == 0"))
	        			.log("entrnado a w ................")
	        			.process(new ValidateErrorProcessor())
	        		.otherwise()
	        			.log("entrnado a otherwise ................")
	        			.bean(EstudianteService.class, "getObjectNotes")
	        			.log("verificando body final: ${body}")
		        .end()*/
		        .bean(EstudianteService.class, "getObjectNotes")
				.log("::::::::: FINISHED ROUTE HORARIOS :::::::::")
			;
			
	}
}
