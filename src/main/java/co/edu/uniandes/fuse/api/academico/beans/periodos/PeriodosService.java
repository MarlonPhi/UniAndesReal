package co.edu.uniandes.fuse.api.academico.beans.periodos;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.entity.Periodo;
import co.edu.uniandes.fuse.api.academico.models.periodo.PeriodoAcademicoAnio;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class PeriodosService {
	
	
	public final static Logger log = Logger.getLogger("periodos");
	
	/**
	 * Method that get periods of banner and set in java poojo for rest response.
	 * @param exchange -exchange of get query with periods info filtered by dates and type period. And Set info on body exchange. 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void periodWithDate(Exchange exchange) throws Exception {
		
		
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		List<Periodo> listaInfo = new LinkedList<Periodo>();
		if (!resultSet.isEmpty()) {
			
			for (Map<String, Object> map : resultSet) {
				Periodo p = new Periodo();
				p.setdFechaFin((DataTypes.getColumnString(map.get("FECHA_FIN"))));
				p.setdFechaInicio((DataTypes.getColumnString(map.get("FECHA_INICIO"))));
				p.setsAnioPeiodo(DataTypes.getColumnString(map.get("ANNO_PERIODO")));
				p.setsDescripcionPeriodo(DataTypes.getColumnString(map.get("DESC_PERIODO")));
				p.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO_LLAVE")));
				p.setsTipoPeiodo(DataTypes.getColumnString(map.get("TIPO_PERIODO")));
				
				log.info("set de tipo periodo: "+ p.getsTipoPeiodo());
				
				listaInfo.add(p);
			}
			exchange.getIn().setBody(listaInfo);
		}else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
	
	/**
	 * Method that get periods of banner and set in java poojo for rest response.
	 * @param ex - exchange of get query with periods info filtered by year. And Set info on body exchange. 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void allPeriods(Exchange ex) throws Exception {
		
		List<Map<String, Object>> periods = (List<Map<String, Object>>) ex.getIn().getBody();
		List<PeriodoAcademicoAnio> listaInfo = new LinkedList<PeriodoAcademicoAnio>();
		String desc = null;
		
		if (!periods.isEmpty()) {
			for (Map<String, Object> map : periods) {
				PeriodoAcademicoAnio per = new PeriodoAcademicoAnio();
				per.setdFechaInicio(DataTypes.getColumnString(map.get("FEC_INICIO")));
				per.setdFechaFin(DataTypes.getColumnString(map.get("FEC_FIN")));
				desc = DataTypes.getColumnString(map.get("DESC_SEMESTRE"));
				String finalDescription[] = desc.split(" ");
				String salida,concat = "";
				for (int i = 0; i < finalDescription.length; i++) {
					salida = finalDescription[i];
					if (!salida.matches(".*[0-9].*")) { 
						if (concat == "") concat+=salida; 
						else concat+=" "+salida;
					}
				}
				per.setsDescripcionPeriodo(concat);
				per.setsPeriodo(DataTypes.getColumnString(map.get("PERIODO")));
				listaInfo.add(per);
			}
			ex.getIn().setBody(listaInfo);
		}else {
			ex.setProperty("HttpErrorProperty", "http.code.not.found");
			ex.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
	}
}
