package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSeccionesConteo {
	
	@JsonProperty("ConteoMaterias")
	private Integer conteoMaterias;
	
	

	public ResponseSeccionesConteo() {
	}

	public ResponseSeccionesConteo(Integer conteoMaterias) {
		this.conteoMaterias = conteoMaterias;
	}

	public Integer getConteoMaterias() {
		return conteoMaterias;
	}

	public void setConteoMaterias(Integer conteoMaterias) {
		this.conteoMaterias = conteoMaterias;
	}
	
	

}
