package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateErrorProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {

		String status = exchange.getIn().getHeader("CamelHttpResponseCode", String.class);
		String resText = exchange.getIn().getHeader("CamelHttpResponseText", String.class);
		String msg = "";
		
		switch (status) {
		case "400":
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			msg = "No es posible procesar la peticion (Bad Request)";
			break;
		case "404":
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			
			msg = (resText != null )? resText:"No existe registros asociados a la consulta";
			break;
		case "401":
			exchange.setProperty("HttpErrorProperty", "http.code.unauthorized");
			msg = resText;
			break;
		default:
			exchange.setProperty("HttpErrorProperty", "http.code.internal.server.error");
			msg = "Error al realizar la consulta";
			break;
		}
		
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, status);
		throw new Exception(msg);
		
	}

}
