package co.edu.uniandes.fuse.api.academico.routes;

import co.edu.uniandes.fuse.core.utils.beans.Cronometro;


/**
 * Definición de rutas de uso transversal
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class CrossRoute extends RestConfiguration {
	
	public void configure() throws Exception {
	
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
	}
}
