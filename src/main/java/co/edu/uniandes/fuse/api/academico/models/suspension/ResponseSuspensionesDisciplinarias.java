package co.edu.uniandes.fuse.api.academico.models.suspension;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSuspensionesDisciplinarias {
	
	@JsonIgnoreProperties(value= {"SPeriodo","SCodigoSuspencion"})
	@JsonProperty("Suspension")
	private List<Suspension> suspension;

	public ResponseSuspensionesDisciplinarias(List<Suspension> suspension) {
		this.suspension = suspension;
	}
	

	public ResponseSuspensionesDisciplinarias() {
	}


	public List<Suspension> getSuspension() {
		if (this.suspension == null) this.suspension = new ArrayList<Suspension>();
		return this.suspension;
	}

	public void setSuspension(List<Suspension> suspension) {
		this.suspension = suspension;
	}

}
