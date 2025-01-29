package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionPersonalRetenciones;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.Retencion;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.SalidaRetenciones;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.model.Estudiante;

public class InformacionPersonalRetencionesProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		SalidaRetenciones response = new SalidaRetenciones();
		List<InformacionPersonalRetenciones> listRete = new ArrayList<InformacionPersonalRetenciones>();
		SimpleDateFormat FECHA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		
		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> map : resultSet) {
		    InformacionPersonalRetenciones retenciones = new InformacionPersonalRetenciones();
			Retencion ret = new Retencion();
			Estudiante est = new Estudiante();
			
			est.setsPidm(UtilsAcademico.getColumnBigDecimalToString(UtilsAcademico.getColumnBigDecimal(map.get("SPIDM"))));

			
			
			ret.setsCodigoRetencion(UtilsAcademico.getColumnString(map.get("CODIGO_RETENCION")));
			ret.setsOrigenRetencion(UtilsAcademico.getColumnString(map.get("ORIGEN_RETENCION")));
			ret.setsRazonRetencion(UtilsAcademico.getColumnString(map.get("RAZON")));
			
			ret.setdFechaInicio(UtilsAcademico.getDate(map.get("FECHA_INI_RETENCION")));
			ret.setdFechaFin(UtilsAcademico.getDate(map.get("FECHA_FIN_REENCION")));
			ret.setdFechaCreacionRetencion(UtilsAcademico.getDate(map.get("FECHA_ACTIVIDAD")));
			

			retenciones.setEstudiante(est);
			retenciones.setRetencion(ret);
			listRete.add(retenciones);

		    }
		    
		   response.setRetenciones(listRete);
		   exchange.getIn().setBody(response);
		    
		} else {
			
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}

	}

}
