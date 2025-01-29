package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromedioAcumulado {
	
	@JsonProperty("PromedioAcumulado")
	private String promedioAcumuladoEstudiante = "";

	public PromedioAcumulado(String promedioAcumuladoEstudiante) {
		this.promedioAcumuladoEstudiante = promedioAcumuladoEstudiante;
	}

	public PromedioAcumulado() {
	}
	

	public String getPromedioAcumuladoEstudiante() {
		return promedioAcumuladoEstudiante;
	}

	public void setPromedioAcumuladoEstudiante(String promedioAcumuladoEstudiante) {
		this.promedioAcumuladoEstudiante = promedioAcumuladoEstudiante;
	}
}
