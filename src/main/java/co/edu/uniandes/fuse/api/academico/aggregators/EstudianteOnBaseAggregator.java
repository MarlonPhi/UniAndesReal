package co.edu.uniandes.fuse.api.academico.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.log4j.Logger;

public class EstudianteOnBaseAggregator implements AggregationStrategy {
	
	private final static Logger log = Logger.getLogger("");

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		List<String> arraystrings;
		//String flag;
		
		if(oldExchange != null) {			
			arraystrings = oldExchange.getProperty("listaDocumentsProcessed", List.class);
			log.info("verificando lista valores de documento en agregator:::"+ arraystrings);
		}else {
			arraystrings = new ArrayList<>();
			//flag = "";
			log.info("GENERANDO EXCHANGE USER AGREGGATOR");
		}
		
		String valor = newExchange.getProperty("nro_documento_user", String.class);
		String flag = newExchange.getProperty("flagVerifyDocumentReadyToProcess", String.class);
		//String valor = newExchange.getIn().getBody(String.class);
		log.warn("verificando valor en AGREGATOR::: "+ valor);
		log.warn("verificando el property de FLAG IN AGGREGATOR newExchange:" + flag);
		
		if (valor != null && !valor.isEmpty() && flag.equals("CORRECTO") && flag != null) {
			arraystrings.add(valor);
        }
		newExchange.setProperty("listaDocumentsProcessed", arraystrings);
		return newExchange;
	}

}
