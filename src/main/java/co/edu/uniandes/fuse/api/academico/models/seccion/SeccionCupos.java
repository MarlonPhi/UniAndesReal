package co.edu.uniandes.fuse.api.academico.models.seccion;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SeccionCupos {

	@JsonProperty("CRN")
	private String crn;

	@JsonProperty("Periodo")
	private String periodo;

	@JsonProperty("CupoMaximo")
	private Integer cupoMaximo;
	
	@JsonProperty("Inscritos")
	private Integer cupoInscritos;

	public SeccionCupos() {
	}



	public SeccionCupos(String crn, String periodo, Integer cupoMaximo, Integer cupoInscritos) {

		this.crn = crn;
		this.periodo = periodo;
		this.cupoMaximo = cupoMaximo;
		this.cupoInscritos = cupoInscritos;
	}



	@ApiModelProperty(value = "CRN", required = false, example = "13577")
	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	@ApiModelProperty(value = "Periodo académico", required = false, example = "201510")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@ApiModelProperty(value = "Cupo máximo curso", required = false, example = "60")
	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}


	@ApiModelProperty(value = "Cupo máximo curso", required = false, example = "35")
	public Integer getCupoInscritos() {
		return cupoInscritos;
	}



	public void setCupoInscritos(Integer cupoInscritos) {
		this.cupoInscritos = cupoInscritos;
	}
	
	

}
