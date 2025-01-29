package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;


public class ProgramasEstudiante {
	

//-------------------------------------------------------------------------------
// ----------------------------- ATTRIBUTES -------------------------------------
//-------------------------------------------------------------------------------
	
	@JsonProperty
	private List<InfoProgramasEstudiante> programa;
	
	
	
//------------------------------------------------------------------------------
// ----------------------------- METHODS ---------------------------------------
//------------------------------------------------------------------------------	
	
	@ApiModelProperty(value = "Programas del estudiante", required = false, example = "")
	public List<InfoProgramasEstudiante> getProgramas() {
		return programa;
	}

	public void setPrograma(List<InfoProgramasEstudiante> programa) {
		this.programa = programa;
	}
	
	
	
}
