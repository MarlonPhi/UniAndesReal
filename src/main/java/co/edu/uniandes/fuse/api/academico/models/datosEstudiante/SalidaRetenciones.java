package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SalidaRetenciones {
	
	@JsonProperty("Retenciones")
	  private List<InformacionPersonalRetenciones> retenciones;

	public SalidaRetenciones(List<InformacionPersonalRetenciones> retenciones) {
		this.retenciones = retenciones;
	}

	public SalidaRetenciones() {
	}

	public List<InformacionPersonalRetenciones> getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(List<InformacionPersonalRetenciones> retenciones) {
		this.retenciones = retenciones;
	}
	
	
	
	
	
	

}
