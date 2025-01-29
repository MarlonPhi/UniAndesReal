package co.edu.uniandes.fuse.api.academico.models.seccion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Horarios;
import co.edu.uniandes.fuse.api.academico.models.entity.Profesor;
import co.edu.uniandes.model.Curso;
import co.edu.uniandes.model.CursoSeccion;

public class SeccionHorarios {
	
	  @JsonIgnoreProperties(value= {"SCRN","SMateria","SSeccion"})
	  @JsonProperty("Horario")
	  private List<Horarios> horario;
	  
	  @JsonIgnoreProperties(value= {"sFacultad","sDescFacultad","sDepartamento","sDescDepartamento","sDescMateria"})
	  @JsonProperty("Curso")
	  private Curso curso;
	  
	  @JsonIgnoreProperties(value= {"sNombreCurso","sCodigoCurso","iCupoMax","iInscritos","sCreditos","sAtributo","sAtributoDescripcion"})
	  @JsonProperty("CursoSeccion")
	  private CursoSeccion cursoseccion;
	  
	  @JsonIgnoreProperties(value= {"sPidm"})
	  @JsonProperty("Profesor")
	  private List<Profesor> profesor;
	  
	  
	  

	public SeccionHorarios(List<Horarios> horario, Curso curso, CursoSeccion cursoseccion, List<Profesor> profesor) {

		this.horario = horario;
		this.curso = curso;
		this.cursoseccion = cursoseccion;
		this.profesor = profesor;
	}
	
	

	public SeccionHorarios() {
	}



	public List<Horarios> getHorario() {
		return horario;
	}

	public void setHorario(List<Horarios> horario) {
		this.horario = horario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public CursoSeccion getCursoseccion() {
		return cursoseccion;
	}

	public void setCursoseccion(CursoSeccion cursoseccion) {
		this.cursoseccion = cursoseccion;
	}

	public List<Profesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(List<Profesor> profesor) {
		this.profesor = profesor;
	}
	  
	  
	  

}
