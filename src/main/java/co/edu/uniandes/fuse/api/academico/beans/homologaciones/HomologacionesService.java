package co.edu.uniandes.fuse.api.academico.beans.homologaciones;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.programa.ProgramaHomologaciones;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;


public class HomologacionesService {

	
	public void getProgramasH(Exchange exchange) throws Exception{
		
		String nombrePrograma = exchange.getIn().getHeader("programName", String.class);
		
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		
		List<ProgramaHomologaciones> programaH = new ArrayList<>();
		
		if(!result.isEmpty()) {
			
			for (int i = 0; i < result.size(); i++) {
				
				Map<String, Object> row = result.get(i);
				
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
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
			}
		
	}
}
