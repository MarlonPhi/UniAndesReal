package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class ErrorHandler implements Processor{

	
	@Override
	public void process (Exchange exchange) throws Exception {
		
		String msgFinal = exchange.getProperty("message", String.class);
		String httpError = exchange.getIn().getHeader("HttpErrorCode", String.class);
		exchange.setProperty("HttpErrorProperty",httpError );
		
		throw new Exception(msgFinal);
		
	}
}
