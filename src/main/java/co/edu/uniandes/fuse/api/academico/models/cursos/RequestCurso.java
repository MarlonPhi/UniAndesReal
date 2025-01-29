package co.edu.uniandes.fuse.api.academico.models.cursos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCurso {
	
	@JsonProperty
	private int curso;
	@JsonProperty
	private int periodo;
	@JsonProperty
	private int crn;
	
	
	public RequestCurso(int curso, int periodo, int crn) {
		this.curso = curso;
		this.periodo = periodo;
		this.crn = crn;
	}


	public RequestCurso() {
		
	}


	public int getCurso() {
		return curso;
	}


	public void setCurso(int curso) {
		this.curso = curso;
	}


	public int getPeriodo() {
		return periodo;
	}


	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}


	public int getCrn() {
		return crn;
	}


	public void setCrn(int crn) {
		this.crn = crn;
	}
	

}
