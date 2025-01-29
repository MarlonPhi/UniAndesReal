package co.edu.uniandes.fuse.api.academico.processors;

import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HomologacionesPogramasprocessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		
		String nameProg = exchange.getIn().getHeader("programName", String.class);
		
		nameProg = java.net.URLDecoder.decode(nameProg, StandardCharsets.UTF_8.name());
		
		exchange.getIn().setHeader("programName", nameProg);
		
	}

}
