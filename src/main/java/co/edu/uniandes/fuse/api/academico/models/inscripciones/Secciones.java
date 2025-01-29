package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;

public class Secciones {
	
	@JsonIgnoreProperties(value={"iCupoMax","iInscritos","sCreditos","sAtributo","sAtributoDescripcion"})
	@JsonProperty("CursoSeccion")
	private List<CursoSeccion> cursoSeccion;
	

	public Secciones() {
	}


	public Secciones(List<CursoSeccion> cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}


	public List<CursoSeccion> getCursoSeccion() {
		return cursoSeccion;
	}


	public void setCursoSeccion(List<CursoSeccion> cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}


	
	

}
