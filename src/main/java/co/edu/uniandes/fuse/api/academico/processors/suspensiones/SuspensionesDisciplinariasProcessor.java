package co.edu.uniandes.fuse.api.academico.processors.suspensiones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.suspension.ResponseSuspensionesDisciplinarias;
import co.edu.uniandes.fuse.api.academico.models.suspension.Suspension;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class SuspensionesDisciplinariasProcessor implements Processor{

	ResponseSuspensionesDisciplinarias response = new ResponseSuspensionesDisciplinarias();

	@Override
	public void process(Exchange exchange) throws Exception {
		
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		List<Suspension> listSus = new ArrayList<>();
	
		if (result != null && !result.isEmpty()) {
			for (Map<String, Object> map : result) {
				Suspension suspension = new Suspension();
				
				suspension.setdSuspenionDiscFin((DataTypes.getColumnString(map.get("FECHA_FIN"))));
				suspension.setdSuspensionDiscInicio((DataTypes.getColumnString(map.get("FECHA_INICIO"))));
				suspension.setsDescipcionSuspDisc(DataTypes.getColumnString(map.get("descripcion_suspencion")));
				suspension.setsRazonSuspDisc(DataTypes.getColumnString(map.get("razon")));
				suspension.setsTipoSuspension("DISCI");
				
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
