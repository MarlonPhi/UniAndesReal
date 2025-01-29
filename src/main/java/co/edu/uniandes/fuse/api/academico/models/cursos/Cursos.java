package co.edu.uniandes.fuse.api.academico.models.cursos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Cursos {

	@JsonProperty
	private String codigo;
	@JsonProperty
	private String numero;
	@JsonProperty
	private String curso;
	@JsonProperty
	private String nombre;
	@JsonProperty
	private String creditos;
	@JsonProperty
	private String nivel;
	
	@ApiModelProperty(value = "Codigo", required = false, example = "string")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@ApiModelProperty(value = "Numero", required = false, example = "string")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@ApiModelProperty(value = "Curso", required = false, example = "string")
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	@ApiModelProperty(value = "Nombre", required = false, example = "string")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@ApiModelProperty(value = "Creditos", required = false, example = "string")
	public String getCreditos() {
		return creditos;
	}
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	@ApiModelProperty(value = "Nivel", required = false, example = "string")
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
}
