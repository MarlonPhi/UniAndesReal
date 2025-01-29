package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ValidateParamsPhdProcessor implements Processor {
	
	private final static Logger log = Logger.getLogger("");

	@Override
	public void process(Exchange exchange) throws Exception {
		
		
		String status = exchange.getProperty("status", String.class);
		String status2 = exchange.getProperty("status2", String.class);
		String codeStudent = exchange.getProperty("codeStudent", String.class);
		String username = exchange.getProperty("username", String.class);
		String faculty = exchange.getProperty("faculty", String.class);
		String programId = exchange.getProperty("programId", String.class);
		String uriValidate = exchange.getIn().getHeader("CamelHttpUri", String.class);
		String[] parts = uriValidate.split("/");
		String pathSelect = parts[4];
		
		log.info("ENTRANDO A PROCESADOR DE VALIDACION DE PARAMETROS");
		log.info("verificando pathSlect -> :"+ pathSelect);
		log.info("verificando parametros :"+" - codeStudent: "+ codeStudent);
		
		if (pathSelect.equals("codeStudent")) {
				if (codeStudent.isEmpty() || codeStudent == null) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
		}else if (pathSelect.equals("username")) {
			
				if (username.isEmpty() || username == null) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
				
		}else if (pathSelect.equals("userAndCode")) {
				
				if ((codeStudent.trim().equals("") || codeStudent == null) && (username.trim().equals("") || username == null)) {
					
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Son necesarios los campos requeridos.");
					
				}else if ((!codeStudent.trim().equals("") || codeStudent != null) && (username.trim().equals("") || username == null)) {
	
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Es necesario otro campo para realizar la petición.");
					
				}else if((codeStudent.trim().equals("") || codeStudent == null) && (!username.trim().equals("") || username != null)){
					
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Es necesario otro campo para realizar la consulta.");
				}
		}else if (pathSelect.equals("faculty")) {
			
				if (faculty.isEmpty() || faculty == null) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
		}else if (pathSelect.equals("program")) {
			
				if (programId.isEmpty() || programId == null) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
		}else if (pathSelect.equals("rangeStatus")) {
			
				if ((status.isEmpty() || status == null) && (status2.isEmpty() || status2 == null)) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
		}else if (pathSelect.equals("status")) {
			
				if (status.isEmpty() || status == null) {
					exchange.setProperty("HttpErrorProperty", "http.code.bad.request");
					throw new IllegalArgumentException("Se necesita el parametro correspondiente para realizar la petición.");
				}
		}
		

	}
	

}
