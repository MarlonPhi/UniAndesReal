package co.edu.uniandes.fuse.api.academico.models.estudiante;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseNivelAcademico {
	
	@JsonProperty("NivelAcademico")
	private String nivelAcademico;
	
	
	
	
	public ResponseNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	public ResponseNivelAcademico() {
	}
	
	

	public String getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	
	
}
