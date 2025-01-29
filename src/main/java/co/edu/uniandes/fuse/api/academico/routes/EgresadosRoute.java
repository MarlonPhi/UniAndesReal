package co.edu.uniandes.fuse.api.academico.routes;


import org.apache.camel.model.rest.RestParamType;

import co.edu.uniandes.fuse.api.academico.beans.egresado.EgresadoService;
import co.edu.uniandes.fuse.api.academico.models.egresado.Titulos;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

/**
 * Definici�n de componentes rest/swagger y definici�n de rutas para el recurso sedes
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class EgresadosRoute extends RestConfiguration {
	
	public void configure() throws Exception {
		
		// EXCEPTIONS       
        onException(Exception.class)
             .handled(true)
             .to("direct-vm:manageException");
        ;
		
		// REST & SWAGGER COMPONENTS	
		rest("/egresados").description("Consulta de informaci&oacute;n de egresados")		
			// programas		
			.get("/{id}/titulos")
				.description("Consulta los titulos de un egresado por Id")				
				.consumes("application/json").produces("application/json")
					.param()
						.name("id").description("C&oacute;digo/Login del egresado").defaultValue("cafanador")
					.endParam()
					.param()
					  	.name("periodo").description("Periodo (Ej: 201620)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				.outType(Titulos[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct-vm:getTitulosEgresado")		
		;

		// REST & SWAGGER COMPONENTS				
			// ROUTE PAISES
			
			from("direct-vm:getTitulosEgresado")
				.to("direct-vm:validaPIDM")
				.to("velocity:template/egresados/query_obtener_titulos.vm")
				.setHeader("CamelSqlQuery").simple("${body}")
				.to("direct:bannerSQL")
				.bean(EgresadoService.class, "getTitulosEgresado")
			;			
			
	}
}
