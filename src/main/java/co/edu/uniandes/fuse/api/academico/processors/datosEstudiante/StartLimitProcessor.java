package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class StartLimitProcessor implements Processor{
	
	private final static Logger log = Logger.getLogger("");
	private final static String s = "0",l = "20";
	@Override
	public void process(Exchange exchange) throws Exception {
		String start = exchange.getIn().getHeader("start", String.class);
		String limit = exchange.getIn().getHeader("limit", String.class);
		
		if (!start.isEmpty()  && !limit.isEmpty() ) {
			
			exchange.setProperty("sStart", start);
			exchange.setProperty("sLimit", limit);
		}else {
			exchange.setProperty("sStart", s);
			exchange.setProperty("sLimit", l);
		}
		
	}

}
