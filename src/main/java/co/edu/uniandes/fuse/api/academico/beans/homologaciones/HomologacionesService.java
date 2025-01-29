package co.edu.uniandes.fuse.api.academico.beans.homologaciones;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.programa.ProgramaHomologaciones;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;


public class HomologacionesService {

	ResponseCode rCode = new ResponseCode();
	
	public void getProgramasH(Exchange exchange) throws Exception{

		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		List<ProgramaHomologaciones> programaH = new ArrayList<>();
		
		if(!result.isEmpty() && result != null) {
			for (Map<String, Object> row: result) {
				ProgramaHomologaciones listaPrograma = new ProgramaHomologaciones();
				
				listaPrograma.setFacultyCode(DataTypes.getColumnString(row.get("FAC")));
				listaPrograma.setFacultyName(DataTypes.getColumnString(row.get("FACULTAD")));
				listaPrograma.setProgramCode(DataTypes.getColumnString(row.get("PROG")));
				listaPrograma.setProgramName(DataTypes.getColumnString(row.get("PROGRAMA")));
				listaPrograma.setDepartamentCode(DataTypes.getColumnString(row.get("DPTO")));
				listaPrograma.setDepartamentName(DataTypes.getColumnString(row.get("DEPARTAMENTO")));
				listaPrograma.setProgramLevel(DataTypes.getColumnString(row.get("NIVEL")));
				
				programaH.add(listaPrograma);
			}
			exchange.getIn().setBody(programaH);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
}
