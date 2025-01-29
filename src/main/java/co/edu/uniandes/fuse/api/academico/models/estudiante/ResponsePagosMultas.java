package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsePagosMultas {

	@JsonProperty("PagosMultas")
	private List<PagosMultas> pagos;
	
	

	public ResponsePagosMultas() {
	}

	public ResponsePagosMultas(List<PagosMultas> pagos) {
		this.pagos = pagos;
	}

	public List<PagosMultas> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagosMultas> pagos) {
		this.pagos = pagos;
	}
	
	
}
