package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.utils.ResponseCodeMessage;


public class ValidateResponseCode implements Processor{
	
	ResponseCodeMessage response = new ResponseCodeMessage();

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String code = exchange.getIn().getHeader("CamelHttpResponseCode", String.class);
		
		
		switch (code) {
		case "204":
			response.setMessage("No se encontro ningún registro asociado a la consulta.");
			break;
		default:
			response.setMessage("No se encontro información.");
			
		}
		
		exchange.getIn().setBody(response);
		
	}

}
