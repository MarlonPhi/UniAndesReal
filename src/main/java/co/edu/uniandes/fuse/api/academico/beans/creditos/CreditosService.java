package co.edu.uniandes.fuse.api.academico.beans.creditos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaResponse;
import co.edu.uniandes.fuse.api.academico.models.egresado.Titulos;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class CreditosService {

	
	
	public void postTransferenciaCreditos(Exchange exchange) throws Exception {
	
		
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		if (!result.isEmpty()) {
			Map<String, Object> row = result.get(0);
			TransferenciaResponse transerencia = new TransferenciaResponse();
			transerencia.setCodigo((DataTypes.getColumnString(row.get("CODIGO"))));	
			exchange.getIn().setBody(transerencia);
		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
}
