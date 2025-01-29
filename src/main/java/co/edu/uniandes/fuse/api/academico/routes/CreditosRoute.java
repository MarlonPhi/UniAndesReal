package co.edu.uniandes.fuse.api.academico.routes;

import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.creditos.CreditosService;
import co.edu.uniandes.fuse.api.academico.beans.egresado.EgresadoService;
import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaRequest;
import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaResponse;
import co.edu.uniandes.fuse.api.academico.models.egresado.Titulos;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class CreditosRoute  extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/creditos").description("Consulta de informaci&oacute;n de egresados")		
			// transferencias		
			.post("/transferir")
				.description("Transferencia de creditos para un estudiante")				
				.consumes("application/json").produces("application/json")
				.type(TransferenciaRequest.class)
				.outType(TransferenciaResponse.class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:postTransferenciaCreditos")		
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE CREDITOS
			
			from("direct:postTransferenciaCreditos")
			    .log("Body:: ${body.pidm}")
				.to("velocity:template/creditos/query_transferir_creditos.vm")
				 .log("Body:: ${body}")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:homologacionesSQL") 
				.bean(CreditosService.class, "postTransferenciaCreditos")
		        .wireTap("mock:outputPostTransferenciaCreditos")
			;			
	}
}
