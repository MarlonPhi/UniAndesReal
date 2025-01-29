package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodosCalificadosMaxMin {
	
	@JsonProperty(value="MaxPeriodo")
	private String maximoPeriodo;
	
	@JsonProperty(value="MinPeriodo")
	private String minimoPeriodo;
	
	
	public PeriodosCalificadosMaxMin(String maximoPeriodo, String minimoPeriodo) {
		this.maximoPeriodo = maximoPeriodo;
		this.minimoPeriodo = minimoPeriodo;
	}
	
	

	public PeriodosCalificadosMaxMin() {
	}



	public String getMaximoPeriodo() {
		return maximoPeriodo;
	}

	public void setMaximoPeriodo(String maximoPeriodo) {
		this.maximoPeriodo = maximoPeriodo;
	}

	public String getMinimoPeriodo() {
		return minimoPeriodo;
	}

	public void setMinimoPeriodo(String minimoPeriodo) {
		this.minimoPeriodo = minimoPeriodo;
	}
	
	

}
