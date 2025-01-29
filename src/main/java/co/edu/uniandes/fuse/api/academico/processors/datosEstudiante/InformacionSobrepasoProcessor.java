package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionSobrepaso;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.SalidaInformacionSobrepaso;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;

public class InformacionSobrepasoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {


		InformacionSobrepaso response = new InformacionSobrepaso();
		List<SalidaInformacionSobrepaso> salida = new ArrayList<>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();

		
		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> map : resultSet) {
			SalidaInformacionSobrepaso element = new SalidaInformacionSobrepaso();
			element.setsSemestre(UtilsAcademico.getColumnString(map.get("SEMESTRE_SOBREPASO")));
			element.setsPidm(UtilsAcademico.getColumnIntegerFromBigDecimal(map.get("ESTUDIANTE_PIDM")));
			element.setsSobreP(UtilsAcademico.getColumnString(map.get("SOBREP")));
			element.setsTipoAdm(UtilsAcademico.getColumnString(map.get("TIPO_ADMIS")));
			element.setsNivel(UtilsAcademico.getColumnString(map.get("NIVEL")));
			salida.add(element);
		    }
		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		response.setSalida(salida);
		exchange.getIn().setBody(response);
	}

}
