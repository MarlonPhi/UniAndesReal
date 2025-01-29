package co.edu.uniandes.fuse.api.academico.processors.suspensiones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.suspension.ResponseSuspensionesAcademicas;
import co.edu.uniandes.fuse.api.academico.models.suspension.Suspension;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class SuspensionesAcademicasProcessor implements Processor {

	ResponseSuspensionesAcademicas response = new ResponseSuspensionesAcademicas();

	@Override
	public void process(Exchange exchange) throws Exception {
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		
		List<Suspension> listSus = new ArrayList<>();
		
		if (!result.isEmpty()) {
			
			for (Map map : result) {
				
				Suspension suspension = new Suspension();
				
				suspension.setsCodigoSuspencion(DataTypes.getColumnString(map.get("ASTD_CODE")));
				suspension.setsTipoSuspension("ACAD");
				suspension.setsPeriodo(DataTypes.getColumnString(map.get("TERM_CODE")));
				
				
				listSus.add(suspension);
				
			}
			response.setSuspension(listSus);
			exchange.getIn().setBody(response);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		
	}

}
