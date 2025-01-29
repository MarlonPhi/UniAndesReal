package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCrn {
	
	@JsonProperty(value="CRN")
	private String crn = "";
	
	

	public ResponseCrn() {
	}

	public ResponseCrn(String crn) {
		this.crn = crn;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}
	
	

}
