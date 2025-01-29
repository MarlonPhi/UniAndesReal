package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalificadosMaxMIn {
	
	@JsonProperty(value="SemestresCalificados")
	private PeriodosCalificadosMaxMin semestreCalificado;

	public CalificadosMaxMIn(PeriodosCalificadosMaxMin semestreCalificado) {
		this.semestreCalificado = semestreCalificado;
	}

	public CalificadosMaxMIn() {
	}

	public PeriodosCalificadosMaxMin getSemestreCalificado() {
		return semestreCalificado;
	}

	public void setSemestreCalificado(PeriodosCalificadosMaxMin semestreCalificado) {
		this.semestreCalificado = semestreCalificado;
	}
	
	
	


}
