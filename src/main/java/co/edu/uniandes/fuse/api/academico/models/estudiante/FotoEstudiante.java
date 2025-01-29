package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class FotoEstudiante {

	@JsonProperty
	private String timestamp;
	@JsonProperty
	private String data;
	
	@ApiModelProperty(value = "Fecha (timestamp) ultima modificación del archivo", required = false, example = "1591292613463")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@ApiModelProperty(value = "Contenido binario del archivo", required = false, example = "[[Binary Data]]")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
