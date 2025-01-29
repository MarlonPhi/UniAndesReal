package co.edu.uniandes.fuse.api.academico.processors.dependencia;

import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParametersProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String depNombre = java.net.URLDecoder.decode(exchange.getIn().getHeader("sdependencianombre", String.class), StandardCharsets.UTF_8.name());
		String depId = exchange.getIn().getHeader("sdependenciaid", String.class);
		String depExtId = exchange.getIn().getHeader("sdependenciaexternalid", String.class);
		
		exchange.setProperty("sdepName", depNombre);
		exchange.setProperty("sdepId", depId);
		exchange.setProperty("sdepExtId", depExtId);
		
		
	}

}
