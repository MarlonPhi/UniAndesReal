package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Inscripcion;


public class SeccionesInfo {

	@JsonIgnoreProperties(value= {"sPeriodo","iCupoMax","iInscritos","sAtributo","sAtributoDescripcion"})
	@JsonProperty("CursoSeccion")
	private CursoSeccion cursoSeccion;

	@JsonProperty("NotaParcial")
	private String notaParcial;

	@JsonIgnoreProperties(value= {"sPidm","sCRN","iInscritos","sAtributo","sAtributoDescripcion"})
	@JsonProperty("Inscripcion")
	private Inscripcion inscripcion;

	public SeccionesInfo() {
	}

	public SeccionesInfo(CursoSeccion cursoSeccion, String notaParcial, Inscripcion inscripcion) {

		this.cursoSeccion = cursoSeccion;
		this.notaParcial = notaParcial;
		this.inscripcion = inscripcion;
	}

	public CursoSeccion getCursoSeccion() {
		return cursoSeccion;
	}

	public void setCursoSeccion(CursoSeccion cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}

	public String getNotaParcial() {
		return notaParcial;
	}

	public void setNotaParcial(String notaParcial) {
		this.notaParcial = notaParcial;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

}
