package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseTurno {
	
	@JsonProperty("Turno")
	private List<Turno> turno;
	

	public ResponseTurno() {
	}

	public ResponseTurno(List<Turno> turno) {
		this.turno = turno;
	}

	public List<Turno> getTurno() {
		return turno;
	}

	public void setTurno(List<Turno> turno) {
		this.turno = turno;
	}


	
	

}
