package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import org.codehaus.jackson.annotate.JsonProperty;

public class SalidaEsMatriculado {
	
	@JsonProperty(value="esMatriculado")
	private Boolean esMatriculado;
	
	
	

	public SalidaEsMatriculado(Boolean esMatriculado) {
		this.esMatriculado = esMatriculado;
	}
	
	

	public SalidaEsMatriculado() {
	}



	public Boolean getEsMatriculado() {
		return esMatriculado;
	}

	public void setEsMatriculado(Boolean esMatriculado) {
		this.esMatriculado = esMatriculado;
	}
	
	
	

}
