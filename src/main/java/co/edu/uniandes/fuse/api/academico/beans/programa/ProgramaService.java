package co.edu.uniandes.fuse.api.academico.beans.programa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.entity.Programa;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class ProgramaService {
	
	private final static Logger log = Logger.getLogger("");

	ResponseCode rCode = new ResponseCode();
	/**
	 * Estructura Programa
	 */
	public void getProgramaEstructura(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getProperty("pEstructura", ArrayList.class);
		String desNivel = exchange.getProperty("desNivel", String.class);
		if (!result.isEmpty()) {
			Map<String, Object> row = result.get(0);
			Programa programa = new Programa();
			
			programa.setDescripcionNivel(desNivel);
			programa.setCodigoDepartamento(DataTypes.getColumnString(row.get("DPTO")));
			programa.setCodigoFacultad(DataTypes.getColumnString(row.get("FAC")));
			programa.setCodigoPrograma(DataTypes.getColumnString(row.get("PROG")));
			programa.setDepartamento(DataTypes.getColumnString(row.get("DEPARTAMENTO")));
			programa.setFacultad(DataTypes.getColumnString(row.get("FACULTAD")));
			programa.setPrograma(DataTypes.getColumnString(row.get("PROGRAMA")));
			programa.setNivel(DataTypes.getColumnString(row.get("NIVEL")));
			
			exchange.getIn().setBody(programa);
			
		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
}
