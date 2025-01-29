package co.edu.uniandes.fuse.api.academico.models.creditos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosApHomologados {
	
	@JsonIgnoreProperties(value= {"CreditosTransferidos","CreditosAprobados"
									,"CreditosIntentados","SemestreSegunCreditos"
									,"CreditosInscritos"})
	@JsonProperty("Creditos")
	private Creditos creditos;
	
	

	public CreditosApHomologados(Creditos creditos) {
		this.creditos = creditos;
	}
	
	

	public CreditosApHomologados() {
	}



	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}
	
	

}
