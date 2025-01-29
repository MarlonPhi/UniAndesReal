package co.edu.uniandes.fuse.api.academico.processors;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.entity.Periodo;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class PeriodoProcessor implements Processor{
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<Periodo> listaInfo = new LinkedList<Periodo>();
		Periodo p = new Periodo();
		
		if (!resultSet.isEmpty()) {
			for (Map<String, Object> map : resultSet) {
				
				p.setdFechaFin((DataTypes.getColumnString(map.get("FECHA_FIN"))));
				p.setdFechaInicio((DataTypes.getColumnString(map.get("FECHA_INICIO"))));
				p.setsAnioPeiodo(UtilsAcademico.getColumnString(map.get("ANNO_PERIODO")));
				p.setsDescripcionPeriodo(UtilsAcademico.getColumnString(map.get("DESC_PERIODO")));
				p.setsPeriodo(UtilsAcademico.getColumnString(map.get("PERIODO_LLAVE")));
				p.setsTipoPeiodo(UtilsAcademico.getColumnString(map.get("TIPO_PERIODO")));
				
				listaInfo.add(p);
			}
			exchange.getIn().setBody(listaInfo);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}

}
