package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class informacionPersonalOpciones {

	@JsonProperty("Opciones")
	private List<OpcionEstudiante> opciones;

	public informacionPersonalOpciones(List<OpcionEstudiante> opciones) {
		this.opciones = opciones;
	}

	public informacionPersonalOpciones() {
	}

	public List<OpcionEstudiante> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OpcionEstudiante> opciones) {
		this.opciones = opciones;
	}
	 
	 
}
