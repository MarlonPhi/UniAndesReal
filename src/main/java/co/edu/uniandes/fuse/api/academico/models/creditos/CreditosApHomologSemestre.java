package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosApHomologSemestre {

	@JsonIgnoreProperties(value = { "CreditosTransferidos", "CreditosAprobados", "CreditosIntentados",
			"SemestreSegunCreditos", "CreditosInscritos" })
	@JsonProperty("Creditos")
	private Creditos creditos;

	public CreditosApHomologSemestre(Creditos creditos) {
		this.creditos = creditos;
	}

	public CreditosApHomologSemestre() {
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public void setCreditos(Creditos creditos) {
		this.creditos = creditos;
	}
}
