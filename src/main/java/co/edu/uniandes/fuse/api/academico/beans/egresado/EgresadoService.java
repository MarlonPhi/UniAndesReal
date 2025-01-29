package co.edu.uniandes.fuse.api.academico.beans.egresado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.egresado.Titulos;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class EgresadoService {

	/**
	 * TITULOS
	 * 
	 * Lee del body el resultado de la consulta a la base de datos, crea una lista
	 * de Correos y mapea la dirección de correo y el tipo de correo
	 */
	public void getTitulosEgresado(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<Titulos> listaTitulos = new ArrayList<>();
		if (!result.isEmpty()) {

			for (int x = 0; x < result.size(); x++) {

				Map<String, Object> row = result.get(x);

				Titulos titulo = new Titulos();
				titulo.setPidm((DataTypes.getColumnString(row.get("PIDM"))));
				titulo.setPeriodo((DataTypes.getColumnString(row.get("PERIODO"))));
				titulo.setNombreTitulo((DataTypes.getColumnString(row.get("NOMBRE_TITULO"))));
				titulo.setFechaObtencion((DataTypes.getColumnString(row.get("FECHA_OBTENCION"))));
				titulo.setNivel((DataTypes.getColumnString(row.get("NIVEL"))));
				listaTitulos.add(titulo);
			
			}
			exchange.getIn().setBody(listaTitulos);			

		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	
}
