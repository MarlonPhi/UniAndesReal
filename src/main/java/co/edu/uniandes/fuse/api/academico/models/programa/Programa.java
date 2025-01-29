package co.edu.uniandes.fuse.api.academico.models.programa;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de Ciudad con c�digo de la ciudad y descripci�n
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-05-04
 */
@ApiModel(description = "Modelo de Programa")
public class Programa {

	@JsonProperty
	private String codigoFacultad;
	@JsonProperty
	private String facultad;
	@JsonProperty
	private String codigoDepartamento;
	@JsonProperty
	private String departamento;
	@JsonProperty
	private String codigoPrograma;
	@JsonProperty
	private String programa;
	@JsonProperty
	private String nivel;
			
	@ApiModelProperty(value = "C�digo de la facultad", required = false, example = "AQ")
	public String getCodigoFacultad() {
		return codigoFacultad;
	}
	public void setCodigoFacultad(String codigoFacultad) {
		this.codigoFacultad = codigoFacultad;
	}
	@ApiModelProperty(value = "Nombre de la facultad", required = false, example = "ARQUITECTURA Y DISE�O")
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	@ApiModelProperty(value = "C�digo del departamento", required = false, example = "DISE")
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	@ApiModelProperty(value = "Nombre del departamento", required = false, example = "DISE�O")
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@ApiModelProperty(value = "C�digo del programa", required = false, example = "DISE")
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	@ApiModelProperty(value = "Nombre del programa", required = false, example = "DISE�O INDUSTRIAL")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@ApiModelProperty(value = "C�digo del Nivel", required = false, example = "PR")
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
	
}


