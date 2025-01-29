package co.edu.uniandes.fuse.api.academico.processors;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.entity.Programa;
import co.edu.uniandes.fuse.api.academico.models.estudiante.InfoProgramasEstudiante;

public class ProgramasEstudianteProcessor  implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		List<InfoProgramasEstudiante>  programasEstudiante = (List<InfoProgramasEstudiante>) exchange.getProperty("programasEstudiante");
		Programa  programa = exchange.getIn().getBody(Programa.class);
		Integer prioridad = exchange.getIn().getHeader("prioridad", Integer.class);
		boolean principal = (prioridad == 1) ? true : false;
		
		InfoProgramasEstudiante  programaEstudiante = new InfoProgramasEstudiante();
		programaEstudiante.setCodigoDepartamento(programa.getCodigoDepartamento());
		programaEstudiante.setCodigoFacultad(programa.getCodigoFacultad());
		programaEstudiante.setCodigoPrograma(programa.getCodigoPrograma());
		programaEstudiante.setDepartamento(programa.getDepartamento());
		programaEstudiante.setFacultad(programa.getFacultad());
		programaEstudiante.setNivel(programa.getNivel());
		programaEstudiante.setPrograma(programa.getPrograma());
		programaEstudiante.setPrincipal(principal);
		programasEstudiante.add(programaEstudiante);
		
		exchange.setProperty("programasEstudiante", programasEstudiante);
		
		
	}
	
}
