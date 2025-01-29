package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.util.List;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseNivelAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class NivelAcademicoProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		
		ResponseNivelAcademico nivelAcademico = new ResponseNivelAcademico();
		List<Map<String, Object>> result = ex.getIn().getBody(List.class);
		String nivel = "";
		
		if ((result != null) && (!result.isEmpty())) {
			
			Map sql = (Map) result.get(0);
			nivel = DataTypes.getColumnString(sql.get("NIVELACADEMICO"));
			
			nivelAcademico.setNivelAcademico(nivel);
			ex.getIn().setBody(nivelAcademico, ResponseNivelAcademico.class);
			
		}else {
			
			ex.setProperty("HttpErrorProperty", "http.code.not.found");
			ex.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		

	}

}
