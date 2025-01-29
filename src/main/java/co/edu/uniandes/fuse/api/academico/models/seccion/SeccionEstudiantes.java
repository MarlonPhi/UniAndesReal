package co.edu.uniandes.fuse.api.academico.models.seccion;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Seccion Estudiantes")
public class SeccionEstudiantes {

	@JsonProperty
	private String codigo;
	
	/*Nombre Completo*/
	@JsonProperty
	private String nombre;
	
	@JsonProperty
	private String nombres;
	
	@JsonProperty
	private String apellidos;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String nivel;
	
	
	@ApiModelProperty(value = "Codigo del estudiante", required = false, example = "201411968")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@ApiModelProperty(value = "Nombre del estudiante", required = false, example = "BUSTOS CASTRO PABLO SANTIAGO")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ApiModelProperty(value = "Email del estudiante", required = false, example = "ps.bustos10@uniandes.edu.co")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ApiModelProperty(value = "Nivel del estudiante", required = false, example = "PR")
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	
}
