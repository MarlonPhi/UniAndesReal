package co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Opciones Academicas Homologacion")
public class OpcionesAcademicasHomologacion {

	@JsonProperty
	private String opcion;

	public String getOpcion() {
		return opcion;
	}

	@ApiModelProperty(value = "Opcion academica", required = false, example = "1")
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	
	
}
