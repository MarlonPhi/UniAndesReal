package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.model.EstadoAcademico;

public class InformacionEstadoAcademico {
	
	@JsonProperty("EstadoAcademico")
	private List<EstadoAcademico> estadoAcademico;

	public InformacionEstadoAcademico(List<EstadoAcademico> estadoAcademico) {
		this.estadoAcademico = estadoAcademico;
	}

	public InformacionEstadoAcademico() {
	}

	public List<EstadoAcademico> getEstadoAcademico() {
		return estadoAcademico;
	}

	public void setEstadoAcademico(List<EstadoAcademico> estadoAcademico) {
		this.estadoAcademico = estadoAcademico;
	}

}
