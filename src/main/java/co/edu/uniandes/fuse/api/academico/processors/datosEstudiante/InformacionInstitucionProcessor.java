package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ThrowExceptionDefinition;

import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.InformacionInstitucion;
import co.edu.uniandes.fuse.api.academico.models.datosEstudiante.SalidaInformacionInstitucion;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.model.EstructuraTerritorial;
import co.edu.uniandes.model.InstitucionEducativa;

public class InformacionInstitucionProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		InformacionInstitucion response = new InformacionInstitucion();
		ArrayList<SalidaInformacionInstitucion>listInfo = new ArrayList<SalidaInformacionInstitucion>();
		List<Map<String, Object>> resultSet = (List<Map<String, Object>>) exchange.getIn().getBody();
		
		
		if (!resultSet.isEmpty()) {

		    for (Map<String, Object> row : resultSet) {		
				SalidaInformacionInstitucion salida=new SalidaInformacionInstitucion();
				EstructuraTerritorial estructura=new EstructuraTerritorial();
				InstitucionEducativa institucion =new InstitucionEducativa();
				
				institucion.setsCodigo(UtilsAcademico.getColumnString(row.get("CODE")));
				institucion.setsNombre(UtilsAcademico.getColumnString(row.get("DESCRIPCION")));
				institucion.setsTipoInstitucion(UtilsAcademico.getColumnString(row.get("IND_COLEGIO_UNIV")));
				estructura.setsCodigoCiudad(UtilsAcademico.getColumnString(row.get("COD_CIUDAD")));
				estructura.setsCodigoDpto(UtilsAcademico.getColumnString(row.get("COD_DPTO")));
				estructura.setsCodigoPais(UtilsAcademico.getColumnString(row.get("COD_PAIS")));
				estructura.setsCodigoPostal(UtilsAcademico.getColumnString(row.get("COD_POSTAL")));
				estructura.setsCodigoDane(UtilsAcademico.getColumnString(row.get("COD_DANE")));
				salida.setEstructuraTerritorial(estructura);
				salida.setInstitucionEducativa(institucion);
				listInfo.add(salida);
			}
		    
		} else {
			
			new ThrowExceptionDefinition();
		}
		
		response.setSalida(listInfo);
		exchange.getIn().setBody(response);
	}

}
