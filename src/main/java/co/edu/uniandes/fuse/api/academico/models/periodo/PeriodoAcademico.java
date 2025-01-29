package co.edu.uniandes.fuse.api.academico.models.periodo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.model.Periodo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Periodo Academico para fechas")
public class PeriodoAcademico {
	
	@JsonProperty(value="Periodo")
	private PeriodoAcademicoAnio periodo;
	
	
	
	public PeriodoAcademico(PeriodoAcademicoAnio periodo) {
		this.periodo = periodo;
	}
	
	

	public PeriodoAcademico() {
	}


	public PeriodoAcademicoAnio getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoAcademicoAnio periodo) {
		this.periodo = periodo;
	}
	
	

}
