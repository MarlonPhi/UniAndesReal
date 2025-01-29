package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsePrestamo {
	
	@JsonProperty("Prestamos")
	private List<Prestamo> prestamo;
	
	

	public ResponsePrestamo() {
	}

	public ResponsePrestamo(List<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}

	public List<Prestamo> getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(List<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}
	
	
	

}
