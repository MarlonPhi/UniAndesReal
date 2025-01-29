package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class InformacionSobrepaso {
	
	@JsonProperty("Salida")
	private List<SalidaInformacionSobrepaso> salida;

	public InformacionSobrepaso(List<SalidaInformacionSobrepaso> salida) {
		this.salida = salida;
	}

	public InformacionSobrepaso() {
	}

	public List<SalidaInformacionSobrepaso> getSalida() {
		return salida;
	}

	public void setSalida(List<SalidaInformacionSobrepaso> salida) {
		this.salida = salida;
	}
	

}
