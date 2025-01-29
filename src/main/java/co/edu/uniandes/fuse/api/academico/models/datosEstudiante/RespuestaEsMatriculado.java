package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import org.codehaus.jackson.annotate.JsonProperty;

public class RespuestaEsMatriculado {
	
	@JsonProperty("Salida")
	private SalidaEsMatriculado salida;
	
	

	public RespuestaEsMatriculado(SalidaEsMatriculado salida) {
		this.salida = salida;
	}
	
	public RespuestaEsMatriculado() {
	}

	public SalidaEsMatriculado getSalida() {
		return salida;
	}

	public void setSalida(SalidaEsMatriculado salida) {
		this.salida = salida;
	} 
	
	
	

}
