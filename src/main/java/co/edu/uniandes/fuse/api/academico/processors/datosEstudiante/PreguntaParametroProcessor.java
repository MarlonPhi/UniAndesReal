package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;
import org.codehaus.jackson.map.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.PreguntaParametro;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class PreguntaParametroProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		PreguntaParametro response = new PreguntaParametro();
		List<Map<String, Object>> bodyPregunta = (List<Map<String, Object>>) exchange.getIn().getBody(); 
		String questionType = "", answerout = "",codein = "", tablename = "";
		
		if(!bodyPregunta.isEmpty()) {
			if (UtilsAcademico.getColumnString(bodyPregunta.get(0).get("questiontype")).equals("P")) {
				response.setIsPreguntaParametrica(true);
				response.setsAnswerOut(UtilsAcademico.getColumnString(bodyPregunta.get(0).get("answerout")));
				response.setsCodeIn(UtilsAcademico.getColumnString(bodyPregunta.get(0).get("codein")));
				response.setsTableName(UtilsAcademico.getColumnString(bodyPregunta.get(0).get("tablename")));
			}else {
				response.setIsPreguntaParametrica(false);
			}
		}else {
			new ThrowExceptionDefinition();
		}
		
		questionType = String.valueOf(response.getIsPreguntaParametrica());
		answerout = response.getsAnswerOut();
		codein = response.getsCodeIn();
		tablename = response.getsTableName();
		
		String resp = new ObjectMapper().writeValueAsString(response);
		exchange.setProperty("responseAudit", resp);
		exchange.setProperty("SquestionType", questionType);
		exchange.setProperty("SanswerOut", answerout);
		exchange.setProperty("ScodeIn", codein);
		exchange.setProperty("StableName", tablename);
		exchange.getIn().setBody(response);
		
	}	
}
