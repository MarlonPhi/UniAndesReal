package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.LoggingLevel;

import co.edu.uniandes.fuse.api.academico.processors.ErrorHandler;
import co.edu.uniandes.fuse.api.academico.processors.ValidatePidmDataProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ClientAuthSalesforceProcessor;
import co.edu.uniandes.fuse.core.utils.beans.Cronometro;


/**
 * Definici�n de rutas de uso transversal
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class CrossRoute extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS  
		onException(Exception.class)
			.handled(true)
			.to("direct-vm:manageException")
			.log("verificando body Exception sql CrossRoute: ${body}")
			;
	
		// ROUTE EXECUTE SQL
		from("direct:bannerSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("banner:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
			;	
		
		from("direct:homologacionesSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("homologaciones:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		from("direct:prescoringSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("prescoring:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		from("direct:qaSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("qa:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		from("direct:nifeSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("nife:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		from("direct:bibliotecaSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("bibliotecaDeposito:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		
		from("direct:nifePortalayrSQL") 
			.bean(Cronometro.class,"inicio(*,${date:now:HH:mm:ss:SSS})") 
			.to("nifePortalayr:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		from("direct:gestDocBannerSQL") 
			.bean(Cronometro.class,"inicio(*,${date:now:HH:mm:ss:SSS})") 
			.to("gestDocBanner:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;
		
		
		 from("direct:AdmisionesSalesForceBannerSQL")
			  .bean(Cronometro.class,"inicio(*,${date:now:HH:mm:ss:SSS})")
			  .to("admisSalesForce:dumy") 
			  .bean(Cronometro.class,"fin(*,${date:now:HH:mm:ss:SSS})")
			  .log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}") 
		;
		 
		 
		
		
		/*from("direct:bannerNukakSQL")
			.bean(Cronometro.class, "inicio(*,${date:now:HH:mm:ss:SSS})")
			.to("bannerNukak:dumy")
			.bean(Cronometro.class, "fin(*,${date:now:HH:mm:ss:SSS})")
			.log("Tiempo de ejecucion SQL: ${property[tiempo]} \n ${header.CamelSqlQuery}")
		;*/
		 //-------------- ROUTE-CONSULTA-PIDM ---------------------------------------------------------------------------------------
	       from("direct:pidmRoute")
		     	.setProperty("ContinueProcedure", constant(true))
		     	.filter(simple("${property.spidm} == null or ${property.spidm} == ''"))
		     		.log("HEADERS EN PIDM ROUTE : ${headers}")
		     		.log("empty SPIDM...consume UNIANDES ::: WS-GET CONSULTA-PIDM")
		     		.setProperty("numeroDocumento", simple("headers.snumerodocumento"))
		     		.setProperty("login", simple("headers.slogin"))
		     		.setProperty("codigo", simple("headers.scodigo"))
		     		.log("property numeroDocumento: ${property.numeroDocumento}")
		     		.log("property login: ${property.login}")
		     		.log("property codigo: ${property.codigo}")
	  	     		.to("direct-vm:pidmRoute")// fuse utils
	  	     		.convertBodyTo(String.class)
	  	     		.setBody().simple("${property.responseAudit}")
	  	     		.log("verificando body de pidm --> ${body}")
	  	     		.setHeader("responseCode").jsonpath("$.MensajeOut.sCodigoRespuesta",true,String.class)
	  	     		.setHeader("Authorization", simple("${property.AutoTokenService}"))
	  	     		.log("Authorization :: ${header.Authorization}")
	  	     		.log("Response service UNIANDES ::: WS-REST CONSULTA-PIDM : ${body}")
	  	     		.setHeader("CamelHttpResponseCode").simple("${headers.responseCode}")
	  	     		.choice()
	  	     			.when(simple("${header.CamelHttpResponseCode} != 200 || ${body.length} == 0"))
	  	     				.log("entrandoooooo a httpError")
	  	     				.setProperty("ContinueProcedure", constant(false))
	  	     			.otherwise()
		  	     			.setProperty("spidm").jsonpath("$.Estudiante.sPidm",true, String.class)
		  	  	     		.setProperty("messageusuario").jsonpath("$.MensajeOut.SMensajeRtaUsuario",true, String.class)
		  	  	     		.setProperty("messagetecnico").jsonpath("$.MensajeOut.SMensajeRtaTecnico",true, String.class)
		  	  	     		.filter(simple("${property.spidm} == null or ${property.spidm} == ''"))
		  	  	     			.setProperty("ContinueProcedure", constant(false))
	  	  	     			.end()	  	  	     			
		     		.end()
		     	.end();
	       
	       
	     //-------------- SET -AUTHENTICATION ---------------------------------------------------------------------------------------
	        from("direct:set-authentication")
	        	.setProperty("AutoTokenService").simple("${headers.Authorization}");
	        
	     //-------------- ROUTE-HERROR-HANDLER --------------------------------------------------------------------------------------
	        from("direct:route-error-handler")
	        	.process(new ErrorHandler());
	     
	    //-------------- Validar PIDM data -----------------------------------------------------------------------------------------
	        from("direct:validatePidmData")
	        	.process(new ValidatePidmDataProcessor());
		
	    //--------------- ROUTE-CONSUME-PROXY ---------------------------------------------------------------------------------------
	        from("direct:route-consume-proxy")
	        	.log("Consumiendo Proxy URL : ${header.CamelHttpUri} ")
	    		//.setHeader(Exchange.CONTENT_TYPE).constant(MediaType.APPLICATION_JSON)
	        	.setHeader("CamelHttpMethod").constant("GET")
	        	.setBody(constant("REQUEST"))
	        	//.log("El body a enviar: ${body} y tambien verificamos los  Header: ${headers}")
	        	.to("http4://dummyHttp?throwExceptionOnFailure=false")
	        	.convertBodyTo(String.class)
	        	.log("Response WS Proxy ::: ${body}")
	        	;
	   //-------------- ROUTE-TOKEN-SICUA -----------------------------------------------------------------------------------------
	        from("direct:token-sicua")
        	.removeHeaders("*")
        	.setHeader("CamelHttpUri").simple("{{sicua.endpoint.obtener.token}}")
        	.setHeader("Authorization").simple("{{sicua.hash}}")
        	.setHeader("CamelHttpMethod").simple("POST")
        	.setHeader("Content-Type").simple("application/x-www-form-urlencoded")
        	.setHeader("grant_type").simple("client_credentials")
        	.setBody(simple(""))
        	.to("http4://dummyHttp?throwExceptionOnFailure=false")
        	.convertBodyTo(String.class)
        	//.log("verificando body de token-sicua: ${body}")
        	.setProperty("accessToken").jsonpath("$.access_token",true, String.class)
        	.log("verificando TOKEN SICUA de ruta propia: ${property.accessToken}")
        ;
	        
	   
	}
}
