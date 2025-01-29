package co.edu.uniandes.fuse.api.academico.models.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;



@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value="SPrimerNombre")
	private String sprimerNombre = "";
	
	@JsonProperty(value="SSegundoNombre")
	private String ssegundoNombre = "";
	
	@JsonProperty(value="SPrimerApellido")
	private String sprimerApellido = "";
	
	@JsonProperty(value="SSegundoApellido")
	private String ssegundoApellido = "";
	
	
	

	
	

	public Persona(String sprimerNombre, String ssegundoNombre, String sprimerApellido, String ssegundoApellido) {
		this.sprimerNombre = sprimerNombre;
		this.ssegundoNombre = ssegundoNombre;
		this.sprimerApellido = sprimerApellido;
		this.ssegundoApellido = ssegundoApellido;
	}




	public Persona() {
	}




	public String getSprimerNombre() {
		return sprimerNombre;
	}




	public void setSprimerNombre(String sprimerNombre) {
		this.sprimerNombre = sprimerNombre;
	}




	public String getSsegundoNombre() {
		return ssegundoNombre;
	}




	public void setSsegundoNombre(String ssegundoNombre) {
		this.ssegundoNombre = ssegundoNombre;
	}




	public String getSprimerApellido() {
		return sprimerApellido;
	}




	public void setSprimerApellido(String sprimerApellido) {
		this.sprimerApellido = sprimerApellido;
	}




	public String getSsegundoApellido() {
		return ssegundoApellido;
	}




	public void setSsegundoApellido(String ssegundoApellido) {
		this.ssegundoApellido = ssegundoApellido;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	

}
