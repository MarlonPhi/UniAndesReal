package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.model.Persona;

public class FotoEstudiante {
	
	@JsonProperty("Persona")
	private Persona persona;
	
	
	
	

	public FotoEstudiante(Persona persona) {
		this.persona = persona;
	}
	
	

	public FotoEstudiante() {
	}



	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	
	

}
