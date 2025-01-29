package co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Periodos Suspecion Academica Homologacion")
public class PeriodosSuspencionAcademicaHomologacion {

	@JsonProperty
	private String periodo;

	@ApiModelProperty(value = "Periodo academico", required = false, example = "1")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}