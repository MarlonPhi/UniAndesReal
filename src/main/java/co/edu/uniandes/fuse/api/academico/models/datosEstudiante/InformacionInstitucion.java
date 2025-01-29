package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InformacionInstitucion {

	 @JsonProperty(value="Salida")
	  private List<SalidaInformacionInstitucion> salida;
	 
	 

	public InformacionInstitucion(List<SalidaInformacionInstitucion> salida) {
		this.salida = salida;
	}
	
	

	public InformacionInstitucion() {
	}



	public List<SalidaInformacionInstitucion> getSalida() {
		return salida;
	}

	public void setSalida(List<SalidaInformacionInstitucion> salida) {
		this.salida = salida;
	}
	 
	 
	 
}
