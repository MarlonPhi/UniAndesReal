package co.edu.uniandes.fuse.api.academico.models.cursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.model.Curso;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;

public class InfoCursos {
	
	@JsonProperty(value = "Curso")
	private Curso curso;
	
	@JsonIgnoreProperties(value= {"sNombreCurso","sCodigoCurso","sSeccion","iCupoMax","iInscritos"
									,"sCreditos","sAtributo","sAtributoDescripcion"})
	@JsonProperty(value = "CursoSeccion")
	private CursoSeccion cursoSeccion;
	
	
	
	public InfoCursos(Curso curso, CursoSeccion cursoSeccion) {
		this.curso = curso;
		this.cursoSeccion = cursoSeccion;
	}
	
	
	public InfoCursos() {
	}

	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	@ApiModelProperty(readOnly = true)
	public CursoSeccion getCursoSeccion() {
		return cursoSeccion;
	}
	public void setCursoSeccion(CursoSeccion cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}
	
	
	

}
