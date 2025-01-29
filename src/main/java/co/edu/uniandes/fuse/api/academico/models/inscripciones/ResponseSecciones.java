package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSecciones {
	
	@JsonProperty("Secciones")
	private Secciones secciones;
	
	

	public ResponseSecciones() {
	}

	public ResponseSecciones(Secciones secciones) {
		this.secciones = secciones;
	}

	public Secciones getSecciones() {
		return secciones;
	}

	public void setSecciones(Secciones secciones) {
		this.secciones = secciones;
	}
	
	

}
