package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosAp {

	@JsonIgnoreProperties(value = { "CreditosTransferidos", "CreditosHomologados", "CreditosIntentados",
			"SemestreSegunCreditos", "CreditosInscritos" })
	@JsonProperty("Creditos")
	private Creditos creditos;

	public CreditosAp() {
	}

	public CreditosAp(Creditos creditos) {
		this.creditos = creditos;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}

}
