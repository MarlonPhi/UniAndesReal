package co.edu.uniandes.fuse.api.academico.models.egresado;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Titulos Egresado")
public class Titulos {
	
	@JsonProperty
	private String pidm;
	@JsonProperty
	private String nombreTitulo;
	@JsonProperty
	private String fechaObtencion;
	@JsonProperty
	private String nivel;
	@JsonProperty
	private String periodo;
	
	@ApiModelProperty(value = "Código PDIM", required = false, example = "230859")
	public String getPidm() {
		return pidm;
	}
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
	
	@ApiModelProperty(value = "Nombre titulo", required = false, example = "M-ARTI")
	public String getNombreTitulo() {
		return nombreTitulo;
	}
	public void setNombreTitulo(String nombreTitulo) {
		this.nombreTitulo = nombreTitulo;
	}
	
	@ApiModelProperty(value = "Fecha obtencion", required = false, example = "2016-10-21 00:00")
	public String getFechaObtencion() {
		return fechaObtencion;
	}
	public void setFechaObtencion(String fechaObtencion) {
		this.fechaObtencion = fechaObtencion;
	}
	
	@ApiModelProperty(value = "Nivel", required = false, example = "MA")
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	@ApiModelProperty(value = "Código PDIM", required = false, example = "201620")
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
	
}
