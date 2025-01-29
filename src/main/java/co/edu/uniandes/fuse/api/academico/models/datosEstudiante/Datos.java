package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de Datos con los atributos nombres, apellidos, fecha de nacimiento,
 * nacionalidad, género y estado civil
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
@ApiModel(description = "Modelo de Datos")
public class Datos {

	// Atributos
	@JsonProperty
	private String primerNombre;
	@JsonProperty
	private String segundoNombre;
	@JsonProperty
	private String primerApellido;
	@JsonProperty
	private String segundoApellido;
	@JsonProperty
	private String fechaNacimiento;
	@JsonProperty
	private String genero;
	@JsonProperty
	private String nacionalidad;
	@JsonProperty
	private String estadoCivil;

	// Métodos
	@ApiModelProperty(value = "Primer nombre de la persona", required = false, example = "JUAN")
	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	@ApiModelProperty(value = "Segundo nombre de la persona", required = false, example = "PABLO")
	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	@ApiModelProperty(value = "Primer apellido de la persona", required = false, example = "PEREZ")
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	@ApiModelProperty(value = "Segundo apellido de la persona", required = false, example = "RODRIGUEZ")
	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	@ApiModelProperty(value = "Fecha de nacimiento de la persona", required = false, example = "14/05/1990")
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@ApiModelProperty(value = "Género de la persona", required = false, example = "MASCULINO")
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@ApiModelProperty(value = "Nacionalidad de la persona", required = false, example = "CO")
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@ApiModelProperty(value = "Estado civil de la persona", required = false, example = "SOLTERO")
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
}
