package co.edu.uniandes.fuse.api.academico.beans.programa;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.programa.Programa;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class ProgramaService {

	/**
	 * Estructura Programa
	 */
	public void getProgramaEstructura(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		if (!result.isEmpty()) {
			Map<String, Object> row = result.get(0);

			Programa programaEstructura = new Programa();
			
			programaEstructura.setCodigoDepartamento(DataTypes.getColumnString(row.get("DPTO")));
			programaEstructura.setCodigoFacultad(DataTypes.getColumnString(row.get("FAC")));
			programaEstructura.setCodigoPrograma(DataTypes.getColumnString(row.get("PROG")));
			programaEstructura.setDepartamento(DataTypes.getColumnString(row.get("DEPARTAMENTO")));
			programaEstructura.setFacultad(DataTypes.getColumnString(row.get("FACULTAD")));
			programaEstructura.setPrograma(DataTypes.getColumnString(row.get("PROGRAMA")));
			programaEstructura.setNivel(DataTypes.getColumnString(row.get("NIVEL")));		
			exchange.getIn().setBody(programaEstructura);
		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
}
