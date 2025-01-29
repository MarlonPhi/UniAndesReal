package co.edu.uniandes.fuse.api.academico.models.estudiante;

import org.codehaus.jackson.annotate.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametroPregunta {
	
	@JsonProperty(value="ParametroPregunta")
	private String parameterQuest;
	
	

	public ParametroPregunta() {
	}

	public ParametroPregunta(String parameterQuest) {
		this.parameterQuest = parameterQuest;
	}

	public String getParameterQuest() {
		return parameterQuest;
	}

	public void setParameterQuest(String parameterQuest) {
		this.parameterQuest = parameterQuest;
	}
}
