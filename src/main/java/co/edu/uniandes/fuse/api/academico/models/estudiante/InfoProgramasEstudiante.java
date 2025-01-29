package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Programas de Estudiante")
public class InfoProgramasEstudiante {

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
	@JsonProperty
	private boolean principal;
			
	@ApiModelProperty(value = "Código de la facultad", required = false, example = "AQ")
	public String getCodigoFacultad() {
		return codigoFacultad;
	}
	public void setCodigoFacultad(String codigoFacultad) {
		this.codigoFacultad = codigoFacultad;
	}
	@ApiModelProperty(value = "Nombre de la facultad", required = false, example = "ARQUITECTURA Y DISEÑO")
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	@ApiModelProperty(value = "Código del departamento", required = false, example = "DISE")
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	@ApiModelProperty(value = "Nombre del departamento", required = false, example = "DISEÑO")
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@ApiModelProperty(value = "Código del programa", required = false, example = "DISE")
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	@ApiModelProperty(value = "Nombre del programa", required = false, example = "DISEÑO INDUSTRIAL")
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@ApiModelProperty(value = "Código del Nivel", required = false, example = "PR")
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	@ApiModelProperty(value = "Programa principal", required = false, example = "true")
	public boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	
}
