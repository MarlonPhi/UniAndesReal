package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidateParamsNotasProcessor implements Processor{

	@Override
	public void process(Exchange ex) throws Exception {
		// TODO Auto-generated method stub
		
		String login = ex.getIn().getHeader("login", String.class);
		String periodo = ex.getIn().getHeader("periodo", String.class);
		
		if((login.trim().equals("")| login == null ) && (periodo.trim().equals("") || periodo == null)) {
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos. Login y periodo");
		}
		
		if(login.trim().equals("")| login == null){
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos. -login-");
		}
		
		if(periodo.trim().equals("") || periodo == null){
			ex.setProperty("HttpErrorProperty", "http.code.bad.request");
			throw new IllegalArgumentException("Son necesarios los campos requeridos. -periodo-");
		}
		
		if(!login.isEmpty() && !periodo.isEmpty()) {
			ex.setProperty("login", login);
			ex.setProperty("periodo", periodo);	
			ex.setProperty("ContinueProcedure", true);
		}
	}

}
