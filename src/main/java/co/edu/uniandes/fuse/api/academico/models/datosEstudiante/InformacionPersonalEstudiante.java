package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;

public class InformacionPersonalEstudiante implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value="Persona")
	private Persona persona;

	@JsonProperty(value="Estudiante")
	private Estudiante estudiante;
	
	
	

	public InformacionPersonalEstudiante(Persona persona, Estudiante estudiante) {
		this.persona = persona;
		this.estudiante = estudiante;
	}
	
	

	public InformacionPersonalEstudiante() {
	}



	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	  

}
