package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de Identificacion con los atributos código, login y pidm
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
@ApiModel(description = "Modelo de Identificacion")
public class Identificacion {

	// Atributos
	@JsonProperty
	private String codigo;
	@JsonProperty
	private String login;
	@JsonProperty
	private String pidm;

	// Métodos
	@ApiModelProperty(value = "Codigo de la persona", required = false, example = "201021881")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ApiModelProperty(value = "Login de la persona", required = false, example = "cafanador")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@ApiModelProperty(value = "Pidm de la persona", required = false, example = "230859")
	public String getPidm() {
		return pidm;
	}

	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
}