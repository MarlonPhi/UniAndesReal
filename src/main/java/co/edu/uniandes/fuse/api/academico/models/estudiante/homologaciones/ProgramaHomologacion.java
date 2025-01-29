package co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Programas de Homologacion")
public class ProgramaHomologacion {

	@JsonProperty
	private String programa;
	@JsonProperty
	private String nombre;
	@JsonProperty
	private String codigo;
	@JsonProperty
	private String nivel;
	@JsonProperty
	private String codigoGrado;
	
	@ApiModelProperty(value = "Numero programa", required = false, example = "1")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@ApiModelProperty(value = "Nombre programa", required = false, example = "string")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@ApiModelProperty(value = "Codigo programa", required = false, example = "0")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@ApiModelProperty(value = "Nivel programa", required = false, example = "0")
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	@ApiModelProperty(value = "Codigo grado programa", required = false, example = "0")
	public String getCodigoGrado() {
		return codigoGrado;
	}
	public void setCodigoGrado(String codigoGrado) {
		this.codigoGrado = codigoGrado;
	}
	
	
}
