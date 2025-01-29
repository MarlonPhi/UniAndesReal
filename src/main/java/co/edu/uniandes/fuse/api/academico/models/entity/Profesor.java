package co.edu.uniandes.fuse.api.academico.models.entity;

import org.codehaus.jackson.annotate.JsonProperty;


public class Profesor {

	@JsonProperty(value = "SPidm")
	private String sPidm = "";
	@JsonProperty(value = "CPersona")
	private Persona cPersona;
	@JsonProperty(value = "SLogin")
	private String sLogin = "";
	
	
	public Profesor(String sPidm, Persona cPersona, String sLogin) {
		this.sPidm = sPidm;
		this.cPersona = cPersona;
		this.sLogin = sLogin;
	}

	public Profesor() {
	}

	public String getsPidm() {
		return sPidm;
	}

	public void setsPidm(String sPidm) {
		this.sPidm = sPidm;
	}

	public Persona getcPersona() {
		return cPersona;
	}

	public void setcPersona(Persona cPersona) {
		this.cPersona = cPersona;
	}

	public String getsLogin() {
		return sLogin;
	}

	public void setsLogin(String sLogin) {
		this.sLogin = sLogin;
	}
	
	
	
	
}
