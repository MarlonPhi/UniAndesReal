package co.edu.uniandes.fuse.api.academico.models.seccion;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Seccion")
public class Seccion {

	@JsonProperty
	private String nombreCorto;
	
	@JsonProperty
	private String nombreLargo;
	
	@JsonProperty
	private String materia;
	
	@JsonProperty
	private String numero;
	
	@JsonProperty
	private String seccion;
	
	
	@ApiModelProperty(value = "Nombre corto", required = false, example = "COMPUTACION CIENTIFICA EN IEE")
	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	@ApiModelProperty(value = "Nombre largo", required = false, example = "CURSO DE COMPUTACIÓN CIENTÍFICA EN INGENIERIA")
	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	@ApiModelProperty(value = "Materia", required = false, example = "IELE")
	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@ApiModelProperty(value = "Número", required = false, example = "2009")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@ApiModelProperty(value = "Sección", required = false, example = "1")
	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}	
}