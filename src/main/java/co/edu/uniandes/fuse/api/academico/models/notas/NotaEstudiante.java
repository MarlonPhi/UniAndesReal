package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Nota;

public class NotaEstudiante {
	
	@JsonIgnoreProperties(value= {"sPeriodo","iCupoMax","iInscritos","sAtributo","sAtributoDescripcion"})
	@JsonProperty("CursoSeccion")
	private CursoSeccion cursoSeccion;

	@JsonIgnoreProperties(value= {"SCRN","SNombreCurso","SCodigoCurso","LNotasParciales"})
	@JsonProperty("Nota")
	private Nota nota;

	public NotaEstudiante(CursoSeccion cursoSeccion, Nota nota) {
		this.cursoSeccion = cursoSeccion;
		this.nota = nota;
	}

	public NotaEstudiante() {
	}

	
	public CursoSeccion getCursoSeccion() {
		return cursoSeccion;
	}

	public void setCursoSeccion(CursoSeccion cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	
	
	

}
