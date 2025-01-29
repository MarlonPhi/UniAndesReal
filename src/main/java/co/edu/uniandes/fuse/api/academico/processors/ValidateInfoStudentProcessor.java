package co.edu.uniandes.fuse.api.academico.processors;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateInfoStudentProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String periodo = exchange.getIn().getHeader("speriodo", String.class);
		String numerodocumento = exchange.getIn().getHeader("snumerodocumento", String.class);
		String slogin = exchange.getIn().getHeader("slogin", String.class);
		String scodigo = exchange.getIn().getHeader("scodigo", String.class);
		

		
		if ((scodigo.trim().equals("") || scodigo == null) && (slogin.trim().equals("") || slogin == null) 
				&& (numerodocumento.trim().equals("") || numerodocumento == null)
				&& (periodo == null || periodo.trim().equals(""))) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos.");
		}else if ((scodigo.trim().equals("") || scodigo == null) && (slogin.trim().equals("") || slogin == null) 
				&& (numerodocumento.trim().equals("") || numerodocumento == null)
				&& ((periodo != null) || (!periodo.trim().equals("")))) {
			exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos del Estudiante.");
		}else {
			if ((!scodigo.trim().equals("") || !slogin.trim().equals("") || !numerodocumento.trim().equals("")) 
					&& ((periodo == null) || (periodo.trim().equals(""))) ) {
				exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
				throw new IllegalArgumentException("Es necesarios el campo requerido adicional.");
			}

		}
		
		
		
	}

}
