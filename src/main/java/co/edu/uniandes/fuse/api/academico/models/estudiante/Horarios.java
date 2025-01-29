package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Horarios {
	
	@JsonProperty(value="codigoCurso")
	private String materia;
	
	@JsonProperty(value="numeroCurso")
	private String numero_curso;
	
	@JsonProperty(value="nombreCurso")
	private String nombre_curso;
	
	@JsonProperty(value="seccion")
	private String seccion;

	@JsonProperty(value="periodo")
	private String periodo;
	
	@JsonProperty(value="NRC")
	private Integer nrc;
	
	@JsonProperty(value="horario")
	private List<Horario> horario;

	
	
	public Horarios() {
	}


	public Horarios(String periodo, String materia, String numero_curso, String nombre_curso, String seccion,
			Integer nrc, List<Horario> horario) {
		super();
		this.periodo = periodo;
		this.materia = materia;
		this.numero_curso = numero_curso;
		this.nombre_curso = nombre_curso;
		this.seccion = seccion;
		this.nrc = nrc;
		this.horario = horario;
	}






	public String getPeriodo() {
		return periodo;
	}



	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}



	public String getMateria() {
		return materia;
	}



	public void setMateria(String materia) {
		this.materia = materia;
	}



	public String getNumero_curso() {
		return numero_curso;
	}



	public void setNumero_curso(String numero_curso) {
		this.numero_curso = numero_curso;
	}



	public String getNombre_curso() {
		return nombre_curso;
	}



	public void setNombre_curso(String nombre_curso) {
		this.nombre_curso = nombre_curso;
	}



	public String getSeccion() {
		return seccion;
	}



	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}



	public Integer getNrc() {
		return nrc;
	}



	public void setNrc(Integer nrc) {
		this.nrc = nrc;
	}



	public List<Horario> getHorario() {
		//return horario = new ArrayList<>();
		return horario ;
	}



	public void setHorario(List<Horario> horario) {
		this.horario = horario ;
	}

	
	
}