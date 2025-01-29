package co.edu.uniandes.fuse.api.academico.beans.cursos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import co.edu.uniandes.fuse.api.academico.models.cursos.Cursos;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class CursosService {
	
	public void getCursos(Exchange exchange) throws Exception {
		
		
		String codigo = exchange.getIn().getHeader("codigo", String.class);
		String codCurso = exchange.getIn().getHeader("curso", String.class);
		String nombre = exchange.getIn().getHeader("nombre", String.class);
		
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		List<Cursos> cursos = new ArrayList<>();
		if (!result.isEmpty()) {

			for (int x = 0; x < result.size(); x++) {

				Map<String, Object> row = result.get(x);
				
				Cursos curso = new Cursos();
				curso.setCodigo(DataTypes.getColumnString(row.get("CODIGO")));
				curso.setNombre(DataTypes.getColumnString(row.get("NOMBRE")));
				curso.setCurso(DataTypes.getColumnString(row.get("CURSO")));
				curso.setCreditos(DataTypes.getColumnString(row.get("CREDITOS")));
				curso.setNumero(DataTypes.getColumnString(row.get("NUMERO")));
				curso.setNivel(DataTypes.getColumnString(row.get("NIVEL")));
				cursos.add(curso);
			
			}
			exchange.getIn().setBody(cursos);			

		} else {
			exchange.setProperty("HttpErrorProperty", "http.code.not.found");
			exchange.setProperty("InternalErrorProperty", "internal.code.resource.not.found");
			throw new Exception("Recurso no encontrado");
		}
		
		
		
	}
}
