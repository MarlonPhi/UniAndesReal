package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creditos {

	@JsonProperty("CreditosTransferidos")
	private String creditosTransferidos = "";

	@JsonProperty("CreditosAprobados")
	private String creditosAprobados = "";
	
	@JsonProperty("CreditosIntentados")
	private String creditosIntentados = "";
	
	@JsonProperty("SemestreSegunCreditos")
	private String semestreSegunCreditos = "";
	
	@JsonProperty("CreditosInscritos")
	private String creditosInscritos = "";
	
	@JsonProperty("CreditosHomologados")
	private String creditosHomologados = "";
	
	


	

	public Creditos(String creditosTransferidos, String creditosAprobados, String creditosIntentados,
			String semestreSegunCreditos, String creditosInscritos, String creditosHomologados) {

		this.creditosTransferidos = creditosTransferidos;
		this.creditosAprobados = creditosAprobados;
		this.creditosIntentados = creditosIntentados;
		this.semestreSegunCreditos = semestreSegunCreditos;
		this.creditosInscritos = creditosInscritos;
		this.creditosHomologados = creditosHomologados;
	}



	public Creditos() {
		
	}



	public String getCreditosTransferidos() {
		return creditosTransferidos;
	}

	public void setCreditosTransferidos(String creditosTransferidos) {
		this.creditosTransferidos = creditosTransferidos;
	}

	public String getCreditosAprobados() {
		return creditosAprobados;
	}

	public void setCreditosAprobados(String creditosAprobados) {
		this.creditosAprobados = creditosAprobados;
	}

	public String getCreditosIntentados() {
		return creditosIntentados;
	}

	public void setCreditosIntentados(String creditosIntentados) {
		this.creditosIntentados = creditosIntentados;
	}

	public String getSemestreSegunCreditos() {
		return semestreSegunCreditos;
	}

	public void setSemestreSegunCreditos(String semestreSegunCreditos) {
		this.semestreSegunCreditos = semestreSegunCreditos;
	}

	public String getCreditosInscritos() {
		return creditosInscritos;
	}

	public void setCreditosInscritos(String creditosInscritos) {
		this.creditosInscritos = creditosInscritos;
	}



	public String getCreditosHomologados() {
		return creditosHomologados;
	}



	public void setCreditosHomologados(String creditosHomologados) {
		this.creditosHomologados = creditosHomologados;
	}
	
	
}
