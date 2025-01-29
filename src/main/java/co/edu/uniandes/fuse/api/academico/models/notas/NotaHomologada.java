package co.edu.uniandes.fuse.api.academico.models.notas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.cursos.CursoSeccion;
import co.edu.uniandes.fuse.api.academico.models.entity.Nota;

public class NotaHomologada implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnoreProperties(value= {"SCRN","DFechaNota","LNotasParciales"})
	@JsonProperty("Nota")
	private Nota nota;

	@JsonIgnoreProperties(value= {"sNombreCurso","sCodigoCurso","sCRN","sSeccion","sPeriodo","iCupoMax","iInscritos","sAtributo","sAtributoDescripcion"})
	@JsonProperty("CursoSeccion")
	private CursoSeccion cursoSeccion;
	
	public Nota getNota() {
		return this.nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public CursoSeccion getCursoSeccion() {
		return this.cursoSeccion;
	}

	public void setCursoSeccion(CursoSeccion cursoSeccion) {
		this.cursoSeccion = cursoSeccion;
	}
}
