package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.model.RecursoBibliografico;

public class Prestamo {

	@JsonIgnoreProperties(value= {"sIdItem"})
	@JsonProperty("PrestamoRecurso")
	private PrestamoRecurso prestamoRecurso;

	@JsonProperty("RecursoBibliografico")
	private RecursoBibliografico recursoBibliografico;
	

	public Prestamo(PrestamoRecurso prestamoRecurso, RecursoBibliografico recursoBibliografico) {

		this.prestamoRecurso = prestamoRecurso;
		this.recursoBibliografico = recursoBibliografico;
	}

	
	public Prestamo() {
	}
	

	public PrestamoRecurso getPrestamoRecurso() {
		return prestamoRecurso;
	}

	public void setPrestamoRecurso(PrestamoRecurso prestamoRecurso) {
		this.prestamoRecurso = prestamoRecurso;
	}

	public RecursoBibliografico getRecursoBibliografico() {
		return recursoBibliografico;
	}

	public void setRecursoBibliografico(RecursoBibliografico recursoBibliografico) {
		this.recursoBibliografico = recursoBibliografico;
	}

}
