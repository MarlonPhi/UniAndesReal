package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSeccionesInfo {
	
	@JsonProperty("Seccionesinfo")
	private List<SeccionesInfo> seccionesInfo;

	public List<SeccionesInfo> getSeccionesInfo() {
		return seccionesInfo;
	}

	public void setSeccionesInfo(List<SeccionesInfo> seccionesInfo) {
		this.seccionesInfo = seccionesInfo;
	}
	
	

}
