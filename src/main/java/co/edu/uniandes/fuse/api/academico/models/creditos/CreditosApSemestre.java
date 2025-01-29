package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosApSemestre {

	@JsonIgnoreProperties(value = { "CreditosTransferidos", "CreditosHomologados", "CreditosIntentados",
			"SemestreSegunCreditos", "CreditosInscritos" })
	@JsonProperty("Creditos")
	private Creditos creditos;

	public CreditosApSemestre() {
	}

	public CreditosApSemestre(Creditos creditos) {
		this.creditos = creditos;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}
}
