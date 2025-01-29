package co.edu.uniandes.fuse.api.academico.models.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class Estudiante implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="Spidm")
	private String spidm;
	@JsonProperty(value="Slogin")
	private String slogin;
	@JsonProperty(value="Scodigo")
	private String scodigo;
	
	
	
	
	public Estudiante(String spidm, String slogin, String scodigo) {
		this.spidm = spidm;
		this.slogin = slogin;
		this.scodigo = scodigo;
	}
	
	
	
	public Estudiante() {
	}



	public String getSpidm() {
		return spidm;
	}
	public void setSpidm(String spidm) {
		this.spidm = spidm;
	}
	public String getSlogin() {
		return slogin;
	}
	public void setSlogin(String slogin) {
		this.slogin = slogin;
	}
	public String getScodigo() {
		return scodigo;
	}
	public void setScodigo(String scodigo) {
		this.scodigo = scodigo;
	}
	
	
	

}
