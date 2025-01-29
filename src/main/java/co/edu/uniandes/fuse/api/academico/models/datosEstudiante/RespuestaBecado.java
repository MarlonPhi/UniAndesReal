package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Becado;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RespuestaBecado {

	 @JsonProperty("Becados")
	  private List<Becado> becados;
	 
	 
	 

	public RespuestaBecado(List<Becado> becados) {
		this.becados = becados;
	}
	
	

	public RespuestaBecado() {
	}



	public List<Becado> getBecados() {
		return becados;
	}

	public void setBecados(List<Becado> becados) {
		this.becados = becados;
	}
	 
	 
	 
}
