package co.edu.uniandes.fuse.api.academico.beans.profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.profesor.ProfesorSecciones;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class ProfesorService {
	ResponseCode rCode = new ResponseCode();
	private final static Logger log = Logger.getLogger("");
	/**
	 * SECCIONES PROFESOR
	 * 
	 * Lee del body el resultado de la consulta a la base de datos, crea una lista
	 * de Correos y mapea la dirección de correo y el tipo de correo
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
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void profesorAcademia (Exchange exchange) throws Exception {
		
		String response = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response , new TypeReference<Map<String, Object>>() {});
		if (map.containsKey("status")) {
			String status = DataTypes.getColumnString(map.get("status"));
			if (status.contains("40")) {
				exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, status);
				exchange.getIn().setBody(map);
				exchange.setProperty("responseAudit", map);
			}
		}else {
			exchange.getOut().setBody(map);
			exchange.setProperty("responseAudit", map);
		}
		
		
	}
	
	public void profesorProducto (Exchange exchange) throws Exception {
		String response = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(response , new TypeReference<Map<String, Object>>() {});
		exchange.getOut().setBody(map);
		exchange.setProperty("responseAudit", map);
	}
}
