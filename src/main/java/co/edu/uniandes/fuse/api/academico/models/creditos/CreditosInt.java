package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosInt {
	
	@JsonIgnoreProperties(value = { "CreditosAprobados", "CreditosHomologados", "CreditosInscritos",
			"SemestreSegunCreditos", "CreditosTransferidos" })
	@JsonProperty("Creditos")
	private Creditos creditos;

	public CreditosInt() {
	}

	public CreditosInt(Creditos creditos) {
		this.creditos = creditos;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}

}
