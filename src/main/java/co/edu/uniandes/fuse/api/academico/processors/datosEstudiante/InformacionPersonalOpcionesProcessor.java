package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.OpcionEstudiante;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.informacionPersonalOpciones;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class InformacionPersonalOpcionesProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		informacionPersonalOpciones response = new informacionPersonalOpciones();
		List<OpcionEstudiante> opciones = new ArrayList<>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();

		
		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> map : resultSet) {
			OpcionEstudiante opcion = new OpcionEstudiante();
			opcion.setsNombreOpcion(UtilsAcademico.getColumnString(map.get("DESCRI_OPCION")));
			opcion.setsCodigoOpcion(UtilsAcademico.getColumnString(map.get("CODIGO_OPCION")));
			opcion.setsPeriodo(UtilsAcademico.getColumnString(map.get("PERIODO")));
			opcion.setsPrioridad(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("PRIORIDAD")));
			opciones.add(opcion);
		    }
		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		response.setOpciones(opciones);
		exchange.getIn().setBody(response);
	}

}
