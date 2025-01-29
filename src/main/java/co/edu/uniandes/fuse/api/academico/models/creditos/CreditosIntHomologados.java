package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosIntHomologados {

	@JsonIgnoreProperties(value = { "CreditosAprobados", "CreditosIntentados", "CreditosInscritos",
			"SemestreSegunCreditos", "CreditosTransferidos" })
	@JsonProperty("Creditos")
	private Creditos creditos;

	public CreditosIntHomologados() {
	}

	public CreditosIntHomologados(Creditos creditos) {
		this.creditos = creditos;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}
}
