package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadoEzProxy {
	
	@JsonProperty(value = "ValidacionEzproxy")
	private String validacionEzproxy;
	
	public String getValidacionEzproxy() {
		return validacionEzproxy;
	}
	public void setValidacionEzproxy(String validacionEzproxy) {
		this.validacionEzproxy = validacionEzproxy;
	}

}
