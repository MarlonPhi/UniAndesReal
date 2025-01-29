package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMultas {
	
	@JsonProperty("Multas")
	private List<Multas> multas;
	
	

	public ResponseMultas(List<Multas> multas) {
		this.multas = multas;
	}
	
	

	public ResponseMultas() {
	}



	public List<Multas> getMultas() {
		return multas;
	}

	public void setMultas(List<Multas> multas) {
		this.multas = multas;
	}
	
	

}
