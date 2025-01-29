package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Nota;
import co.edu.uniandes.fuse.api.academico.models.entity.Profesor;
import co.edu.uniandes.model.Curso;

public class NotaHistorico {
	
	  @JsonIgnoreProperties(value= {"sPeriodo","iCupoMax","iInscritos","sAtributo","sAtributoDescripcion"})
	  @JsonProperty("CursoSeccion")
	  private CursoSeccion cursoSeccion;
	  
	  @JsonIgnoreProperties(value= {"sNombreCurso","sCodigoCurso","sFacultad","sDescFacultad","sDepartamento","sDescDepartamento","sDescMateria"})
	  @JsonProperty("Curso")
	  private Curso curso;
	  
	  @JsonIgnoreProperties(value= {"SCRN","SNombreCurso","SCodigoCurso","LNotasParciales"})
	  @JsonProperty("Nota")
	  private Nota nota;
	  
	  @JsonIgnoreProperties(value= {"sPidm"})
	  @JsonProperty("Profesor")
	  private Profesor profesor;

	public NotaHistorico(CursoSeccion cursoSeccion, Curso curso, Nota nota, Profesor profesor) {
		this.cursoSeccion = cursoSeccion;
		this.curso = curso;
		this.nota = nota;
		this.profesor = profesor;
	}

	public NotaHistorico() {
	}

	public CursoSeccion getCursoSeccion() {
		return cursoSeccion;
	}

	public void setCursoSeccion(CursoSeccion cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
	
	  
	  

}
