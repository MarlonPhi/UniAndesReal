package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditosSemCreditos {
	
	@JsonIgnoreProperties(value= {"CreditosTransferidos","CreditosAprobados"
			,"CreditosIntentados","CreditosInscritos"
			,"CreditosHomologados"})
@JsonProperty("Creditos")
private Creditos creditos;



public CreditosSemCreditos(Creditos creditos) {
this.creditos = creditos;
}



public CreditosSemCreditos() {
}



public Creditos getCreditos() {
return creditos;
}

public void setCreditos(Creditos creditos) {
this.creditos = creditos;
}

}
