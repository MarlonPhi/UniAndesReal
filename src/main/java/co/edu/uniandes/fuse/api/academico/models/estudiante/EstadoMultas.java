package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadoMultas {
	
	@JsonProperty(value="EstadoMultas")
	private List<SalidaEstadoMultas> estadoMultas;

	public EstadoMultas() {
	}

	public EstadoMultas(List<SalidaEstadoMultas> estadoMultas) {
		this.estadoMultas = estadoMultas;
	}

	public List<SalidaEstadoMultas> getEstadoMultas() {
		return estadoMultas;
	}

	public void setEstadoMultas(List<SalidaEstadoMultas> estadoMultas) {
		this.estadoMultas = estadoMultas;
	}
}
