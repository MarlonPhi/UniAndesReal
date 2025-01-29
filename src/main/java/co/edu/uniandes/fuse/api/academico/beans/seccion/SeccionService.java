package co.edu.uniandes.fuse.api.academico.beans.seccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import co.edu.uniandes.fuse.api.academico.models.seccion.Seccion;
import co.edu.uniandes.fuse.api.academico.models.seccion.SeccionEstudiantes;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class SeccionService {
	/**
	 * Seccion
	 */
	public void getSeccion(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<Seccion> listaSecciones = new ArrayList<>();
		if (!result.isEmpty()) {

				Map<String, Object> row = result.get(0);

				Seccion seccion = new Seccion();
				seccion.setMateria(DataTypes.getColumnString(row.get("MATERIA")));
				seccion.setNombreCorto(DataTypes.getColumnString(row.get("NOMBRE_CORTO")));
				seccion.setNombreLargo(DataTypes.getColumnString(row.get("NOMBRE_LARGO")));
				seccion.setNumero(DataTypes.getColumnString(row.get("NUMERO")));
				seccion.setSeccion(DataTypes.getColumnString(row.get("SECCION")));

			exchange.getIn().setBody(seccion);			

		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
	
	
	/**
	 * Estudiantes Seccion
	 */
	public void getSeccionEstudiantes(Exchange exchange) throws Exception {

		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<SeccionEstudiantes> listaSeccionEst = new ArrayList<>();
		String nombres = "";
		String apellidos = "";
		if (!result.isEmpty()) {

			for (int x = 0; x < result.size(); x++) {

				Map<String, Object> row = result.get(x);

				SeccionEstudiantes seccionEst = new SeccionEstudiantes();
				seccionEst.setCodigo(DataTypes.getColumnString(row.get("CODIGO")));
				nombres = (DataTypes.getColumnString(row.get("NOMBRES")));
				apellidos = (DataTypes.getColumnString(row.get("APELLIDOS")));
				seccionEst.setNombre(apellidos + " " + nombres);
				seccionEst.setNombres(nombres);
				seccionEst.setApellidos(apellidos);
				seccionEst.setEmail(DataTypes.getColumnString(row.get("CORREO")));
				seccionEst.setNivel(DataTypes.getColumnString(row.get("NIVEL")));

				listaSeccionEst.add(seccionEst);
			
			}
			exchange.getIn().setBody(listaSeccionEst);			

		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
		}
	}
}

