package co.edu.uniandes.fuse.api.academico.processors.Cursos;

import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CursosProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		String nombre = exchange.getIn().getHeader("nombre", String.class);
		nombre = java.net.URLDecoder.decode(nombre, StandardCharsets.UTF_8.name());
		exchange.getIn().setHeader("nombre", nombre);
		
	}
}
