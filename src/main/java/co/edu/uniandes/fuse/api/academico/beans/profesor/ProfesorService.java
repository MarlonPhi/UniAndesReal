package co.edu.uniandes.fuse.api.academico.beans.profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.profesor.ProfesorSecciones;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class ProfesorService {
	
	/**
	 * SECCIONES PROFESOR
	 * 
	 * Lee del body el resultado de la consulta a la base de datos, crea una lista
	 * de Correos y mapea la direcci�n de correo y el tipo de correo
	 */
	public void getProfesorSecciones(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<ProfesorSecciones> listaSecciones = new ArrayList<>();
		if (!result.isEmpty()) {

			for (int x = 0; x < result.size(); x++) {

				Map<String, Object> row = result.get(x);

				ProfesorSecciones seccion = new ProfesorSecciones();
				boolean profPrincipal = (DataTypes.getColumnEmptyString(row.get("IND_PROF_PRINC")).equals("Y")) ? true : false; 
				seccion.setProfesorPrincipal(profPrincipal);
				seccion.setDedicacionSeccion(DataTypes.getColumnString(row.get("PORCENTAJE_SESSION")));
				seccion.setResponsabilidadSeccion(DataTypes.getColumnString(row.get("PORCENTAJE_RESPONSABILIDA")));
				seccion.setCrn(DataTypes.getColumnString(row.get("CRN")));

				listaSecciones.add(seccion);
			
			}
			exchange.getIn().setBody(listaSecciones);			

		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
}
