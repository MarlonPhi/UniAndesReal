package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de Documento con los atributos tipo de documento, descripción
 * documento, número documento, fecha de expedición, fecha de vencimiento y
 * estatus activo
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
@ApiModel(description = "Modelo de Documento")
public class Documento {

	// Atributos
	@JsonProperty
	private String tipoDocumento;
	@JsonProperty
	private String descripcionDocumento;
	@JsonProperty
	private String numeroDocumento;
	@JsonProperty
	private String fechaExpedicion;
	@JsonProperty
	private String fechaVencimiento;
	@JsonProperty
	private Boolean activo;

	// Métodos
	@ApiModelProperty(value = "Tipo de documento", required = false, example = "C")
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@ApiModelProperty(value = "Descripción del tipo de documento", required = false, example = "CEDULA DE CIUDADANIA")
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	@ApiModelProperty(value = "Número del documento", required = false, example = "BB284917")
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@ApiModelProperty(value = "Fecha de expedición del documento", required = false, example = "31/10/2018")
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	@ApiModelProperty(value = "Fecha de vencimiento del documento", required = false, example = "18/10/2023")
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@ApiModelProperty(value = "Estado activo del documento", required = false, example = "true")
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
