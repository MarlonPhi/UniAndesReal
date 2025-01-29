package co.edu.uniandes.fuse.api.academico.models.cursos;

import java.util.List;

public class ListResInfoCursosv2 {

	private List<ResponseInfoCursos> infoCursos;
	
	
	

	public ListResInfoCursosv2(List<ResponseInfoCursos> infoCursos) {
		this.infoCursos = infoCursos;
	}
	
	

	public ListResInfoCursosv2() {
	}



	public List<ResponseInfoCursos> getInfoCursos() {
		return infoCursos;
	}

	public void setInfoCursos(List<ResponseInfoCursos> infoCursos) {
		this.infoCursos = infoCursos;
	}
	
	
}
