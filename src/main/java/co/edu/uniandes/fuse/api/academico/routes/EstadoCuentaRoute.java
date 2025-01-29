package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.estudiante.EstudianteService;
import co.edu.uniandes.fuse.api.academico.beans.estudiante.ValidateParamsEstudianteService;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ContratoM;
import co.edu.uniandes.fuse.api.academico.models.estudiante.EstadoEzProxy;
import co.edu.uniandes.fuse.api.academico.models.estudiante.EstadoMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.HistorialPrestamos;
import co.edu.uniandes.fuse.api.academico.models.estudiante.PagosMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponsePrestamo;
import co.edu.uniandes.fuse.api.academico.processors.BDconnectionMultasProcessor;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.api.academico.processors.ValidateParamsActMultasProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ConvertBodyEzProxyProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ResponseEzProxyProcessor;
import co.edu.uniandes.fuse.api.academico.processors.Estudiante.ValidateParamsEzProxyProcessor;
import co.edu.uniandes.fuse.api.academico.processors.estadoCuenta.MultasProcessor;
import co.edu.uniandes.fuse.api.academico.processors.estadoCuenta.PagosProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class EstadoCuentaRoute extends RestConfiguration{
	
	public void configure() throws Exception {
		
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
        
        
        rest("/cuenta").description("Consulta de informaci&oacute;n del estado de la cuenta del estudiante")
        
	        .get("/{slogin}/multas")
				.description("consulta y retorna la informaci&oacute;n de las retenciones de un estudiante.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("slogin").description("Login acad&eacute;mico del estudiante. <br> Ejemplo: cafanador")
				.endParam()
				.outType(ResponseMultas.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:obtenerMultas")
			
			.put("/multas")
				.description("Actualiza las multas de un estudiante.")				
				.consumes("application/json").produces("application/json")
				.type(ContratoM.class)
				.outType(EstadoMultas.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:actualizarMultas")
				
			.get("/{slogin}/historial/prestamos")
				.description("consulta y retorna la informaci&oacute;n del historial de prestamos de un estudiante.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("slogin").description("Login acad&eacute;mico del estudiante. <br> Ejemplo: j.rodriguezhxx &oacute; j.rodriguezhxx  caleal")
				.endParam()
				.outType(HistorialPrestamos[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:obtenerHistorialPrestamos")
				
	        .get("/{slogin}/pagos")
				.description("consulta y retorna la informaci&oacute;n de los pagos realizados por un estudiante a multas previas.")
				.consumes("application/json").produces("application/json")
				.param()
					.name("slogin").description("Login acad&eacute;mico del estudiante. <br> Ejemplo: cafanador")
				.endParam()
				.outType(PagosMultas[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:obtenerPagos")
			
			.get("/{id}/prestamos")
				.description("consulta y retorna la informaci&oacute;n del historial de prestamos de un estudiante.")
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("Login/identificaci&oacute;n acad&eacute;mico del estudiante. <br> Ejemplo: j.rodriguezhxx &oacute; j.rodriguezhxx  caleal")
					.endParam()
					.param()
						.name("dfechaprestamo").description("Login acad&eacute;mico del estudiante. <br> Ejemplo: j.rodriguezhxx &oacute; j.rodriguezhxx  caleal")
						.type(RestParamType.query)
						.required(true)
					.endParam()
					.param()
						.name("dfechadevolucion").description("Login acad&eacute;mico del estudiante. <br> Ejemplo: j.rodriguezhxx &oacute; j.rodriguezhxx  caleal")
						.type(RestParamType.query)
						.required(true)
					.endParam()
				.outType(ResponsePrestamo.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()        	
				.to("direct:obtenerPrestamos")
			
			.post("/ezProxy")
				.description("Estado de proxy al iniciar sesion.")
				.consumes("application/json").produces("application/json")
					.param()
						.name("username").description("Login acad&eacute;mico del estudiante o administrativo. <br> Ejemplo: (correo electronico sin '@uniandes.edu.co')")
						.type(RestParamType.query)
						.required(true)
					.endParam()
					.param()
						.name("password").description("Password acad&eacute;mico del estudiante.")
						.type(RestParamType.query)
						.required(true)
					.endParam()
					.param()
						.name("cliente").description("Tipo de cliente. <br> Ejemplo: GENERAL")
						.type(RestParamType.query)
						.required(true)
					.endParam()
					.param()
						.name("users_vip").description("Si es usuario vip. <br> Ejemplo: no")
						.type(RestParamType.query)
						.required(true)
					.endParam()
					.param()
						.name("access_vip").description("Users con acceso vip. <br> Ejemplo: iortiz;caleal;lcaldero")
						.type(RestParamType.query)
						.required(true)
					.endParam()	
				.outType(EstadoEzProxy.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:validacionEzProxy")	
        
        ;
        
        
        
		// REST & SWAGGER COMPONENTS				
		// ROUTE PROGRAMAS	
        
        from("direct:obtenerMultas")
        	.bean(ValidateParamsEstudianteService.class, "validateLogin")
	    	.setProperty("server").simple("{{datasource.qa.server}}")
	    	.setProperty("user").simple("{{datasource.qa.user}}")
	    	.setProperty("password").simple("{{datasource.qa.password}}")
	    	.process(new MultasProcessor())
	    	.removeHeaders("*")
	    	.log("body:${body}")
	    ;
        
        from("direct:obtenerPagos")
	    	.bean(ValidateParamsEstudianteService.class, "validateLogin")
	    	.setProperty("server").simple("{{datasource.qa.server}}")
	    	.setProperty("user").simple("{{datasource.qa.user}}")
	    	.setProperty("password").simple("{{datasource.qa.password}}")
	    	.process(new PagosProcessor())
	    	.removeHeaders("*")
	    	.log("body:${body}")
	    ;

        from("direct:actualizarMultas")
        	.log("###### INICIANDO PROCESO ACTUALIZACION MULTAS...")
	    	.process(new ValidateParamsActMultasProcessor())
	    	.setProperty("operationService").simple("${body.getSNombreOperacion()}")
	    	.setProperty("SCODIGO").simple("${body.getSPidm()}")
			.setProperty("IDPROCESO").simple("${body.getSIdentificadorProceso()}")
			.setProperty("ITIPOPAGO").simple("${body.getITipoPago()}")
			.setProperty("IBILLNUMBER").simple("${body.getIBillBNumber()}")
			.setProperty("IMONTOPAGADO").simple("${body.getDMontoPagado()}")
			.setProperty("ILIBRARY").simple("${body.getILibrary()}")
			.setProperty("user").simple("{{datasource.qa.user}}")
        	.setProperty("password").simple("{{datasource.qa.password}}")
        	.setProperty("server").simple("{{datasource.qa.server}}")
			.setProperty("packageF").simple("{{strore.package}}")
			.setProperty("function").simple("{{strore.function.multas}}")
			.process(new BDconnectionMultasProcessor())
			.marshal().json(JsonLibrary.Jackson)
			.log("###### SALIENDO ACTUALIZACION MULTAS...")
	    ;
        
        from("direct:obtenerHistorialPrestamos")
        	.bean(ValidateParamsEstudianteService.class, "validateLogin")
	    	.to("velocity:template/estudiantes/estadoCuenta/query-historial-prestamos.vm")
	    	.setHeader("CamelSqlQuery").simple("${body}")
			.log("body antes de ejecutarsql: ${body}")
	    	.to("direct:qaSQL")
	    	.log("body sql ejecutado: ${body}")
	    	.log("headers a verificar antes del choice 1: ${headers}")
	    	.choice()
				.when(body().isNotNull())
					.setHeader("CamelHttpResponseCode").simple("{{http.code.ok}}")
					.log("salida ok")
				.otherwise()
					.setHeader("CamelHttpResponseCode").simple("{{http.code.not.found}}")
					.log("salida fail")
			.end()
			.log("headers de headers a verificar antes del choice 2 multas: ${headers}")
	    	.choice()
	    		.when(simple("${headers.CamelHttpResponseCode} != 200"))
	    			.process(new ValidateErrorProcessor())
	    		.otherwise()
	    		.log("VERIFICANDO BODY ANTES DEL PROCESSOR ---> ${body}")
	    			.bean(EstudianteService.class, "getHistorialPrestamos")
	    	.end()
	    	
	    ;
        
        from("direct:obtenerPrestamos")
	    	.bean(ValidateParamsEstudianteService.class, "validatePrestamo")
	    	//.setProperty("id", header("id"))
	    	.to("direct-vm:validaPIDM")
	    	.setHeader("id", simple("${property.id}"))
	    	.to("direct-vm:getDocumentos")
	    	.setProperty("sizeBodyDocs", simple("${body.size()}"))
	    	.marshal().json(JsonLibrary.Jackson)
	    	.setProperty("DocumentosRoute").jsonpath("$.[*]",true, String.class)
	    	.setProperty("flag").jsonpath("$.[0].activo",true, String.class)
	    	.choice()
				.when(simple("${property.flag} == true"))
					.setProperty("documento").jsonpath("$.[0].numeroDocumento",true, String.class)
		        	.to("velocity:template/estudiantes/estadoCuenta/query-prestamo.vm")
		        	.setHeader("CamelSqlQuery").simple("${body}")
		        	.to("direct:bibliotecaSQL")
		        	.log("body sql ejecutado: ${body}")
		        	.bean(EstudianteService.class,"getObtenerPrestamos")
				.otherwise()
		        	.log(LoggingLevel.ERROR,"::::: Error - User doesn´t be active") // cambiar estos errores por un response con mensaje y code response
	                .setProperty("message", simple("Usuario no es activo en la Universidad"))
	                .setHeader("HttpErrorCode").simple("{{http.code.bad.request}}")
	                .to("direct:route-error-handler")
			.end()
		;
        

        
        from("direct:validacionEzProxy")
    	.setProperty("username").simple("${headers.username}")
    	.setProperty("password").simple("${headers.password}")
    	.setProperty("cliente").simple("${headers.cliente}")
    	.setProperty("usersVip").simple("${headers.users_vip}")
    	.setProperty("accessVip").simple("${headers.access_vip}")
    	.removeHeaders("*")
    	.process(new ConvertBodyEzProxyProcessor())
    	.setProperty("request").simple("${body}")
    	.setProperty("clientesPublicos").simple("{{tipo.usuarios.publicos}}")
    	.process(new ValidateParamsEzProxyProcessor())
    	.filter(simple("${property.continua} != '0'"))
    		.choice()
    			.when(simple("${property.esPublico} == true"))
    				.to("direct:conteoPerfilesPublico")
    			.otherwise()
    				.setProperty("estado").constant(true)
    				.to("direct:validarAutenticacionDA")
        			.to("direct:conteoPerfiles")
		     .end()	
    	.end()
    ;
    
  //child routes of ezproxy -----------------------------------------------------------------------------
    
    from("direct:validarAutenticacionDA")
    	.log("Consume UNIANDES ::: WS-REST AUTENTICACION POR LOGIN - DIRECTORIO ACTIVO")
    	.to("velocity:template/estudiantes/estadoCuenta/request-validacion-autenticacion.vm")
    	.log("verificando body : ${body}")
    	.unmarshal().json(JsonLibrary.Jackson)
    	.to("direct-vm:autenticacionLogin")
    	.log("verificando body de direct autentication: ${body}")
    	.log("VERIFICANDO HEADERS:  ${headers}")
    	.choice()
    		.when(simple("${headers.CamelHttpResponseCode} == 400"))
    			.setProperty("ValidacionDA").jsonpath("$.OperacionExitosa",true, Boolean.class)
    			.log("validacion false")
    		.otherwise()
        		.marshal().json(JsonLibrary.Jackson)
	        	.log("verificando body despeus de marshal --> ${body}")
	        	.setProperty("ValidacionDA").jsonpath("$.OperacionExitosa",true, Boolean.class)
    	.end()
    ; 
     
    from("direct:conteoPerfilesPublico")
        .to("velocity:template/estudiantes/estadoCuenta/query-conteoPerfiles-publico.vm")
        .setHeader("CamelSqlQuery").simple("${body}")
    	.to("direct:qaSQL")
    	.log("body sql ejecutado: ${body}")
    	.log("verificando headers:::: ${headers}")
    	.choice()
			.when(body().isNotNull())
				.setHeader("CamelHttpResponseCode").simple("{{http.code.ok}}")
				.log("salida ok")
			.otherwise()
				.setHeader("CamelHttpResponseCode").simple("{{http.code.not.found}}")
				.log("salida fail")
		.end()
		.log("headers de headers conteoPerfilesPublico: ${headers}")
    	.choice()
    		.when(simple("${headers.CamelHttpResponseCode} != 200"))
    			.process(new ValidateErrorProcessor())
    		.otherwise()
    		.log("VERIFICANDO BODY ANTES DEL PROCESSOR ---> ${body}")
    		
    		.process(new ResponseEzProxyProcessor())
    	.end()
    ;
    
    from("direct:conteoPerfiles")
    	.to("velocity:template/estudiantes/estadoCuenta/query-conteoPerfiles.vm")
    	.setHeader("CamelSqlQuery").simple("${body}")
    	.to("direct:qaSQL")
    	.log("body sql ejecutado: ${body}")
    	.log("verificando headers:::: ${headers}")
    	.choice()
			.when(body().isNotNull())
				.setHeader("CamelHttpResponseCode").simple("{{http.code.ok}}")
				.log("salida ok")
			.otherwise()
				.setHeader("CamelHttpResponseCode").simple("{{http.code.not.found}}")
				.log("salida fail")
		.end()
		.log("headers de headers conteoPerfiles: ${headers}")
    	.choice()
    		.when(simple("${headers.CamelHttpResponseCode} != 200"))
    			.process(new ValidateErrorProcessor())
    		.otherwise()
    		.process(new ResponseEzProxyProcessor())
    	.end()
    ;
	}

}
