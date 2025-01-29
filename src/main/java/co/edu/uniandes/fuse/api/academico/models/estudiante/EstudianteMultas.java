package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstudianteMultas {
	
	@JsonProperty(value="SPidm")
	private String SPidm;
	@JsonProperty(value = "SCodigo")
	private String sCodigo;
	
	

	public EstudianteMultas() {
	}

	public EstudianteMultas(String spidm) {
		this.SPidm = spidm;
	}

	public String getsPidm() {
		return SPidm;
	}

	public void setsPidm(String spidm) {
		this.SPidm = spidm;
	}

	public String getsCodigo() {
		return sCodigo;
	}

	public void setsCodigo(String sCodigo) {
		this.sCodigo = sCodigo;
	} 
	
	

}
