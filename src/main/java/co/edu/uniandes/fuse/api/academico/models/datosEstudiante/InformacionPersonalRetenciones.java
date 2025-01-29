package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import co.edu.uniandes.model.Estudiante;

@JsonAutoDetect
@JsonSerialize
public class InformacionPersonalRetenciones {
	
	@JsonProperty("Estudiante")
	private Estudiante estudiante;
	
	@JsonProperty("Retencion")
	  private Retencion retencion;

	public InformacionPersonalRetenciones(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public InformacionPersonalRetenciones() {
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Retencion getRetencion() {
		return retencion;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}
	
	
	

}
