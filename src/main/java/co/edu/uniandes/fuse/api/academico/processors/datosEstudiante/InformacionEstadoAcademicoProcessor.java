package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionEstadoAcademico;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.model.EstadoAcademico;

public class InformacionEstadoAcademicoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		InformacionEstadoAcademico response = new InformacionEstadoAcademico();
		List<EstadoAcademico> listResponse = new ArrayList<EstadoAcademico>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();

		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> dir : resultSet) {
			   
			   //Salida salida = new Salida();
			    EstadoAcademico estadoAcademico = new EstadoAcademico();
			 

			    estadoAcademico.setsPeriodo(UtilsAcademico.getColumnBigDecimalToString(dir.get("SEMESTRE")));
			    estadoAcademico.setsNivel(UtilsAcademico.getColumnString(dir.get("NIVEL_HA")));
			    estadoAcademico.setsPidm(UtilsAcademico.getColumnBigDecimalToString(dir.get("LLAVE_ESTUDIANTE")));
			    estadoAcademico.setsDescripcionEstado(UtilsAcademico.getColumnString(dir.get("DESC_ESTADO_ACADMICO")));
			    estadoAcademico.setsCodigoEstado(UtilsAcademico.getColumnBigDecimalToString(dir.get("CODIGO_ESTADO_ACADEMICO")));
			    estadoAcademico.setsCodigoEstadoDoble(UtilsAcademico.getColumnString(dir.get("CODIGO_ESTADO_ACADEMICO_DOBLE")));

			    listResponse.add(estadoAcademico);
			}
		    
		    response.setEstadoAcademico(listResponse);
		    exchange.getIn().setBody(response);
		   
		    
		} else {
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}

}
