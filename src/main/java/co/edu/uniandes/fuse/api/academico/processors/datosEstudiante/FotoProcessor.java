package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.FotoEstudiante;
import co.edu.uniandes.model.Persona;

public class FotoProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String picture = exchange.getProperty("PEncode", String.class);
		String newPicture = "";
		FotoEstudiante response = new FotoEstudiante();
		Persona fotoEstudiante = new Persona();
		
		if(picture != null && !picture.equals("")) {
			
			newPicture = picture.replaceAll("[\n\r]", "");
			fotoEstudiante.setsFoto((String) newPicture);
		}else {
			new ThrowExceptionDefinition();
		}
		
		response.setPersona(fotoEstudiante);
		String resp = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", resp);
		exchange.getIn().setBody(response);
	}

}
