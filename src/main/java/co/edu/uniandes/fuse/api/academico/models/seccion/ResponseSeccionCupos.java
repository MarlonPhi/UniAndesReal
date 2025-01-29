package co.edu.uniandes.fuse.api.academico.models.seccion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSeccionCupos {
	
	@JsonProperty(value="CursoSeccion")
	private SeccionCupos cursoSeccion;
	
	

	public ResponseSeccionCupos() {
	}

	public ResponseSeccionCupos(SeccionCupos cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}

	public SeccionCupos getCursoSeccion() {
		return cursoSeccion;
	}

	public void setCursoSeccion(SeccionCupos cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}
	
	

}
